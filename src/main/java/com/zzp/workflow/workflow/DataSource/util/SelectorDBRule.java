package com.zzp.workflow.workflow.DataSource.util;

import com.zzp.workflow.workflow.DataSource.domin.DataSourceEntity;
import com.zzp.workflow.workflow.DataSource.domin.MultiDataSource;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:44 PM
 * @description: com.zzp.workflow.workflow.DataSource
 * @version: v1.0
 */
public class SelectorDBRule {

    private static Map<String, String> selectorDBMaps = new HashMap<>();

    /**
     * tenantId，clientId,productId设置对应的数据源
     *
     * @param dataSourceEntity
     */
    public static void putSelectorDb(MultiDataSource dataSourceEntity) {
        String selectorKey = "";

        if (!StringUtils.isEmpty(dataSourceEntity.getDataName())) {
            selectorKey = dataSourceEntity.getDataName();
        }

        if (!StringUtils.isEmpty(dataSourceEntity.getCurrentId())) {
            selectorKey = selectorKey + "_" + dataSourceEntity.getCurrentId();
        }

        selectorDBMaps.put(selectorKey, dataSourceEntity.getDataName());
    }

    /**
     * 根据参数获取对应的数据源
     *
     * @param tenantId
     * @param clientId
     * @param productId
     */
    public static String getSelectorDb(String tenantId, String clientId, String productId) {
        String selectorKey = "";

        if (!StringUtils.isEmpty(tenantId)) {
            selectorKey = tenantId;
        }

        if (!StringUtils.isEmpty(clientId)) {
            selectorKey = selectorKey + "_" + clientId;
        }

        if (!StringUtils.isEmpty(productId)) {
            selectorKey = selectorKey + "_" + productId;
        }

        return selectorDBMaps.get(selectorKey);
    }

    public static void deleteSelectorDb(MultiDataSource dataSourceEntity) {
        String selectorKey = "";

        if (!StringUtils.isEmpty(dataSourceEntity.getDataName())) {
            selectorKey = dataSourceEntity.getDataName();
        }

        if (!StringUtils.isEmpty(dataSourceEntity.getCurrentId())) {
            selectorKey = selectorKey + "_" + dataSourceEntity.getCurrentId();
        }

        selectorDBMaps.remove(selectorKey);
    }

}
