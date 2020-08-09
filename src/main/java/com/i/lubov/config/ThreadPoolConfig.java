package com.i.lubov.config;

import com.google.common.eventbus.AsyncEventBus;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {
    @Value("${lubov.thread-pool.core-pool-size:1}")
    private int threadPoolCorePoolSize;
    @Value("${lubov.thread-pool.max-pool-size:20}")
    private int threadPoolMaxPoolSize;
    @Value("${lubov.thread-pool.queue-capacity:100}")
    private int threadPoolQueueCapacity;
    @Value("${lubov.thread-pool.keep-alive-seconds:60}")
    private int threadPoolKeepAliveSeconds;

    public ThreadPoolConfig() {}

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("LubovExecutor-");
        threadPoolTaskExecutor.setCorePoolSize(this.threadPoolCorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(this.threadPoolMaxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(this.threadPoolQueueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(this.threadPoolKeepAliveSeconds);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new AbortPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean
    public AsyncEventBus asyncEventBus() {
        AsyncEventBus asyncEventBus = new AsyncEventBus("LubovAsyncEventBus", this.threadPoolTaskExecutor());
        return asyncEventBus;
    }
}

