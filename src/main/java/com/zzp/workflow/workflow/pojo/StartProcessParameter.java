package com.zzp.workflow.workflow.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 3:56 PM
 * @description: com.zzp.workflow.workflow.pojo
 * @version: v1.0
 */
@Data
public class StartProcessParameter implements Serializable {

    /**
     * 流程名称，必填
     */
    private String processDefKey;

    /**
     * 流程版本号
     */
    private Integer processVersion;

    /**
     * 流程变量，非必填
     */
    private List<Variable> variables;
}
