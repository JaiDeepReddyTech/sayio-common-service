package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TalkioDataMissingException extends TalkioException {
    public TalkioDataMissingException() {
        super(TalkioError.Error20);
    }

    public TalkioDataMissingException(List<Object> fields) {
        super(TalkioError.Error20);
        this.fields = fields;
    }
}
