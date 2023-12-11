package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

/**
 * Created by Ahmer Munir Khan on 10-04-2015.
 */
public class TalkioLockedException extends TalkioException {
    public TalkioLockedException() {
        super(TalkioError.Error24);
    }

    public TalkioLockedException(List<Object> fields) {
        super(TalkioError.Error24);
        this.fields = fields;
    }
}
