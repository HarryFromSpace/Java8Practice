package java8.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ds.linklist.test.Employee;
import com.ds.linklist.test.Employee.Sex;

public class Java8PracticePart2
{
    public static void main(String[] args)
    {

        Employee e1 = new Employee("Himanshu", 1, "HCL", Employee.Sex.Male);
        Employee e2 = new Employee("Bhawna", 2, "HCL", Employee.Sex.Female);
        Employee e3 = new Employee("Roshni", 3, "HCL", Employee.Sex.Female);
        Employee e4 = new Employee("Chandni", 4, "HCL", Employee.Sex.Female);
        Employee e5 = new Employee("Nagpal", 5, "HCL", Employee.Sex.Male);
        Employee e6 = new Employee("Harry", 6, "HCL", Employee.Sex.Male);
        Employee e7 = new Employee("Rahul", 7, "HCL", Employee.Sex.Male);

        List<Employee> empList = new ArrayList<>();
        empList.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        System.out.println("\n------------------ Parallel Streams ------------------");

        // Parallel computing means dividing problem into sub parts and executing the sub parts simultaneously at same time and combining results.
        // Parallel computing is difficult in case of Collections in java as they are non-thread safe.
        // Problems in parallel computing:- Thread interference & Memory inconsistency errors.
        //      For ex: -  Suppose one thread is reading some data in collection and other thread change the data before it read.

        // 1) So, Threads can manipulate Collections by implementing Thread interference and memory inconsistency solutions.

        // 2) Collection framework also provide synchronization wrappers, which add synchronization to Collections and they become thread-safe.
        //      But , synchronization causes Thread-Contention Problem(live-lock and starvation problem).

        // 3) However, Aggregate operations and parallel stream enables you to implement parallelism with non-thread safe Collections.
        //      provided a condition that you do not modify the Collections while operating on them.

        // Note : Parallelism generally is faster but not in all cases its all depend on the data and processors you have, 
        //          So, it is your responsibility to check if your application really need it.

        // ====> Executing parallel Streams <====
        Double maleEmpIdAverage = empList.stream().parallel().filter(e -> e.getGender() == Sex.Male).mapToInt(Employee::getId).average().getAsDouble();
        Double maleEmpIdAverage2 = empList.parallelStream().filter(e -> e.getGender() == Sex.Male).mapToInt(Employee::getId).average().getAsDouble();
        System.out.println("Male Emp Id Average (using stream().parallel()): " + maleEmpIdAverage);
        System.out.println("Male Emp Id Average (using parallelStream()): " + maleEmpIdAverage2);

        System.out.println("---------------------------------------------------------------------------------------");

        // ====> Concurrent Reduction <====
        ConcurrentMap<Sex, List<Employee>> mapOfEmployeesGroupByGender = empList.parallelStream().collect(Collectors.groupingByConcurrent(Employee::getGender));
        mapOfEmployeesGroupByGender.forEach((a, b) -> System.out.println(a + " " + b));

        // Note : Donot use groupingBy() with parallel stream , it works poorly.                        // 
        //        Also you can see difference in result order in both cases:-                           //
        //          1) stream gives sequential order record  ,  2) parallel stream gives random order   //
        //        So , use groupingByConcurrent() for current reduction.                                //

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        // Concurrent reduction is possible if we have following condition for a particular pipeline that contains colect operation :-
        // 1) Stream is parallel.
        // 2) parameter of collect i.e. collect must have character "Collection.Characterstics.CONCURRENT".
        // 3) Either "stream is unordered" or "collector have 'Collector.Characterstics.UNORDERED'" 
        // Example are af follows:-

        System.out.println("-------------------- Concurrent Reduction : 1st condition : Stream should be parallel --------------------");

        Stream<Employee> streamOfEmployees = empList.stream();
        Stream<Employee> parallelStreamOfEmployees = empList.parallelStream();

        System.out.println("Ques) stream() is parallel stream?? \nAns :- " + streamOfEmployees.isParallel());
        System.out.println("Ques) parallelStream() is parallel stream?? \nAns :- " + parallelStreamOfEmployees.isParallel());

        // Conclusion : parallelStream() gives parallelStream, So Concurrent Reduction is possible with parallelStream() only.

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-------------------- Concurrent Reduction : 2nd condition : Collector should have Concurrent characteristics --------------------");

        Map<Sex, List<Employee>> mapOfEmpGroupByGender = empList.parallelStream().collect(Collectors.groupingBy(Employee::getGender));
        System.out.println("Male Data using parallel stream but not Collector.concurrent operation: - ");
        mapOfEmpGroupByGender.get(Sex.Male).forEach(System.out::println);

        // It returns ConcurrentMap<> to reduce memory inconsistency problem
        ConcurrentMap<Sex, List<Employee>> concurrentMapOfEmpGroupByGender = empList.parallelStream().collect(Collectors.groupingByConcurrent(Employee::getGender));
        System.out.println("Male Data using parallel stream and Collector.concurrent operation: - ");
        concurrentMapOfEmpGroupByGender.get(Sex.Male).forEach(System.out::println);

        // Conclusion : Just Check The output sequence , you should came to know that :- 
        //              For "parallel computing" OR "concurrent reduction" we should use Collector.ConcurrentOperation

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-------------------- Concurrent Reduction : 3rd condition : Stream should be Unordered --------------------");

        Stream<Employee> unorderedStream = empList.stream().unordered();
        System.out.println("unordered() operation on Stream gives ordered list :- ");
        unorderedStream.forEach(System.out::println);

        Stream<Employee> unorderedParallelStream = empList.parallelStream().unordered();
        System.out.println("\nunordered() operation on Parallel Stream gives unordered list :- ");
        unorderedParallelStream.forEach(System.out::println);

        // Conclusion : just Check the output sequence , you should came to know that :- 
        //              For "parallel computing" OR "concurrent reduction" we should use "Collector.Unorder" or "Unordered Stream"

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        // Take a simple example from here

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));

        // Note :- 
        // 1) Order in which pipeline process the element depends on :-
        //     whether stream is executed in serial or parallel, intermediate Operations, Source of stream
        System.out.println("List of Integers : ");
        intList.stream().forEach(System.out::println);

        System.out.println("Reverse List : ");
        // Collection.sort(List<>,Comparator) , So first of all find comparator :-
        Comparator<Integer> list = (a, b) -> Integer.compare(a, b);
        Comparator<Integer> reverseList = list.reversed();
        Collections.sort(intList, reverseList);
        intList.forEach(System.out::println);

        System.out.println("Parallel Stream : ");
        intList.parallelStream().forEach(System.out::println);

        System.out.println("Parallel Stream Again to show parallel processing (random access) : ");
        intList.parallelStream().forEach(System.out::println);

        // For parallelStream(), pipeline will process the element randomly but what happen when we use forEachOrdered() , let see.....
        System.out.println("Parallel Stream with forEachOrdered()");
        intList.parallelStream().forEachOrdered(System.out::println);
        //  forEachOrdered() processes the elements of the stream in the order specified by its source,
        //    regardless of whether you executed the stream in serial or parallel.

        System.out.println("Statefull Lambda");
        List<Integer> serialStorage = new ArrayList<>();
        List<Integer> serialStorage2 = new ArrayList<>();
        List<Integer> parallelStorage = new ArrayList<>();
        List<Integer> parallelStorage2 = new ArrayList<>();

        System.out.println("-----------------------------1-----------------------------");

        // simple sequential/ordered stream , so it process and store elements one by one in ordered manner.
        intList.stream().map(e -> {
            serialStorage.add(e);
            return e;
        }).forEach(System.out::println);

        System.out.println("-----------------------------2-----------------------------");

        // simple stream with sequence depends on source of data(i.e. in ordered manner)(same as above)
        intList.stream().map(e -> {
            serialStorage2.add(e);
            return e;
        }).forEachOrdered(System.out::println);

        System.out.println("-----------------------------3-----------------------------");

        // parallel stream , pipeline pick any element and add into list, so every time sequence is unpredictable
        intList.parallelStream().map(e -> {
            parallelStorage.add(e);
            return e;
        }).forEach(System.out::println);

        System.out.println("-----------------------------4-----------------------------");

        // parallel stream but with ordered sequence(means it is ordered no benefits from parallel stream here) , 
        // pipeline process data in same sequence as that of source of stream(data). i.e. ordered
        intList.parallelStream().map(e -> {
            parallelStorage2.add(e);
            return e;
        }).forEachOrdered(System.out::println);

        System.out.println("-----------------------------5-----------------------------");

        System.out.println("serialStorage contains sequential data as it was created using stream()");

        System.out.println("Expected :- 87654321 ");
        serialStorage.stream().forEach(System.out::println);

        System.out.println("Expected :- 87654321 ");
        serialStorage.stream().forEachOrdered(System.out::println);

        System.out.println("-----------------------------6-----------------------------");

        System.out.println("serialStorage2 contains ordered sequence as it was created using stream()");

        System.out.println("Expected :- 87654321 ");
        serialStorage2.stream().forEach(System.out::println);

        System.out.println("Expected :- 87654321 ");
        serialStorage2.stream().forEachOrdered(System.out::println);

        System.out.println("-----------------------------7-----------------------------");

        System.out.println("parallelStorage contains unordered sequence as we created it using parallelStream()");

        System.out.println("unordered + stream() = (Same as Source)Unordered/Random-1 (no need to see forEach/forEachOrdered operation in case of stream())");
        parallelStorage.stream().forEach(System.out::println);

        System.out.println("unordered + stream() = (Same as Source)Unordered/Random-1 (no need to see forEach/forEachOrdered operation in case of stream())");
        parallelStorage.stream().forEachOrdered(System.out::println);

        System.out.println("unordered sequence + parallelStream() + forEach(), Excepted :- Random-2");
        parallelStorage.parallelStream().forEach(System.out::println);

        System.out.println("unordered sequence + parallelStream() + forEachOrdered(), Excepted : Source of stream i.e. Unordered/Random-1 ");
        parallelStorage.parallelStream().forEachOrdered(System.out::println);

        System.out.println("-----------------------------9-----------------------------");

        System.out.println("parallelStorage2 is unordered sequence, it was created with parallelStream()");

        System.out.println("unordered-2 sequence + stream() => Source of Stream/Unordered-2/Random-3(no need to check forEach/forEachOrdered in case of stream())");
        parallelStorage2.stream().forEach(System.out::println);

        System.out.println("unordered-2 sequence + stream() => Source of Stream/Unordered-2/Random-3(no need to check forEach/forEachOrdered in case of stream())");
        parallelStorage2.stream().forEachOrdered(System.out::println);

        System.out.println("unordered-2 sequence + parallelStream() + forEach(), Expected : Random-4 ");
        parallelStorage2.parallelStream().forEach(System.out::println);

        System.out.println("unordered-2 sequence + parallelStream() + forEachOrdered() => Source of Stream/Unordered-2/Random-3");
        parallelStorage2.parallelStream().forEachOrdered(System.out::println);

    }
}
