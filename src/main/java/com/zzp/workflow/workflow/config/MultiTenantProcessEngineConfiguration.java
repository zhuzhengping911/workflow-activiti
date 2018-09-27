package com.zzp.workflow.workflow.config;

import com.zzp.workflow.workflow.DataSource.MulitTenantInfoHolder;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by zhengping.Zhu
 * on 2018/9/25.
 * 多租户引擎config
 */
@Configuration
public class MultiTenantProcessEngineConfiguration {


    @Autowired
    private MulitTenantInfoHolder mulitTenantInfoHolder;

    /**
     * 多租户引擎的创建
     * 此处关键是传入mulitTenantInfoHolder
     * 不可以直接返回Processinge
     * 因为需要MultiSchemaMultiTenantProcessEngineConfiguration来添加数据库中已有的租户
     * 直接创建processinge的话，MultiSchemaMultiTenantProcessEngineConfiguration会不存在，后面无法添加
     * @return
     */
    @Bean
    public MultiSchemaMultiTenantProcessEngineConfiguration buildMultiSchemaMultiTenantProcessEngineConfiguration(){


        MultiSchemaMultiTenantProcessEngineConfiguration multiTenantProcessEngineConfiguration =
                new MultiSchemaMultiTenantProcessEngineConfiguration(mulitTenantInfoHolder);
        multiTenantProcessEngineConfiguration.setDatabaseType(MultiSchemaMultiTenantProcessEngineConfiguration.DATABASE_TYPE_MYSQL);
        multiTenantProcessEngineConfiguration.setDatabaseSchemaUpdate(MultiSchemaMultiTenantProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return multiTenantProcessEngineConfiguration;
    }

    /**
     * 返回processinge
     * @return
     */
    @Primary
    @Bean
    public ProcessEngine buildProcessinge(){
        return buildMultiSchemaMultiTenantProcessEngineConfiguration().buildProcessEngine();
    }


    /**
     * 运行service，启动流程使用
     * @return
     */
    @Bean
    public RuntimeService runtimeService() {
        return buildProcessinge().getRuntimeService();
    }

    /**
     * 任务service，操作节点使用
     * @return
     */
    @Bean
    public TaskService taskService() {
        return buildProcessinge().getTaskService();
    }

    /**
     * 历史service
     * @return
     */
    @Bean
    public HistoryService historyService() {
        return buildProcessinge().getHistoryService();
    }

    /**
     * 表单service，操作userTask内表单数据
     * @return
     */
    @Bean
    public FormService formService() {
        return buildProcessinge().getFormService();
    }

    /**
     *
     * @return
     */
    @Bean
    public IdentityService identityService() {
        return buildProcessinge().getIdentityService();
    }

    /**
     *
     * @return
     */
    @Bean
    public ManagementService managementService() {
        return buildProcessinge().getManagementService();
    }
}
