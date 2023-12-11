package com.talkio.smartdevice.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String traceId;
    private Long timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
    private List<Error> errors;
    private T data;
}