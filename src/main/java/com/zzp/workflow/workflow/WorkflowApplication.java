package com.zzp.workflow.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/workflow")
@RestController
@SpringBootApplication
public class WorkflowApplication {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloWorld(){
        return "Hello World";
    }
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }
}
