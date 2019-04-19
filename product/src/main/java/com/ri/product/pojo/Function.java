package com.ri.product.pojo;

import java.util.Arrays;
import java.util.List;

public class Function {

    private String functionName;

    private List<String> parameter;

    private List<String> parameterType;

    private List<Function> functionList;

    public String getFunctionName() {
        return functionName;
    }

    public Function setFunctionName(String functionName) {
        this.functionName = functionName;
        return this;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public Function setParameter(List<String> parameter) {
        this.parameter = parameter;
        return this;
    }

    public List<String> getParameterType() {
        return parameterType;
    }

    public Function setParameterType(List<String> parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public Function setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
        return this;
    }

    @Override
    public String toString() {
        return "Function{" +
                "functionName='" + functionName + '\'' +
                ", parameter=" + Arrays.toString(parameter.toArray()) +
                ", parameterType=" + Arrays.toString(parameterType.toArray()) +
                ", functionList=" + Arrays.toString(functionList.toArray()) +
                '}';
    }
}
