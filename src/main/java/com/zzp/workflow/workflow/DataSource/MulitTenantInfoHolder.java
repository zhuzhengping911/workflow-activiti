package com.zzp.workflow.workflow.DataSource;

import org.activiti.engine.impl.cfg.multitenant.TenantInfoHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhengping.Zhu
 * on 2018/9/25.
 * 此类实现TenantInfoHolder，也是多租户的关键
 * tenantId代表租户id，也是用来鉴别数据库的key
 * 通常与ThreadLocal合用，每一次的流程指定目前的租户
 * allTenantId是目前程序中所有的租户，可用来校验添加等等
 *
 */
@Component
public class MulitTenantInfoHolder implements TenantInfoHolder {

    public static List<String> allTenantId = new ArrayList<>();

    private ThreadLocal<String> tenantId = new ThreadLocal<>();

    @Override
    public Collection<String> getAllTenants() {
        return allTenantId;
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

    /**
     * 添加租户对应的数据源
     * @param tenantId
     */
    public static void addTenant(String tenantId){
        allTenantId.add(tenantId);
    }

    /**
     * 移除租户对应的数据源
     * @param tenantId
     */
    public static void removeTenant(String tenantId){
        allTenantId.remove(tenantId);
    }

    /**
     * 是否存在租户ID
     * @param tenantId
     * @return
     */
    public static boolean exitsTenant(String tenantId){
        return allTenantId.contains(tenantId);
    }
}
