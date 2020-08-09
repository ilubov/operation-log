package com.i.lubov.util;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class SpringContextUtils implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext = null;

    public SpringContextUtils() {}

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    private static void clearHolder() {
        applicationContext = null;
    }

    public void destroy() {
        clearHolder();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext != null) {
            log.warn("SpringContextUtils中的ApplicationContext被覆盖, 原有ApplicationContext为: {}", SpringContextUtils.applicationContext);
        }
        SpringContextUtils.applicationContext = applicationContext;
    }
}
