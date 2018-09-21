package com.ds.linklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.linklist.service.Myservice;
import com.ds.linklist.test.Employee;

import io.reactivex.Observable;

@RestController
@RequestMapping("/reactive")
public class ReactiveController
{

    @Autowired
    private Myservice myservice;


    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<Employee> getAllEmployee()
    {
        return myservice.getReactiveEmployeeList().doAfterNext(e -> {
            Thread.sleep(500L);
            System.out.println(e);
        });
        //return null;
    }
}
