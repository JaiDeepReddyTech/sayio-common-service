package com.talkio.smartdevice.common.enums;


import com.talkio.smartdevice.common.model.TalkioResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TalkioSuccess implements TalkioResponseStatus {
    Success1("0001", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK),
    Success2("0002", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK),
    Success3("0003", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK),
    Success4("0004", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK),
    Success5("0005", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK),
    Success6("0006", "TALKIO-SUC-COM", "label.talkio.success.code", HttpStatus.OK);


    private int statusId;
    private String statusCode;
    private String statusTypeCode;
    private String statusValue;
    private HttpStatus status;

    TalkioSuccess(String statusCode, String statusTypeCode, String statusValue, HttpStatus status) {
        this.statusCode = statusCode;
        this.statusTypeCode = statusTypeCode;
        this.statusValue = statusValue;
        this.status = status;
    }
}
