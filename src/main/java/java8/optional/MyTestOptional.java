package java8.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyTestOptional
{
    public static void main(String[] args)
    {
        // 1
        Optional<String> op1 = Optional.empty();
        System.out.println("0) Output : " + op1);
        System.out.println("1) Output : " + op1.isPresent());
        op1.ifPresent(name -> System.out.println(name)); // print nothing as it work on ifPresent()

        // 2
        Optional<String> op2 = Optional.ofNullable("Harry Nagpal");
        System.out.println("2) Output : " + op2.get());
        op2.ifPresent(ele -> System.out.println("2) Output-2 : " + ele));
        op2.ifPresent(System.out::println);

        // 3
        Optional<List<String>> op3 = Optional.of(Arrays.asList("Himanshu", "Nagpal"));
        System.out.println("3) Output : " + op3);
        System.out.println("4) Output : " + op3.get());
        System.out.println("5) Output : " + op3.get().get(1)); //

        // 4
        String stringSample = null;
        //Optional<String> op4 = Optional.of(stringSample);   // null pointer exception
        Optional<String> op4 = Optional.ofNullable(stringSample);
        System.out.println("6) Output : " + op4);
        // Note : So use ofNullable() to prevent from NullPointerException

        // 5
        String stringSample2 = null;
        String preciousValue = Optional.ofNullable(stringSample2).orElse("UNKNOWN");
        System.out.println("7) Output : " + preciousValue);

        // 6
        String magicalValue = Optional.ofNullable(stringSample2).orElseGet(() -> "Magical");
        System.out.println("8) Output : " + magicalValue);

        // 7 Streams 
        int min1 = Arrays.stream(new int[] {1, 2, 3, 4, 5}).min().orElse(0);
        System.out.println("9a) Output : " + min1); // works fine

        int min2 = Arrays.stream(new int[] {}).min().orElse(0);
        System.out.println("9b) Output : " + min2); // nothing is present so Else part works

        // int min3 = Arrays.stream(new int[] {}).min().getAsInt(); // noSuchElement Exception
        // System.out.println("9c) Output : " + min3);

    }
}
