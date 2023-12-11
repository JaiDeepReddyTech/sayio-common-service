package com.talkio.smartdevice.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalkioResponse<T> {
    private String traceId;
    private Long timestamp;
    private Boolean hasError = false;
    private Integer status;
    private String path;
    private List<TalkioErrorResponse> errors;
    private String message;
    private T data;
}
