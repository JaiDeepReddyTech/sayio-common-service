package com.talkio.smartdevice.common.exception;



import com.talkio.smartdevice.common.enums.TalkioError;

import java.util.List;

/**
 * Created by Ahmer Munir Khan on 10-04-2015.
 */
public class TalkioReferenceExistException extends TalkioException {
    public TalkioReferenceExistException() {
        super(TalkioError.Error26);
    }

    public TalkioReferenceExistException(List<Object> fields) {
        super(TalkioError.Error26);
        this.fields = fields;
    }
}
