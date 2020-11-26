package com.zzp.workflow.workflow.DataSource.domin;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:30 PM
 * @description: com.zzp.workflow.workflow.DataSource
 * @version: v1.0
 */
@Data
public class DataSourceEntity implements Serializable {

    private String dataSourceId;

    @NotBlank
    private String dataSourceType;

    @NotBlank
    private String dataSourceName;

    @NotBlank
    private String dataSourceTenantId;

    private String dataSourceClientId;

    private String dataSourceProductId;

    @NotBlank
    private String dataSourceUrl;

    @NotBlank
    private String dataSourceUserName;

    private String dataSourcePassword;

    private int dataSourceEnable;

    private Date dataSourceCreateDate;

    private Date dataSourceUpdateDate;
}
