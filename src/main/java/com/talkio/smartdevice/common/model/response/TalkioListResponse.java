package com.talkio.smartdevice.common.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TalkioListResponse extends TalkioResponse {
    private int page;
    private int pageSize;
    private long count;
}
