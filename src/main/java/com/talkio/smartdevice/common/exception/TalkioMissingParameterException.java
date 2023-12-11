package com.talkio.smartdevice.common.exception;



import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioMissingParameterException extends TalkioException {

    public TalkioMissingParameterException() {
        super(TalkioError.Error25);
    }

    public TalkioMissingParameterException(List<Object> fields) {
        super(TalkioError.Error25);
        this.fields = fields;
    }
}
