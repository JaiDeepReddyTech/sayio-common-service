package com.talkio.smartdevice.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliasUtil {
    protected static final Logger logger = LoggerFactory.getLogger(AliasUtil.class);

    @Autowired
    EncryptionUtil encryptionUtil;

    public String generateAlias(String message){
        String alias = null;
        try {
            if(message != null) {
                alias = encryptionUtil.getMD5((message.replace(" ", "-").toLowerCase()));
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        return alias;
    }
}
