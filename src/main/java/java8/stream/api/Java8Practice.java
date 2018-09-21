package java8.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ds.linklist.test.Employee;
import com.ds.linklist.test.Employee.Sex;

public class Java8Practice<MyTemplateT>
{

    public <T> Java8Practice(T t)
    {
        System.out.println(t);
    }


    static void invoke(Runnable r)
    {
        System.out.println("abc");
        r.run();
    }


    static <T> T invoke(Callable<T> c) throws Exception
    {
        System.out.println("abc2");
        return c.call();
    }


    public static void main(String[] args) throws Exception
    {
        Java8Practice<Integer> j = new Java8Practice<>(Integer.valueOf(5));

        List<String> abc = (List<String>) Arrays.asList("Harry", "Nagpal");
        // abc.add("none");
        System.out.println(abc);

        List<Integer> intList = Arrays.asList(5, 2, 4, 3, 2, 1);
        Collections.sort(intList, (a, b) -> a.compareTo(b));
        System.out.println(intList); // intList.forEach(System.out::println);

        List<Integer> intList2 = new ArrayList<>(Arrays.asList(5, 2, 4, 3, 2, 1));
        //Collections.sort(intList2, (a, b) -> Integer.compare(a, b));
        Collections.sort(intList2, (a, b) -> a.compareTo(b));
        //Collections.sort(intList2, Integer::compare);

        System.out.println(intList2);

        List<Integer> intList3 = new ArrayList<>(Arrays.asList(15, 12, 14, 13, 12, 11));
        Collections.sort(intList3);
        System.out.println(intList3);

        invoke(() -> "Done");
        // System.out.println(ab);
        System.out.println("---------------------------------------------------------------------------------------");

        Employee e1 = new Employee("Himanshu", 1, "HCL", Employee.Sex.Male);
        Employee e2 = new Employee("Bhawna", 2, "HCL", Employee.Sex.Female);
        Employee e3 = new Employee("Nagpal", 3, "HCL", Employee.Sex.Male);
        Employee e4 = new Employee("Chandni", 4, "HCL", Employee.Sex.Female);
        Employee e5 = new Employee("Roshni", 5, "HCL", Employee.Sex.Female);
        Employee e6 = new Employee("Harry", 6, "HCL", Employee.Sex.Male);
        Employee e7 = new Employee("Rahul", 7, "HCL", Employee.Sex.Male);

        List<Employee> empList = new ArrayList<>();
        empList.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        // "Stream" API 
        for (Employee e : empList)
            System.out.println(e.getName());

        System.out.println("---------------------------------------------------------------------------------------");

        empList.stream().forEach(e -> System.out.println(e.getName()));
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        for (Employee e : empList)
            if (e.getGender() == Employee.Sex.Female)
                System.out.println(e.getName());

        empList.stream().filter(e -> e.getGender().equals(Employee.Sex.Female)).forEach(System.out::println);

        System.out.println("----------------------------- Reduction ----------------------------------------------------------");

        //Average,Sum,Count,Max on Id of Employees List
        Double maleAverageId = empList.stream().filter(e -> e.getGender().equals(Employee.Sex.Male)).mapToInt(Employee::getId).average().getAsDouble();
        Integer maleMaxId = empList.stream().filter(e -> e.getGender().equals(Employee.Sex.Male)).mapToInt(Employee::getId).max().getAsInt();
        Long emplCount = empList.stream().mapToInt(Employee::getId).count();
        Integer sumofEmployeeId = empList.stream().mapToInt(Employee::getId).sum();
        System.out.println(
            "male Average Id : " + maleAverageId + " \n" + "male Max Id : " + maleMaxId + " \n" + "Employee Count : " + emplCount + " \n" + "sum of id's : " + sumofEmployeeId);

        //Sum of Id's of Employees List : reduce() method
        int sumOfIdOfEmployees = empList.stream().map(Employee::getId).reduce(200, (emp1, emp2) -> emp1 + emp2);
        System.out.println("sum Of ids using reduce(100, lambda) : " + sumOfIdOfEmployees);
        int sumOfIdOfEmployees2 = empList.stream().map(Employee::getId).reduce((emp1, emp2) -> emp1 + emp2).get();
        System.out.println("sum Of ids using reduce(lambda) : " + sumOfIdOfEmployees2);

        // Note:- 1) Reduction mean method which can convert Stream into single data or collection of data  //
        //        2) Reduction Example of single data :- sum() , min() , max() , average()                  //
        //        3) Reduction Example of collection of data :- reduce() , collect()                        //
        //        4) Stream is returned by stream() or map() functions                                      //
        //        5) Reduce method always creates a new value when it processes an element,                 // 
        //           collect method modifies, or mutates, an existing value.                                //

        /**
         * About -------- .collect(supplier, accumulator, combiner); -----------
         * supplier : result required
         * accumulator : handle single stream element/operation
         * combiner : takes two container as input and merge
         */
        // collect() method
        List<String> empNameList = empList.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(empNameList);
        List<String> empNameList2 = empList.stream().map(Employee::getName).collect(ArrayList::new, List::add, List::addAll);//we can use ArrayList/List both but instantiation can only be possible via ArrayList
        System.out.println(empNameList2);

        System.out.println("---------------------------------------------------------------------------------------");

        // Grouping by Gender :- Map contains only Two Elements with KEY : "Male" and "Female" and Their VALUE is of type : List<Employee>
        // Map Keys are values returned by lambdaFunction() : as parameter in groupingBy(lambdaFunction())
        Map<Sex, List<Employee>> groupByGender = empList.stream().collect(Collectors.groupingBy(Employee::getGender));

        System.out.println("S.O.P. : " + groupByGender);

        groupByGender.forEach((a, b) -> System.out.println(b.size() + " " + a + " : " + b));

        System.out.println("Females Record : ");
        groupByGender.get(Sex.Female).forEach(System.out::println);;

        System.out.println("Males Record : ");
        groupByGender.get(Sex.Male).forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        Map<Sex, List<String>> mapofEmpNamesGroupByGender =
            empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.toList())));

        mapofEmpNamesGroupByGender.forEach((a, b) -> System.out.println(a + " : " + b));

        // Note : We can calculate male max id by applying filter(e -> e.getGender==Male) function on stream 
        //        and then mapToInt() for converting it to integer array
        //        and then max() function for finding maximum value
        // SO , WE CAN DO IT BY FILTER ALSO BUT IF WE WANT TO FIND MULTIPLE MAXIMUM ACCORDING TO GROUPS(MAP OF MAXIMUM) WE HAVE TO USE :- collect()
        // i.e.  collect(Collectors.groupingBy("FUNCTION", Collectors.reduce(.....)));
        //Max of Id of male Employees only
        Map<Sex, Integer> mapOfMaxIdInBothGender =
            empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.reducing(0, Employee::getId, Integer::max)));

        mapOfMaxIdInBothGender.forEach((a, b) -> System.out.println("Max Id of " + a + " : " + b));

        System.out.println("---------------------------------------------------------------------------------------");

        System.out.println(
            "Avarage id by gender : "
                + empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getId))));

        System.out.println(
            "Average id using collect(Collectos.averageDouble(Employee::getId)) : "
                + empList.stream().collect(Collectors.averagingDouble(Employee::getId)));

        System.out.println(
            "Avarage id using mapToInt(Employee::getId).avarage() : "
                + empList.stream().mapToInt(Employee::getId).average().getAsDouble());

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

        System.out.println("---------------------------------------------------------------------------------------");
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

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("-------------------- Concurrent Reduction : 2nd condition : Collector should have Concurrent characteristics --------------------");

        Map<Sex, List<Employee>> mapOfEmpGroupByGender = empList.parallelStream().collect(Collectors.groupingBy(Employee::getGender));
        System.out.println("Male Data using parallel stream but not Collector.concurrent operation: - ");
        mapOfEmpGroupByGender.get(Sex.Male).forEach(System.out::println);

        ConcurrentMap<Sex, List<Employee>> concurrentMapOfEmpGroupByGender = empList.parallelStream().collect(Collectors.groupingByConcurrent(Employee::getGender));
        System.out.println("Male Data using parallel stream and Collector.concurrent operation: - ");
        concurrentMapOfEmpGroupByGender.get(Sex.Male).forEach(System.out::println);

        // Conclusion : Just Check The output sequence , you should came to know that :- 
        //              For "parallel computing" OR "concurrent reduction" we should use Collector.ConcurrentOperation

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("-------------------- Concurrent Reduction : 3rd condition : Stream should be Unordered --------------------");

        Stream<Employee> unorderedStream = empList.stream().unordered();
        System.out.println("unordered() operation on Stream:- ");
        unorderedStream.forEach(System.out::println);

        Stream<Employee> unorderedParallelStream = empList.parallelStream().unordered();
        System.out.println("\nunordered() operation on Parallel Stream:- ");
        unorderedParallelStream.forEach(System.out::println);

        // Conclusion : just Check the output sequence , you should came to know that :- 
        //              For "parallel computing" OR "concurrent reduction" we should use "Collector.Unorder" or "Unordered Stream"

        System.out.println("\n-----------------New METHODs----------------\n");

        Map<Integer, String> listToMap = empList.stream().collect(Collectors.toMap(Employee::getId, Employee::getName)); //.forEach((a, b) -> System.out.println(a + " : " + b));
        System.out.println("List to map conversion using toMap(key, Value) : " + listToMap);

        Stream<Object> empListBuilderStream = Stream.builder().add(empList).build();
        System.out.println("\nStream of Employee List using Stream.builder() : ");
        empListBuilderStream.forEach(System.out::println);

        Stream<List<Employee>> empListStreamOf = Stream.of(empList);
        System.out.println("\nStream of Employee List using Stream.of() : ");
        empListStreamOf.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        Stream<Employee> empStreamOf = Stream.of(e1, e2, e3, e4);
        System.out.println("\nUsing Stream.of(e1,e2,e3,e4) : ");
        empStreamOf.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        Stream.Builder<Employee> empStreamBuilder = Stream.builder();
        empStreamBuilder.accept(e1);
        empStreamBuilder.accept(e2);
        empStreamBuilder.accept(e3);

        Stream<Employee> empBuilderStream = empStreamBuilder.build();
        System.out.println("\nUsing builder() and multiple accept() and then build() : ");
        empBuilderStream.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        //Adding Salary Column Value ======>> Salary = id * 10000 ;
        System.out.println("Adding Salary Column Value ======>> Salary = id * 10000  : - ");
        empList.parallelStream().forEach(e -> {
            e.setSalary((9 - e.getId()) * 10000.0);
            System.out.println(e);
        });

        System.out.println("");

        // Incrementing Salary Column Value
        System.out.println("Incrementing Salary Column Value : - ");
        empList.stream().forEach(e -> {
            e.salaryIncrement(10.0);
            System.out.println(e);
        });
        //  OR
        // empList.stream().forEach(e -> e.salaryIncrement(10.0));
        // empList.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        //Random Record Generation using parallelStream()
        System.out.println("Randomly Pick:- " + empList.parallelStream().findAny().get());

        System.out.println("---------------------------------------------------------------------------------------");

        List<List<Employee>> listOfListEmployees =
            new ArrayList<>(
                Arrays.asList(
                    Arrays.asList(e2, e5, e4),
                    Arrays.asList(e1, e6, e3, e7),
                    Arrays.asList(e2, e3)));

        System.out.println("List of Employee List : ");
        listOfListEmployees.forEach(System.out::println);

        System.out.println("\nEmployee List : ");
        listOfListEmployees.stream().flatMap(Collection::stream).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");

        System.out.println("---------------------------------------------------------------------------------------");
    }

}
