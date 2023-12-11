package com.talkio.smartdevice.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalkioErrorResponse<T> {
    private String errorCode;
    private String errorType;
    private String errorMessage;

    public TalkioErrorResponse(){

    }

    public TalkioErrorResponse(String errorCode, String errorType, String errorMessage) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public TalkioErrorResponse(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
