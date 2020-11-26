package com.zzp.workflow.workflow.pojo;

import com.zzp.workflow.workflow.enums.ResponseCode;

import java.io.Serializable;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:12 PM
 * @description: com.zzp.workflow.workflow.pojo
 * @version: v1.0
 */
public class ResponseMessage implements Serializable {


    public ResponseMessage() {}

    /**
     * 响应码
     */
    private int responseCode;


    /**
     * 反馈数据
     */
    private Object responseBody;

    /**
     * 反馈信息
     */
    private String responseMessage;


    protected ResponseMessage(String responseMessage) {
        this.responseCode = ResponseCode.SUCCESS_CODE.getCode();
        this.responseMessage = responseMessage;
    }

    protected ResponseMessage(String responseMessage, Object responseBody) {
        this.responseCode = ResponseCode.SUCCESS_CODE.getCode();
        this.responseBody = responseBody;
        this.responseMessage = responseMessage;
    }

    protected ResponseMessage(Object responseBody) {
        this.responseCode = ResponseCode.SUCCESS_CODE.getCode();
        this.responseBody = responseBody;
    }


    protected ResponseMessage(Object responseBody, int responseCode) {
        this(responseBody);
        this.responseCode = responseCode;
    }


    protected ResponseMessage(String responseMessage, int responseCode) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
    }

    /**
     * 成功报文
     *
     * @param responseBody
     * @param responseMessage
     * @return
     */
    public static ResponseMessage ok(String responseMessage, Object responseBody) {
        return new ResponseMessage(responseMessage, responseBody);
    }

    /**
     * 成功报文
     *
     * @param responseMessage
     * @return
     */
    public static ResponseMessage ok(String responseMessage) {
        return new ResponseMessage(responseMessage);
    }

    /**
     * 成功报文
     *
     * @param responseBody
     * @return
     */
    public static ResponseMessage ok(Object responseBody) {
        return new ResponseMessage(responseBody);
    }

    /**
     * 创建报文
     *
     * @param responseBody
     * @return
     */
    public static ResponseMessage created(Object responseBody, int responseCode) {
        return new ResponseMessage(responseBody, responseCode);
    }

    /**
     * 创建失败报文
     *
     * @param responseMessage
     * @param responseCode
     * @return
     */
    public static ResponseMessage error(String responseMessage, int responseCode) {
        return new ResponseMessage(responseMessage, responseCode);
    }
}
