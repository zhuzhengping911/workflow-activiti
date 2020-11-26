package com.zzp.workflow.workflow.util;

import com.zzp.workflow.workflow.pojo.Variable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 4:23 PM
 * @description: com.zzp.workflow.workflow.util
 * @version: v1.0
 */
public class Variables {

    List<Variable> variables;

    private Variables(List<Variable> variables) {
        this.variables = variables;
    }

    public static Variables newInstance(List<Variable> variables) {
        return new Variables(variables);
    }

    public Map<String, Object> toMap() {
        return variables == null ? null : variables.stream().collect(Collectors.toMap(Variable::getName, Variable::getValue));
    }

    public static Map<String, Object> toMap(List<Variable> variables) {
        return Variables.newInstance(variables).toMap();
    }
}
