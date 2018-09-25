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

    @Override
    public Collection<String> getAllTenants() {
        return null;
    }

    @Override
    public void setCurrentTenantId(String s) {

    }

    @Override
    public String getCurrentTenantId() {
        return null;
    }

    @Override
    public void clearCurrentTenantId() {

    }
}
