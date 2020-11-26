package com.zzp.workflow.workflow.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:21 PM
 * @description: com.zzp.workflow.workflow.pojo
 * @version: v1.0
 */
@Data
public class StartProcessResponse implements Serializable {

    private String processInstanceId;

    private String processDefId;

    private String processDefKey;
}
