package java8.stream.api.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nagpalh
 *
 *     In general Difference between Map and Flat map is :-
 *          1) A Map operation wraps its return value inside its ordinal type while FlatMap doesn't.
 *             For example : Map would return Optional<String> while FlatMap would return String.
 *          2) Both are intermediate Stream Operation, that receive a function and apply this function to all elements of stream.
 *             Note : The function which they receive is lambda function.
 *             Main Point : Map receive value from function && FlatMap receives stream for that function.
 */
public class MapAndFlatMap
{
    public static void main(String[] args)
    {

        // Collection.Map<k,v>
        Map<String, List<String>> people = new HashMap<>();
        people.put("Himanshu", new ArrayList<>(Arrays.asList("7876497499", "9922479401")));
        people.put("Harry", new ArrayList<>(Arrays.asList("9412564725", "9017028411")));
        people.put("Deepam", new ArrayList<>(Arrays.asList("9017244127", "9991735924")));

        // flatMap()
        System.out.println("----------- FlatMap receives value from Function as Stream--------------");
        people.values().stream().flatMap(e -> e.parallelStream()).collect(Collectors.toList()).forEach(System.out::println);

        // map()
        System.out.println("----------- Map receives value from Function as 'String'--------------");
        people.values().stream().map(e -> e.get(0) + "\n" + e.get(1)).collect(Collectors.toList()).forEach(System.out::println);

        // map()
        System.out.println("----------- Map receives value from Function as same --------------");
        people.values().stream().map(e -> e).collect(Collectors.toList()).forEach(System.out::println);

        // map()
        System.out.println("----------- Map receives value from Function as stream --------------");
        people.values().stream().map(e -> e.stream()).collect(Collectors.toList()).forEach(System.out::println);
    }

}
