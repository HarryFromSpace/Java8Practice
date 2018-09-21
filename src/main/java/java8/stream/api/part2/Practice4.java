package java8.stream.api.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ds.linklist.test.Employee;

public class Practice4
{

    public static void main(String[] args)
    {

        Employee e1 = new Employee("Himanshu", 1, "HCL", Employee.Sex.Male);
        Employee e2 = new Employee("Bhawna", 2, "HCL", Employee.Sex.Female);
        Employee e3 = new Employee("Nagpal", 3, "HCL", Employee.Sex.Male);
        Employee e4 = new Employee("Chandni", 4, "HCL", Employee.Sex.Female);
        Employee e5 = new Employee("Roshni", 5, "HCL", Employee.Sex.Female);
        Employee e6 = new Employee("Harry", 6, "HCL", Employee.Sex.Male);
        Employee e7 = new Employee("Rahul", 7, "HCL", Employee.Sex.Male);

        List<Employee> empList = new ArrayList<>();
        empList.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        System.out.println("-------------------------------------------------------------------------------------");

        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        List<String> stringList = new ArrayList<>(Arrays.asList("1", "333", "666666", "55555", "4444", "22"));

        priorityQueue.addAll(stringList);

        System.out.println("-------------------------------------------------------------------------------------");

        // Problem : See Output and order of Insertion...
        System.out.println("Before Creating Stream, PriorityQueue: ");
        priorityQueue.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------------------------");

        // Solution 1
        System.out.println("Solution 1 : Priority Queue converted into List<String> Stream (Using sorted().collect(...) : ");
        List<String> sortedQueue = priorityQueue.stream().sorted().collect(Collectors.toList());
        sortedQueue.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------------------------");

        // Solution 2
        System.out.println("Solution 2 : Priority Queue converted into List<String> Stream (Using poll) : ");
        List<String> result = Stream.generate(priorityQueue::poll).limit(priorityQueue.size()).collect(Collectors.toList());
        result.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------------------------");

        System.out.println("After Creating Stream Using priorityQueue.poll() , priorityQueue becomes Empty : ");
        priorityQueue.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------------------------");

        Map<Employee.Sex, List<Employee>> abc = empList.stream().collect(Collectors.groupingBy(Employee::getGender));
        abc.forEach((sex, list) -> System.out.println(sex + " : " + list));

        System.out.println("-------------------------------------------------------------------------------------");

        empList.stream().collect(Collectors.toMap(Employee::getName, Function.identity())).forEach((a, b) -> System.out.println(a + " : " + b));

        System.out.println("-------------------------------------------------------------------------------------");

        Stream<String> streamOfString = Stream.of("Harry", "Nagpal", "22", "333");
        Stream<String> streamOfString2 = streamOfString.peek(System.out::println); // Alert : It will print nothing
        streamOfString2.forEach(System.out::println); // It will print two times each String
        
        System.out.println("-------------------------------------------------------------------------------------");

    }

}
