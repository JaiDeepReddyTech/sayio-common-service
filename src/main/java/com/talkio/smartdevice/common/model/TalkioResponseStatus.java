package com.talkio.smartdevice.common.model;

import org.springframework.http.HttpStatus;

public interface TalkioResponseStatus {
    String getStatusCode();
    String getStatusTypeCode();
    String getStatusValue();
    HttpStatus getStatus();
}
