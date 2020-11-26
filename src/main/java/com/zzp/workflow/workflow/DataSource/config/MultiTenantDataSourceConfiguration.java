package com.zzp.workflow.workflow.DataSource.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:40 PM
 * @description: com.zzp.workflow.workflow.DataSource
 * @version: v1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mutiltenant.datasource")
public class MultiTenantDataSourceConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MultiTenantDataSourceConfiguration.class);

    //最大活动连接数
    @Value("${tenantInit.poolMaximumActiveConnections}")
    private int poolMaximumActiveConnections;
    //最大空闲连接数
    @Value("${tenantInit.poolMaximumActiveConnections}")
    private int poolMaximumIdleConnections;

    public DataSource createDataSource(String tenantId, String databaseType, String jdbcUrl, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
        LOG.info("Creating datasource for tenant " + tenantId + " at jdbc url " + jdbcUrl);

        if (null != jdbcUrl) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassLoader(this.getClass().getClassLoader());
            dataSource.setDriverClassName(jdbcDriver);
            dataSource.setUsername(jdbcUsername);
            dataSource.setPassword(jdbcPassword);
            dataSource.setUrl(jdbcUrl);

            dataSource.setMaxActive(poolMaximumActiveConnections);
            try {
                dataSource.setFilters("stat");
            } catch (SQLException e) {
                LOG.error("租户库数据源配置druid监控失败", e);
            }
            return dataSource;
        }

        return null;
    }
}
