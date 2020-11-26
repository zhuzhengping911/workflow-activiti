package com.zzp.workflow.workflow.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zzp.workflow.workflow.config.CustomDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:07 PM
 * @description: com.zzp.workflow.workflow.pojo
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(of = {"statusCode", "statusDescription"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessStatus implements Serializable {

    private String statusCode;

    private String statusDescription;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date statusDate;
}
