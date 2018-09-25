package com.zzp.workflow.workflow.config;

import com.zzp.workflow.workflow.DataSource.MulitTenantInfoHolder;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengping.Zhu
 * on 2018/9/25.
 * 多租户引擎config
 */
@Configuration
@ConfigurationProperties(prefix = "")
public class MultiTenantProcessEngineConfiguration {

    @Autowired
    private MulitTenantInfoHolder mulitTenantInfoHolder;

    @Bean
    public ProcessEngine buildMultiSchemaMultiTenantProcessEngineConfiguration(){


        MultiSchemaMultiTenantProcessEngineConfiguration multiTenantProcessEngineConfiguration =
                new MultiSchemaMultiTenantProcessEngineConfiguration(mulitTenantInfoHolder);
        multiTenantProcessEngineConfiguration.setDatabaseType(MultiSchemaMultiTenantProcessEngineConfiguration.DATABASE_TYPE_H2);
        multiTenantProcessEngineConfiguration.setDatabaseSchemaUpdate(MultiSchemaMultiTenantProcessEngineConfiguration.DB_SCHEMA_UPDATE_DROP_CREATE);
        return multiTenantProcessEngineConfiguration.buildProcessEngine();
    }

    /**
     * 运行service，启动流程使用
     * @return
     */
    @Bean
    public RuntimeService runtimeService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getRuntimeService();
    }

    /**
     * 任务service，操作节点使用
     * @return
     */
    @Bean
    public TaskService taskService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getTaskService();
    }

    /**
     * 历史service
     * @return
     */
    @Bean
    public HistoryService historyService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getHistoryService();
    }

    /**
     * 表单service，操作userTask内表单数据
     * @return
     */
    @Bean
    public FormService formService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getFormService();
    }

    /**
     *
     * @return
     */
    @Bean
    public IdentityService identityService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getIdentityService();
    }

    /**
     *
     * @return
     */
    @Bean
    public ManagementService managementService() {
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().getManagementService();
    }
}
