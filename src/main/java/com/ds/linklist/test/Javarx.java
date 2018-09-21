package com.ds.linklist.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Javarx
{
    public static void main(String[] args) throws Exception
    {

        // producer
        Observable<String> observable = Observable.just("Himanshu", "Mohneesh", "Alam", "Parkhi", "Shahjade", "Nagpal");

        // consumer
        Consumer<? super String> consumer = System.out::println;

        //--------------------------------------------------------------------------------------
        consumer.accept("HCL");
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        // Link
        observable.map(o -> o.toUpperCase()).subscribe(consumer);

        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        Flowable<String> flowable = Flowable.just("Himanshu Nagpal is the king");

        flowable.subscribe(new Consumer<String>()
        {
            @Override
            public void accept(String arg0) throws Exception
            {
                System.out.println(arg0);
            };

        });
        //--------------------------------------------------------------------------------------

        Employee e1 = new Employee("Himanshu", 1, "HCL");
        Employee e2 = new Employee("Mohneesh", 2, "HCL");
        Employee e3 = new Employee("Alam", 3, "HCL");
        Employee e4 = new Employee("Parkhi", 4, "HCL");
        Employee e5 = new Employee("Nagpal", 5, "HCL");
        Observable<Employee> source = Observable.just(e1, e2, e3, e4, e5, e1, e2, e3, e4, e5);
        Consumer<Employee> con = System.out::println;
        source.subscribe(con);
        //--------------------------------------------------------------------------------------
        List<Integer> list = Arrays.asList(5, 10, 20, 15, 30);
        List<Integer> list2 = list.stream().map(i -> i * 2).collect(Collectors.toList());
        // OR Set<Integer> list2 = list.stream().map(i -> i * 2).collect(Collectors.toSet());
        System.out.println(list2);
        //--------------------------------------------------------------------------------------
        

    }
}
