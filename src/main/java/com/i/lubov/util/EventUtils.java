package com.i.lubov.util;

import com.google.common.eventbus.AsyncEventBus;

public final class EventUtils {
    public EventUtils() {}

    public static void postEvent(Object event) {
        SpringContextUtils.getBean(AsyncEventBus.class).post(event);
    }
}
