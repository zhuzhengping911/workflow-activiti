package com.zzp.workflow.workflow.delgate;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhuzhengping
 * on 2018/9/29.
 */
public class MyDelgate implements JavaDelegate {

    @Autowired
    private RuntimeService runtimeService;


    @Override
    public void execute(DelegateExecution delegateExecution) {
        runtimeService.createProcessInstanceQuery().processInstanceId("myProcess");
        System.out.println("流程已经启动");
    }
}
