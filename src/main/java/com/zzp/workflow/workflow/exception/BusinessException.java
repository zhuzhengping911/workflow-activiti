package com.zzp.workflow.workflow.exception;

import com.zzp.workflow.workflow.enums.ResponseCode;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:28 PM
 * @description: com.zzp.workflow.workflow.exception
 * @version: v1.0
 */
public class BusinessException extends RuntimeException{


    private int status;

    public BusinessException(String message){
        this(message, ResponseCode.PARAMETER_ERROR_CODE.getCode());
    }

    public BusinessException(String message,int status){
        super(message);
        this.status = status;
    }

    public BusinessException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
