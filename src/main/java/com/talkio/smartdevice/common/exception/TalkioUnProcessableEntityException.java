package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

public class TalkioUnProcessableEntityException extends TalkioException {

    public TalkioUnProcessableEntityException() {
        super(TalkioError.Error27);
    }

    public TalkioUnProcessableEntityException(List<Object> fields) {
        super(TalkioError.Error27);
        this.fields = fields;
    }
}
