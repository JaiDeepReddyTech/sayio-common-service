package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioInvalidParameterException extends TalkioException {

    public TalkioInvalidParameterException() {
        super(TalkioError.Error23);
    }

    public TalkioInvalidParameterException(List<Object> fields) {
        super(TalkioError.Error23);
        this.fields = fields;
    }
}
