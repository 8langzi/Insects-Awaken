package com.insects.dynamicservice.config;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class TypeMapper {

    private String className;

    private Method method;

    private String type;

}
