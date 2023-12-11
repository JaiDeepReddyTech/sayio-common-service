package com.talkio.smartdevice.common.util;

import com.talkio.smartdevice.common.constant.CommonConstants;
import com.talkio.smartdevice.common.enums.TalkioSuccess;
import com.talkio.smartdevice.common.model.TalkioResponseStatus;
import com.talkio.smartdevice.common.model.response.TalkioErrorResponse;
import com.talkio.smartdevice.common.model.response.TalkioListResponse;
import com.talkio.smartdevice.common.model.response.TalkioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ResponseUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    HttpServletRequest httpServletRequest;

    public TalkioErrorResponse getTalkioErrorResponse(TalkioResponseStatus talkioResponseStatus, Object[] args, Locale locale) {
        TalkioErrorResponse talkioErrorResponse = new TalkioErrorResponse();
        talkioErrorResponse.setErrorCode(talkioResponseStatus.getStatusTypeCode() + CommonConstants.STRING_HYPHEN + talkioResponseStatus.getStatusCode());
        talkioErrorResponse.setErrorType(talkioResponseStatus.getStatusTypeCode());
        talkioErrorResponse.setErrorMessage(messageSource.getMessage(talkioResponseStatus.getStatusValue() + CommonConstants.STRING_DOT + talkioResponseStatus.getStatusCode(), args, locale));
        return talkioErrorResponse;
    }

    public TalkioResponse getTalkioResponse(Object data, TalkioSuccess talkioSuccess, List<Object> argsList, Locale locale) {
        TalkioResponse talkioResponse = getTalkioSuccessResponse(data, talkioSuccess, argsList, locale);
        return talkioResponse;
    }

    public TalkioListResponse getTalkioResponsePagination(Object data, TalkioSuccess talkioSuccess, List<Object> argsList, Integer page, Integer pageSize, Integer count, Locale locale) {
        if (argsList == null) {
            argsList = new ArrayList<>();
        }
        TalkioListResponse talkioListResponse = new TalkioListResponse();
        talkioListResponse.setData(data);
        talkioListResponse.setTimestamp(System.currentTimeMillis());
        talkioListResponse.setHasError(false);
        talkioListResponse.setStatus(talkioSuccess.getStatus().value());
        talkioListResponse.setMessage(messageSource.getMessage(talkioSuccess.getStatusValue() + CommonConstants.STRING_DOT + talkioSuccess.getStatusCode(), argsList.toArray(), locale));
        talkioListResponse.setPath(httpServletRequest.getRequestURI());
        talkioListResponse.setPage(page);
        talkioListResponse.setPageSize(pageSize);
        talkioListResponse.setCount(count);
        return talkioListResponse;
    }

    public TalkioResponse getTalkioSuccessResponse(Object data, TalkioSuccess talkioSuccess, List<Object> argsList, Locale locale) {
        if (argsList == null) {
            argsList = new ArrayList<>();
        }
        TalkioResponse talkioResponse = new TalkioResponse();
        talkioResponse.setData(data);
        talkioResponse.setTimestamp(System.currentTimeMillis());
        talkioResponse.setHasError(false);
        talkioResponse.setStatus(talkioSuccess.getStatus().value());
        talkioResponse.setMessage(messageSource.getMessage(talkioSuccess.getStatusValue() + CommonConstants.STRING_DOT + talkioSuccess.getStatusCode(), argsList.toArray(), locale));
        talkioResponse.setPath(httpServletRequest.getRequestURI());
        return talkioResponse;
    }
}
