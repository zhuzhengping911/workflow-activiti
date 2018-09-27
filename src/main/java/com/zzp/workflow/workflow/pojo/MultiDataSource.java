package com.zzp.workflow.workflow.pojo;

import lombok.Data;

/**
 * Created by zhengping.Zhu
 * on 2018/9/27.
 * 租户实例
 */
@Data
public class MultiDataSource {

    public String userName;

    public String pwd;

    public String driver;

    public String url;

    public String currentId;

    public int enabled;

}
