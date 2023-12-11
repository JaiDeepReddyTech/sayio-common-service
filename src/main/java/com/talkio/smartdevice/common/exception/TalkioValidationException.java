package com.talkio.smartdevice.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Ahmer Munir Khan on 05-02-2015.
 */
@Getter
@Setter
public class TalkioValidationException extends TalkioException {

    protected List<TalkioException> validationException;

    public TalkioValidationException(){}

    public TalkioValidationException(List<TalkioException> validationException){
        this.validationException= validationException;
    }

    public void addValidationException(TalkioException talkioException){
        this.validationException.add(talkioException);
    }
}
