package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Ahmer Munir Khan on 10-04-2015.
 */
@Setter
@Getter
public class TalkioDuplicateException extends TalkioException {

    public TalkioDuplicateException() {
        super(TalkioError.Error21);
    }

    public TalkioDuplicateException(List<Object> fields) {
        super(TalkioError.Error21);
        this.fields = fields;
    }
}

