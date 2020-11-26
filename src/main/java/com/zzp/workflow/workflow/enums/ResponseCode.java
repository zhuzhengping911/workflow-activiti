package com.zzp.workflow.workflow.enums;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:18 PM
 * @description: com.zzp.workflow.workflow.enums
 * @version: v1.0
 */
public enum ResponseCode {

    SUCCESS_CODE(2000),
    PARAMETER_ERROR_CODE(1001),

    FOUND_ERROR_CODE(4004),
    SERVER_ERROR_CODE(5000);

    private int code;

    public int getCode() {
        return code;
    }

    ResponseCode(int code) {
        this.code = code;
    }
}
