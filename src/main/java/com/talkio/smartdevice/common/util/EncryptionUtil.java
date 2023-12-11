package com.talkio.smartdevice.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Formatter;
import java.util.UUID;

@Component
public class EncryptionUtil {

    @Autowired
    CryptoUtil cryptoUtil;
    private final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

    private final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
    private final int IV_LENGTH_BYTE = 12;
    private final int SALT_LENGTH_BYTE = 16;
    private final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * Method to generate Token based on Random UUID, user name & current
     * timestamp
     *
     * @param userName
     * @return
     */
    public String generateAuthToken(String userName) {
        java.util.Date date = new java.util.Date();

        String key = UUID.randomUUID().toString().toUpperCase() + "|" + userName + "|" + new Timestamp(date.getTime());

        byte[] tokenByte = Base64.getEncoder().encode(key.getBytes());
        String token = new String(tokenByte);

        return token.replace("=", "");
    }

    /**
     * Method to crypt the string
     *
     * @param value
     * @return byte[] password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String getSha512(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return byteToHex(MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8")));
    }

    public String getMD5(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return byteToHex(MessageDigest.getInstance("MD5").digest(value.getBytes("UTF-8")));
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public byte[] HexStringToByteArray(String s) {
        byte data[] = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
        }
        return data;
    }

    public String generatePIN() {
        int randomPIN = (int) (Math.random() * 900000) + 100000;
        return "" + randomPIN;
    }

    public String encrypt(byte[] pText, String password) throws Exception {

        // 16 bytes salt
        byte[] salt = cryptoUtil.getRandomNonce(SALT_LENGTH_BYTE);
        // GCM recommended 12 bytes iv?
        byte[] iv = cryptoUtil.getRandomNonce(IV_LENGTH_BYTE);
        // secret key from password
        SecretKey aesKeyFromPassword = cryptoUtil.getAESKeyFromPassword(password.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        // ASE-GCM needs GCMParameterSpec
        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] cipherText = cipher.doFinal(pText);
        // prefix IV and Salt to cipher text
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv)
                .put(salt)
                .put(cipherText)
                .array();

        // string representation, base64, send this string to other for decryption.
        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
    }

    // we need the same password, salt and iv to decrypt it
    public String decrypt(String cText, String password) throws Exception {

        byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));
        // get back the iv and salt from the cipher text
        ByteBuffer bb = ByteBuffer.wrap(decode);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        byte[] salt = new byte[SALT_LENGTH_BYTE];
        bb.get(salt);
        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);
        // get back the aes key from the same password and salt
        SecretKey aesKeyFromPassword = cryptoUtil.getAESKeyFromPassword(password.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, UTF_8);

    }

    /*
     * just for testing purpose
     * */
    public void main(String[] args) throws Exception {
        String pText = "{\"clientId\":\"f5b6316a9241498eefd75b82290a78a4\",\"assetId\":\"A888\",\"productTypeName\":\"RAR\",\"modelNo\":\"m45654\",\"deviceId\":\"D888\",\"productName\":\"P High\",\"productTypeId\":\"a642340ea4148bf83f1a126ef5862585\",\"url\":\"wwer\",\"imeiId\":\"8888\"}";
        String key = "0e027b10446bff97b72828bd65c1b355f747e6564a449859e6a3f735f3e23d9f3e4f428f51105551c9848666074d09a3da84e8d7b3e3dc0e0381af457054412c";
        String encrypt = encrypt(pText.getBytes(UTF_8), key);
        System.out.println(encrypt);
        //decrypt
        String decryptedText = decrypt(encrypt, key);
        System.out.println(decryptedText);
    }

}
