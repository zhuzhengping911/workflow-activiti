package com.zzp.workflow.workflow.controller;

import com.zzp.workflow.workflow.delgate.MyDelgate;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhuzhengping
 * on 2018/10/2.
 */
@RestController("receive")
public class ReceiveController {

    @Autowired
    private RuntimeService runtimeService;


    @RequestMapping("/receive")
    public String receiverTask(@RequestBody Map requestMap){

        for (Object o : requestMap.keySet()) {
            System.out.println(o);
        }
//        runtimeService.signalEventReceived();
        return "OK";

    }

    @GetMapping("/startInstance")
    public String startInstance(@RequestBody Map requestMap){
        return "流程启动成功";
    }
}
