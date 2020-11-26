package com.zzp.workflow.workflow.exception;

import com.zzp.workflow.workflow.enums.ResponseCode;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:25 PM
 * @description: com.zzp.workflow.workflow
 * @version: v1.0
 */
public class NotFoundException extends BusinessException{

    public NotFoundException(String message){
        super(message, ResponseCode.FOUND_ERROR_CODE.getCode());
    }
}
