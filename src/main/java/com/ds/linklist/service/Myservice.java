package com.ds.linklist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ds.linklist.test.Employee;

import io.reactivex.Observable;

@Service
public class Myservice
{
    private static Logger log = LoggerFactory.getLogger(Myservice.class);


    @Async
    public CompletableFuture<Employee> hitAsync() throws InterruptedException
    {
        log.info(Thread.currentThread().getName());

        Employee e1 = new Employee("Himanshu", 1, "HCL");
        Employee e2 = new Employee("Mohneesh", 2, "HCL");
        Employee e3 = new Employee("Alam", 3, "HCL");
        Employee e4 = new Employee("Parkhi", 4, "HCL");
        Employee e5 = new Employee("Nagpal", 5, "HCL");
        Employee e6 = new Employee("Harry", 6, "HCL");
        Random random = new Random();
        int number = random.nextInt(5);
        Employee emp = null;
        switch (number)
        {
            case 0:
                log.info("1");
                Thread.sleep(1000);
                emp = e1;
                break;
            case 1:
                log.info("2");
                Thread.sleep(2000);
                emp = e2;
                break;
            case 2:
                log.info("4");
                Thread.sleep(4000);
                emp = e3;
                break;
            case 3:
                log.info("8");
                Thread.sleep(8000);
                emp = e4;
                break;
            case 4:
                log.info("16");
                Thread.sleep(16000);
                emp = e5;
                break;
            default:
                log.info("32");
                emp = e6;
                break;

        }

        return CompletableFuture.completedFuture(emp);
    }


    @Async
    public CompletableFuture<Stream<Employee>> getEmployeeList() throws InterruptedException
    {
       
        System.out.println("1 : " + Thread.currentThread().getName());
        Employee e1 = new Employee("Himanshu", 1, "HCL");
        Employee e2 = new Employee("Mohneesh", 2, "HCL");
        Employee e3 = new Employee("Alam", 3, "HCL");
        Employee e4 = new Employee("Parkhi", 4, "HCL");
        Employee e5 = new Employee("Nagpal", 5, "HCL");
        Employee e6 = new Employee("Harry", 6, "HCL");
        List<Employee> empList = new ArrayList<>();
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        empList.add(e4);
        empList.add(e5);
        empList.add(e6);

        System.out.println(empList);
        
        // Flux<Employee> fluxEmployee = Flux.fromIterable(empList);
        // fluxEmployee.subscribe(System.out::println);
        // System.out.println("2");
        // return fluxEmployee.delayElements(Duration.ofMillis(1000));
        Thread.sleep(4000);
        Stream<Employee> empListStream = empList.stream();
        
        CompletableFuture<Stream<Employee>> empListStreamFuture = CompletableFuture.completedFuture(empListStream);
        return empListStreamFuture;
    }


    public Observable<Employee> getReactiveEmployeeList()
    {
        System.out.println("1");
        Employee e1 = new Employee("Himanshu", 1, "HCL");
        Employee e2 = new Employee("Mohneesh", 2, "HCL");
        Employee e3 = new Employee("Alam", 3, "HCL");
        Employee e4 = new Employee("Parkhi", 4, "HCL");
        Employee e5 = new Employee("Nagpal", 5, "HCL");
        Employee e6 = new Employee("Harry", 6, "HCL");

        List<Employee> empList = new ArrayList<>();
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        empList.add(e4);
        empList.add(e5);
        empList.add(e6);

        System.out.println(empList);
        Observable<Employee> observableEmployee = Observable.fromIterable(empList);
        return observableEmployee.delaySubscription(3L, TimeUnit.MILLISECONDS);

    }
}
