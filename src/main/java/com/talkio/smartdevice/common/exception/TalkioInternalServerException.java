package com.talkio.smartdevice.common.exception;



import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioInternalServerException extends TalkioException {

    public TalkioInternalServerException() {
        super(TalkioError.Error22);
    }

    public TalkioInternalServerException(List<Object> fields) {
        super(TalkioError.Error22);
        this.fields = fields;
    }
}
