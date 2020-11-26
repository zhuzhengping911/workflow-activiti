package com.zzp.workflow.workflow.filter;

import com.zzp.workflow.workflow.DataSource.*;
import com.zzp.workflow.workflow.DataSource.dao.EngineConfigMapper;
import com.zzp.workflow.workflow.DataSource.domin.DataSourceEntity;
import com.zzp.workflow.workflow.DataSource.domin.MultiDataSource;
import com.zzp.workflow.workflow.DataSource.util.SelectorDBRule;
import com.zzp.workflow.workflow.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:20 PM
 * @description: com.zzp.workflow.workflow.filter
 * @version: v1.0
 */
@Component
public class MultiTenantFilter extends AbstractFilter {

    private static final Logger LOG = LoggerFactory.getLogger(MultiTenantFilter.class);

    @Autowired
    private MulitTenantInfoHolder mulitTenantInfoHolder;

    @Autowired
    private EngineConfigMapper engineConfigMapper;

    @Lazy
    @Autowired
    private MulitRegisterTenant mulitRegisterTenant;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(isExclusion(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return ;
        }

        String tenantId = httpServletRequest.getHeader("Tenant");
        String clientId = httpServletRequest.getHeader("clientId");
        String productId = httpServletRequest.getHeader("productId");

        //根据tenantId,clientId,productId选择数据源
        String tenantName = SelectorDBRule.getSelectorDb(tenantId, clientId, productId);
        if (StringUtils.isBlank(tenantName)) {
            try {
                MultiDataSource dataSourceEntity = engineConfigMapper.findByDataSourceName(tenantId, clientId);
                if (dataSourceEntity == null) {
                    chain.doFilter(request, response);
                    return;
                }
                mulitRegisterTenant.registerTenant(dataSourceEntity);
                mulitTenantInfoHolder.setCurrentTenantId(SelectorDBRule.getSelectorDb(tenantId, clientId, productId));
            } catch (Exception e) {
                LOG.error("the data source update failed...", e);
                LOG.error("Tenant【" + tenantName + "】do not exist please confirm Tenant");
                chain.doFilter(request, response);
            }
        } else if (MulitTenantInfoHolder.exitsTenant(tenantName)) {
            mulitTenantInfoHolder.setCurrentTenantId(tenantName);
        } else {
            throw new NotFoundException(tenantId + "+" + clientId + "+" + productId + "does not exist for data source");
        }
        chain.doFilter(request, response);
        mulitTenantInfoHolder.clearCurrentTenantId();
    }

    @Override
    public void destroy() {
        mulitTenantInfoHolder.clearCurrentTenantId();
    }
}
