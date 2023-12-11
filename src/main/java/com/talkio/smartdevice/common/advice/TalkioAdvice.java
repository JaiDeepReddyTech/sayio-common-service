package com.talkio.smartdevice.common.advice;

import com.talkio.smartdevice.common.exception.*;
import com.talkio.smartdevice.common.model.response.ApiResponse;
import com.talkio.smartdevice.common.enums.TalkioError;
import com.talkio.smartdevice.common.model.TalkioResponseStatus;
import com.talkio.smartdevice.common.model.response.TalkioErrorResponse;
import com.talkio.smartdevice.common.model.response.TalkioResponse;
import com.talkio.smartdevice.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@ControllerAdvice
public class TalkioAdvice {
    protected static final Logger logger = LoggerFactory.getLogger(TalkioAdvice.class);

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    MessageSource messageSource;

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, TalkioResponseStatus talkioResponseStatus, HttpHeaders headers, HttpServletRequest request, Locale locale) {
        logger.error("Error in request {} , status : {} , Exception : {} , Message: {}", request.getRequestURI(), talkioResponseStatus.getStatus().value(), ex.getClass().getName(), ex.getMessage());
        TalkioResponse<Object> errorResponse = new TalkioResponse<Object>();
        List<TalkioErrorResponse> errors = new ArrayList<TalkioErrorResponse>();
        errors.add(responseUtil.getTalkioErrorResponse(talkioResponseStatus, null, locale));
        errorResponse.setErrors(errors);
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(talkioResponseStatus.getStatus().value());
        errorResponse.setHasError(true);
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<Object>(errorResponse, headers, talkioResponseStatus.getStatus());
    }

    protected ResponseEntity<Object> handleExceptionInternal(TalkioException ex, HttpHeaders headers, HttpServletRequest request, Locale locale) {
        logger.error("Error in request {} , status : {} , Exception : {} , Message: {}", request.getRequestURI(), ex.getTalkioResponseStatus().getStatus().value(), ex.getClass().getName(), ex.getMessage());
        TalkioResponse<Object> errorResponse = new TalkioResponse<Object>();
        List<TalkioErrorResponse> errors = new ArrayList<TalkioErrorResponse>();
        errors.add(responseUtil.getTalkioErrorResponse(ex.getTalkioResponseStatus(), ex.getFields().toArray(), locale));
        errorResponse.setErrors(errors);
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(ex.getTalkioResponseStatus().getStatus().value());
        errorResponse.setHasError(true);
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<Object>(errorResponse, headers, ex.getTalkioResponseStatus().getStatus());
    }

    protected ResponseEntity<Object> handleExceptionInternal(TalkioValidationException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request, Locale locale) {
        logger.error("Error in request {} , status : {} , Exception : {} ", request.getRequestURI(), status.value(), ex.getClass().getName());
        TalkioResponse<Object> errorResponse = new TalkioResponse<Object>();
        List<TalkioErrorResponse> errors = new ArrayList<TalkioErrorResponse>();
        for (TalkioException talkioException : ex.getValidationException()) {
            errors.add(responseUtil.getTalkioErrorResponse(talkioException.getTalkioResponseStatus(), talkioException.getFields().toArray(), locale));
        }
        errorResponse.setErrors(errors);
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(status.value());
        errorResponse.setHasError(true);
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<Object>(errorResponse, headers, status);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        return this.handleExceptionInternal(ex, TalkioError.Error1, headers, request, locale);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }
        return this.handleExceptionInternal(ex, TalkioError.Error2, headers, request, locale);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error3, headers, request, locale);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error4, headers, request, locale);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error5, headers, request, locale);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error6, headers, request, locale);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error7, headers, request, locale);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error8, headers, request, locale);
    }

    /* Custom Exceptions */
    @ExceptionHandler(TalkioValidationException.class)
    protected ResponseEntity<Object> handleTalkioValidationException(TalkioValidationException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, HttpStatus.BAD_REQUEST, request, locale);
    }

    @ExceptionHandler(TalkioDuplicateException.class)
    public ResponseEntity<Object> handleTalkioDuplicateException(final TalkioDuplicateException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioNoRecordFoundException.class)
    public ResponseEntity<Object> handleTalkioException(final TalkioNoRecordFoundException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }


    @ExceptionHandler(TalkioInternalServerException.class)
    public ResponseEntity<Object> handleTalkioInternalServerException(final TalkioInternalServerException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioInvalidParameterException.class)
    public ResponseEntity<Object> handleTalkioInvalidParameterException(final TalkioInvalidParameterException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioLockedException.class)
    public ResponseEntity<Object> handleTalkioLockedException(final TalkioLockedException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioReferenceExistException.class)
    public ResponseEntity<Object> handleTalkioReferenceExistException(final TalkioReferenceExistException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioUnProcessableEntityException.class)
    public ResponseEntity<Object> handleTalkioUnProcessableEntityException(final TalkioUnProcessableEntityException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    @ExceptionHandler(TalkioException.class)
    public ResponseEntity<Object> handleTalkioException(final TalkioException ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, headers, request, locale);
    }

    /* Parent Exception*/
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request, Locale locale) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleExceptionInternal(ex, TalkioError.Error0, headers, request, locale);
    }

    protected ApiResponse<Object> getApiResponseForError(List<Error> errors) {
        ApiResponse<Object> apiResponse = new ApiResponse<Object>();
        apiResponse.setErrors(errors);
        return apiResponse;
    }
}
