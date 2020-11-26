package com.zzp.workflow.workflow.controller;

import com.zzp.workflow.workflow.exception.NotFoundException;
import com.zzp.workflow.workflow.pojo.ResponseMessage;
import com.zzp.workflow.workflow.pojo.StartProcessParameter;
import com.zzp.workflow.workflow.pojo.StartProcessResponse;
import com.zzp.workflow.workflow.service.BusinessStatusService;
import com.zzp.workflow.workflow.util.Variables;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:02 PM
 * @description: com.zzp.workflow.workflow.controller
 * @version: v1.0
 */

@RestController
@RequestMapping(value = "/service/runtime/instances")
public class ProcessInstanceController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

//    @Autowired
//    BusinessStatusService businessStatusService;

    @PostMapping(value = "/start")
    public ResponseMessage startProcessInstance(@RequestBody StartProcessParameter parameter) {



        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().processDefinitionKey(parameter.getProcessDefKey());
        if(parameter.getProcessVersion() != null && parameter.getProcessVersion() > 0) {
            processDefinitionQuery.processDefinitionVersion(parameter.getProcessVersion());
        } else {
            processDefinitionQuery.latestVersion();
        }

        ProcessDefinition processDefinition = null;
        if(processDefinitionQuery.count() > 0) {
            processDefinition = processDefinitionQuery.singleResult();
        }

        if(processDefinition == null) {
            throw new NotFoundException("process is not exists, " + parameter);
        }

        StartProcessResponse startProcessResponse = new StartProcessResponse();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), Variables.toMap(parameter.getVariables()));
        startProcessResponse.setProcessDefId(processInstance.getProcessDefinitionId());
        startProcessResponse.setProcessDefKey(processInstance.getProcessDefinitionKey());
        startProcessResponse.setProcessInstanceId(processInstance.getId());

        ResponseMessage responseMessage = ResponseMessage.ok("流程启动成功", startProcessResponse);
        return responseMessage;
    }
}
