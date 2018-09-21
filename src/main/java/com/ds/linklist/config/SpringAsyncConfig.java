package com.ds.linklist.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@EnableAsync
@Configuration
public class SpringAsyncConfig
{
    @Bean
    public Executor asynchExecuter()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setQueueCapacity(1000);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(1200);
        executor.setThreadNamePrefix("asyncExecuter-");
        executor.initialize();
        return executor;
    }
}
