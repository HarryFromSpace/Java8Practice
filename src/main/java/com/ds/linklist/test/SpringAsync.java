package com.ds.linklist.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SpringAsync
{
    private static Logger log = LoggerFactory.getLogger(SpringAsync.class);


    @Async
    public void asyncMethod() throws InterruptedException
    {
        log.info("inside async method: " + Thread.currentThread().getName());
    }

}
