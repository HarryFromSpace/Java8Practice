package com.ds.linklist.controller;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.linklist.service.Myservice;
import com.ds.linklist.test.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flux")
public class FluxController
{
    @Autowired
    private Myservice myservice;


    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getEmployee() throws InterruptedException, ExecutionException
    {
        CompletableFuture<Stream<Employee>> empListStreamFuture = myservice.getEmployeeList(); //.doOnNext(e -> System.out.println(e));

        //Flux<Employee> result = Flux.fromIterable(empListStreamFuture.get().collect(Collectors.toList()));
        Flux<Employee> result = Mono.fromFuture(empListStreamFuture).flatMapMany(Flux::fromStream);
        //Flux<Employee> result = Flux.fromStream(empListStreamFuture.join());

        //result.subscribe(System.out::println);

        System.out.println("3" + Thread.currentThread().getName());
        return result.delayElements(Duration.ofMillis(500L));
    }
}
