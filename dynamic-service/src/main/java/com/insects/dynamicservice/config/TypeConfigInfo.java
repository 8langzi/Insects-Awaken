package com.insects.dynamicservice.config;

import com.insects.dynamicservice.service.AbstractServiceContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class TypeConfigInfo implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean("dynamicServiceMethod")
    public Map<String, List<Mapper>> getTypeMapper() {
        // 拿到所有 AbstractServiceContent 的子类,String是类名 AbstractServiceContent是这个类的信息
        Map<String, AbstractServiceContent> serviceContent = applicationContext.getBeansOfType(AbstractServiceContent.class);
        // 匹配所有的集合
        List<Mapper> allTypeMathList = new ArrayList<>();
        // 根据类型匹配的集合
        List<TypeMapper> typeMapperList = new ArrayList<>();
        // 对获取到的数据进行初步处理
        serviceContent.entrySet().stream().forEach(o -> {
            Mapper info = new Mapper();
            // 获取此类名
            String className = o.getKey();
            // 获取此类信息
            AbstractServiceContent abstractServiceContent = o.getValue();
            // 获取此类被注解的方法
            Method[] methods = abstractServiceContent.getClass().getDeclaredMethods();
            Arrays.stream(methods).forEach(k -> {
                // 根据 Type 获取方法的注解
                Type annotation = k.getAnnotation(Type.class);
                // 如果此方法存在 Type 注解,进行存储
                if (!ObjectUtils.isEmpty(annotation)) {
                    // 获取注解内容
                    String type = annotation.type();
                    // 如果是 统配符 * 修饰过的，将该信息放入 allTypeMathList 中， 否则放入 typeMapperList 中
                    if (StringUtils.equals(type, "*")) {
                        info.setClassName(className);
                        info.setMethod(k);
                        allTypeMathList.add(info);
                    } else {
                        TypeMapper typeMapper = new TypeMapper();
                        typeMapper.setClassName(className);
                        typeMapper.setMethod(k);
                        typeMapper.setType(type);
                        typeMapperList.add(typeMapper);
                    }
                }

            });
        });

        // 将 List<TypeMapper>  转为 Map<String, List<Mapper>>
        Map<String, List<Mapper>> mapperMap = typeMapperList.stream().flatMap(o -> {
            // 获取类型
            String type = o.getType();
            // 获取类名
            String className = o.getClassName();
            // 取方法
            Method method = o.getMethod();
            // 将类型拆开
            String[] types = type.split(",");

            // 对拆开的类型根据 type 进行区分，并将所有的type转换成 type,List<Mapper>的形式
            Map<String, List<Mapper>> methodMapper = Stream.of(types).flatMap(k -> {
                Map<String, Mapper> map = new HashMap<>();
                Mapper mapper = new Mapper();
                mapper.setMethod(method);
                mapper.setClassName(className);
                map.put(k, mapper);
                return map.entrySet().stream();
                // 收集的时候进行第二次处理，将每个Map都转为List，type重复时 进行合并
            }).collect(Collectors.toMap(k -> k.getKey(), k -> {
                List<Mapper> mappers = new ArrayList<>();
                mappers.add(k.getValue());
                return mappers;
            }, (v1, v2) -> {
                v1.addAll(v2);
                return v1;
            }));
            return methodMapper.entrySet().stream();
        }).collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue(), (v1, v2) -> {
            v1.addAll(v2);
            return v1;
        }));
        return mapperMap;
    }
}