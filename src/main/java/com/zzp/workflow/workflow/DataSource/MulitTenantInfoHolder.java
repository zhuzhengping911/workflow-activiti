package com.zzp.workflow.workflow.DataSource;

import org.activiti.engine.impl.cfg.multitenant.TenantInfoHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by zhengping.Zhu
 * on 2018/9/25.
 *
 */
@Component
public class MulitTenantInfoHolder implements TenantInfoHolder {

    private ThreadLocal<String> tenantId = new ThreadLocal<>();

    @Override
    public Collection<String> getAllTenants() {
        return null;
    }

    @Override
    public void setCurrentTenantId(String s) {
        tenantId.set(s);
    }

    @Override
    public String getCurrentTenantId() {
        return tenantId.get();
    }

    @Override
    public void clearCurrentTenantId() {
        tenantId.remove();
    }
}
