package com.talkio.smartdevice.common.enums;


import com.talkio.smartdevice.common.model.TalkioResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TalkioError implements TalkioResponseStatus {
    Error0("0000", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.INTERNAL_SERVER_ERROR),
    Error1("0001", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.METHOD_NOT_ALLOWED),
    Error2("0002", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    Error3("0003", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.NOT_ACCEPTABLE),
    Error4("0004", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error5("0005", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error6("0006", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error7("0007", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error8("0008", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.NOT_FOUND),
    Error20("0020", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error21("0021", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error22("0022", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.INTERNAL_SERVER_ERROR),
    Error23("0023", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error24("0024", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error25("0025", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error26("0026", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error27("0027", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),
    Error28("0028", "TALKIO-ERR-VAL","label.talkio.error.code", HttpStatus.BAD_REQUEST),


    Error29("0029", "TALKIO-ERR-COM","label.talkio.error.code", HttpStatus.NOT_FOUND);


    private String statusCode;
    private String statusTypeCode;
    private String statusValue;
    private HttpStatus status;

    TalkioError(String statusCode, String statusTypeCode, String statusValue, HttpStatus status) {
        this.statusCode = statusCode;
        this.statusTypeCode = statusTypeCode;
        this.statusValue = statusValue;
        this.status = status;
    }
}
