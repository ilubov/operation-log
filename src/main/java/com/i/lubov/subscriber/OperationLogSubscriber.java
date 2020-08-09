package com.i.lubov.subscriber;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.i.lubov.annotation.AsyncEventSubscriber;
import com.i.lubov.comps.OperationLogEvent;
import com.i.lubov.entity.OperationLog;
import com.i.lubov.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AsyncEventSubscriber
@Component
public class OperationLogSubscriber {

    @Autowired
    private OperationLogService operationLogService;

    @Subscribe
    @AllowConcurrentEvents
    public void receiveOperationLogEvent(OperationLogEvent operationLogEvent) {
        OperationLog operationLog = BeanUtil.toBean(operationLogEvent, OperationLog.class);
        operationLogService.save(operationLog);
    }

}
