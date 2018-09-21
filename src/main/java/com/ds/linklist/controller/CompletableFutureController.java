package com.ds.linklist.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.linklist.service.Myservice;
import com.ds.linklist.test.Employee;

@RestController
@RequestMapping("/")
public class CompletableFutureController
{
    @Autowired
    Myservice myservice;

    private static Logger log = LoggerFactory.getLogger(CompletableFutureController.class);


    @RequestMapping("/hit")
    public String requestUrl() throws InterruptedException, ExecutionException
    {

        CompletableFuture<Employee> result1 = myservice.hitAsync();
        CompletableFuture<Employee> result2 = myservice.hitAsync();
        // CompletableFuture<Employee> result3 = myservice.hitAsync();
        // CompletableFuture<Employee> result4 = myservice.hitAsync();

        // CompletableFuture.allOf(result1, result2, result3, result4).join();/*
        log.info("result 1 : " + result1);
        // log.info("result 2 : " + result2.get());
        // log.info("result 3 : " + result3.get());
        // log.info("result 4 : " + result4.get());

        // List<Employee> listEmployee = new ArrayList<>();
        // listEmployee.add(result1.get());
        // listEmployee.add(result2.get());
        // listEmployee.add(result3.get());
        // listEmployee.add(result4.get());
        // log.info("hello");
        return "Result 1 : " + result1 + "\n Result 2 : " + result2;
    }

}
