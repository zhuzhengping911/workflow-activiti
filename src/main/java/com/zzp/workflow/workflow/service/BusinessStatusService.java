package com.zzp.workflow.workflow.service;

import com.zzp.workflow.workflow.pojo.BusinessStatus;

import java.util.List;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:05 PM
 * @description: com.zzp.workflow.workflow.service
 * @version: v1.0
 */
public interface BusinessStatusService {

    /**
     * @param  procInstId 流程实例id
     * @return List<BusinessStatus>
     * @throws
     * @Title: queryBusinessStatus
     * @Description: 查询业务状态数据
     */
    List<BusinessStatus> queryBusinessStatus(String procInstId);
}
