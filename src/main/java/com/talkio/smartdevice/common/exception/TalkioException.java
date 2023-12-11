package com.talkio.smartdevice.common.exception;


import com.talkio.smartdevice.common.enums.TalkioError;
import com.talkio.smartdevice.common.model.TalkioResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmer Munir Khan on 05-02-2015.
 */
@Getter
@Setter
public class TalkioException extends Exception {
    protected TalkioResponseStatus talkioResponseStatus;
    protected List<Object> fields=new ArrayList<>();

    public TalkioException() {
        talkioResponseStatus = TalkioError.Error0;
    }

    public TalkioException(TalkioResponseStatus talkioResponseStatus) {
        this.talkioResponseStatus = talkioResponseStatus;
    }
    public TalkioException(TalkioResponseStatus talkioResponseStatus, List<Object> fields) {
        this.talkioResponseStatus = talkioResponseStatus;
        this.fields=fields;
    }
}
