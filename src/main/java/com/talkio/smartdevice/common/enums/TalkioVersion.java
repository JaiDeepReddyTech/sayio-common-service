package com.talkio.smartdevice.common.enums;

import lombok.Getter;

@Getter
public enum TalkioVersion {
    V1("v1");

    private String versionCode;

    TalkioVersion(String versionCode) {
        this.versionCode = versionCode;
    }
}
