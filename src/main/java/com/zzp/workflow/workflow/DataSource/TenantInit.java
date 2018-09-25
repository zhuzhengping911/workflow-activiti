package com.zzp.workflow.workflow.DataSource;

import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;

/**
 * Created by zhengping.Zhu
 * on 2018/9/25.
 * 如果在SpringBoot应用启动的时候需要执行特定的动作，可以利用CommandLineRunner。
 * 实现了CommandLineRunner接口的Component会在所有Spring Beans都初始化之后，
 * SpringApplication.run()之前执行，非常适合在应用程序启动之初进行一些数据初始化的工作。
 *
 * 这里预先获取数据库已经存在的租户信息（DB）
 */

public class TenantInit implements CommandLineRunner {

    @Autowired
    @Lazy
    private MulitTenantInfoHolder mulitTenantInfoHolder;

    @Autowired
    private MultiSchemaMultiTenantProcessEngineConfiguration multiSchemaMultiTenantProcessEngineConfiguration;

    @Override
    public void run(String... strings) throws Exception {
//        multiSchemaMultiTenantProcessEngineConfiguration.registerTenant();

    }
}
