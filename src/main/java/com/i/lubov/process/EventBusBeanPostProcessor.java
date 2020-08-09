package com.i.lubov.process;

import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;

import java.util.List;

import com.i.lubov.annotation.AsyncEventSubscriber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class EventBusBeanPostProcessor implements BeanPostProcessor, DisposableBean {
    @Autowired
    private AsyncEventBus asyncEventBus;

    List<Object> subscribers = Lists.newArrayList();

    public EventBusBeanPostProcessor() {}

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        AsyncEventSubscriber annotation = bean.getClass().getAnnotation(AsyncEventSubscriber.class);
        if (annotation != null) {
            this.asyncEventBus.register(bean);
            this.subscribers.add(bean);
        }

        return bean;
    }

    public void destroy() {
        this.subscribers.forEach((bean) -> this.asyncEventBus.unregister(bean));
    }
}

