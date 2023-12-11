package com.talkio.smartdevice.common.exception;



import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioNoRecordFoundException extends TalkioException {
    public TalkioNoRecordFoundException() {
        super(TalkioError.Error29);
    }

    public TalkioNoRecordFoundException(List<Object> fields) {
        super(TalkioError.Error29);
        this.fields = fields;
    }
}
