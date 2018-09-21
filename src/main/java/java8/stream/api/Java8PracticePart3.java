package java8.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Java8PracticePart3
{

    public static void main(String[] args)
    {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> intList = new ArrayList<Integer>(Arrays.asList(intArray));

        List<Integer> serialStorage = new ArrayList<Integer>();
        List<Integer> parallelStorage = new ArrayList<Integer>();
        List<Integer> parallelSyncStorage = Collections.synchronizedList(new ArrayList<Integer>());

        intList.stream().map(e -> {
            serialStorage.add(e);
            return e;
        }).forEachOrdered(System.out::println); //with stream both forEach/forEachOrdered operation works same

        System.out.println("-------------------------------------------------------");

        intList.parallelStream().map(e -> {
            parallelStorage.add(e);
            return e;
        }).forEachOrdered(System.out::println); //with parallel stream in forEach/forEachOrdered work diffrently(check it out, magic) 

        System.out.println("-------------------------------------------------------");

        intList.parallelStream().map(e -> {
            parallelSyncStorage.add(e);
            return e;
        }).forEachOrdered(System.out::println);

        // Note :- if we use forEach operation it will work absolutely fine. 
        //          but forEachOrdered operation with parallel stream in case of stateless lambda can be dangerous.
        //          you can see each time result is unpredictable and also sometime we get less elements.
        //          we can use synchronized List or remove this problem :- 
        //              List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<Integer>());

        System.out.println("-------------------------------------------------------");

        // Note : Read Carefully                                                      //
        // => 1)      stream()    + forEach()               ==> same                  //
        // => 2)      stream()    + forEachOrdered()        ==> same                  //
        // => 3) parallelStream() + forEach()               ==> Random                //
        // => 4) parallelStream() + forEachOrdered()        ==> same                  //

        System.out.println("order + stream => serailStorage(12345678) : " + serialStorage);
        System.out.println("order + parallel stream => parallelStorage(Random-1) : " + parallelStorage); //some element were lost due to stateless lambda , so use sync collection if you want to perform add/remove in lambda or use stateless lambda
        System.out.println("order + parallel stream => parallelStorage(Random-2) : " + parallelSyncStorage);
        System.out.println("-------------------------------------------------------");

        System.out.println("Expected : 12345678");
        serialStorage.stream().forEach(System.out::println);

        System.out.println("Expected : 12345678");
        serialStorage.stream().forEachOrdered(System.out::println);

        System.out.println("Expected : Random-3");
        serialStorage.parallelStream().forEach(System.out::println);

        System.out.println("Expected : 12345678");
        serialStorage.parallelStream().forEachOrdered(System.out::println);

        System.out.println("-------------------------------------------------------");

        System.out.println("Expected : Random-1");
        parallelStorage.stream().forEach(System.out::println);
        System.out.println("Expected : Random-1");
        parallelStorage.stream().forEachOrdered(System.out::println);
        System.out.println("Expected : Random-4");
        parallelStorage.parallelStream().forEach(System.out::println);
        System.out.println("Expected : Random-1");
        parallelStorage.parallelStream().forEachOrdered(System.out::println);

        System.out.println("-------------------------------------------------------");

        System.out.println("Expected : Random-2");
        parallelSyncStorage.stream().forEach(System.out::println);
        System.out.println("Expected : Random-2");
        parallelSyncStorage.stream().forEachOrdered(System.out::println);
        System.out.println("Expected : Random-5");
        parallelSyncStorage.parallelStream().forEach(System.out::println);
        System.out.println("Expected : Random-2");
        parallelSyncStorage.parallelStream().forEachOrdered(System.out::println);

        System.out.println("-------------------------------------------------------");
    }

}
