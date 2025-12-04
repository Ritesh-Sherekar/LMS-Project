package com.example.LMS_SchedularService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);       // minimum threads
        executor.setMaxPoolSize(10);       // maximum threads
        executor.setQueueCapacity(50);     // queue size before new threads are created
        executor.setThreadNamePrefix("AsyncThread-");

        executor.initialize();
        return executor;
    }
}
