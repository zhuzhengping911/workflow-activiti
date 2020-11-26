package com.zzp.workflow.workflow.DataSource;

import com.zzp.workflow.workflow.DataSource.config.MultiTenantDataSourceConfiguration;
import com.zzp.workflow.workflow.DataSource.dao.EngineConfigMapper;
import com.zzp.workflow.workflow.DataSource.domin.DataSourceEntity;
import com.zzp.workflow.workflow.DataSource.domin.MultiDataSource;
import com.zzp.workflow.workflow.DataSource.util.SelectorDBRule;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:38 PM
 * @description: com.zzp.workflow.workflow.DataSource
 * @version: v1.0
 */
@Configuration
public class MulitRegisterTenant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MulitRegisterTenant.class);

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    @Autowired
    private MultiSchemaMultiTenantProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private MultiTenantDataSourceConfiguration datasourceConfiguration;

    @Autowired
    private EngineConfigMapper engineConfigMapper;


    /**
     * 注册租户
     *
     * @param dataSourceEntity
     */
    public void registerTenant(MultiDataSource dataSourceEntity) {
        String jdbcDriver = MYSQL_JDBC_DRIVER;
//        if (ProcessEngineConfigurationImpl.DATABASE_TYPE_ORACLE.equalsIgnoreCase(dataSourceEntity.getDriver())) {
//            jdbcDriver = "ORACLE_JDBC_DRIVE";
//        } else if (ProcessEngineConfigurationImpl.DATABASE_TYPE_MYSQL.equalsIgnoreCase(dataSourceEntity.getDriver())) {
//            jdbcDriver = MYSQL_JDBC_DRIVER;
//        }

        //创建数据源
        DataSource dataSource = datasourceConfiguration.createDataSource(dataSourceEntity.getDataName(),
                ProcessEngineConfigurationImpl.DATABASE_TYPE_MYSQL, dataSourceEntity.getUrl(),
                dataSourceEntity.getUserName(),
                dataSourceEntity.getPwd(), jdbcDriver);

        //添加路由规则映射
        SelectorDBRule.putSelectorDb(dataSourceEntity);
        //添加租户
        MulitTenantInfoHolder.addTenant(dataSourceEntity.getDataName());
        //引擎添加租户
        processEngineConfiguration.registerTenant(dataSourceEntity.getDataName(), dataSource);
    }

    /**
     * 禁用租户
     *
     * @param dataSourceName
     */
    public void removeTenant(String dataSourceName) {
        try {
            MultiDataSource dataSourceEntity = engineConfigMapper.findByDataSourceName(dataSourceName,null);
            SelectorDBRule.deleteSelectorDb(dataSourceEntity);
        } catch (Exception e) {
            LOGGER.error("Data Source Name (" + dataSourceName + ") Failed to Get Data Source");
        }
        MulitTenantInfoHolder.removeTenant(dataSourceName);
    }

    /**
     * 启用租户
     *
     * @param dataSourceName
     */
    public void enableTenant(String dataSourceName) {
        try {
            MultiDataSource dataSourceEntity = engineConfigMapper.findByDataSourceName(dataSourceName,null);
            SelectorDBRule.putSelectorDb(dataSourceEntity);
        } catch (Exception e) {
            LOGGER.error("Data Source Name (" + dataSourceName + ") Failed to Get Data Source");
        }
        MulitTenantInfoHolder.addTenant(dataSourceName);
    }
}
