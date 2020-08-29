/*
 * Copyright (c) 2020 ilubov
 * https://www.ilubov.cn
 * All rights reserved.
 */
package com.i.lubov.util;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class EventUtils {

    @Autowired
    private AsyncEventBus asyncEventBus;

    private static EventUtils instance = null;

    public EventUtils() {
        instance = this;
    }

    public static void postEvent(Object event) {
        instance.asyncEventBus.post(event);
    }
}
