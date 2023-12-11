package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioInvalidSizeException extends TalkioException {
    public TalkioInvalidSizeException() {
        super(TalkioError.Error28);
    }

    public TalkioInvalidSizeException(List<Object> fields) {
        super(TalkioError.Error28);
        this.fields = fields;
    }
}

