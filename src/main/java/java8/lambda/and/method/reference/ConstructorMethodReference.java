package java8.lambda.and.method.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import io.reactivex.functions.BiFunction;

public class ConstructorMethodReference
{
    public static void main(String[] args) throws Exception
    {
        // Zero Argument Constructor => Supplier
        zeroArgumentConstructor();

        // One Argument Constructor  => Function
        oneArgumentConstructor();

        // Two Argument Constructor  => BiFunction
        twoArgumentConstructor();
    }


    private static void zeroArgumentConstructor()
    {
        // Using Anonymous Class
        Supplier<List<String>> supplierAnonymous = new Supplier<List<String>>()
        {
            public List<String> get()
            {
                return new ArrayList<String>();
            }
        };
        List<String> listString = supplierAnonymous.get();

        // Using Lambda Expression
        Supplier<List<String>> supplierLambda = () -> new ArrayList<String>();
        List<String> listString2 = supplierLambda.get();

        // Using Method Reference
        Supplier<List<String>> supplierMethodRef = ArrayList::new;
        List<String> listString3 = supplierMethodRef.get();

        // Note :- Lazy and Early evaluation
        //          () -> new ArrayList<>(); is Lazy Evaluation
        //          new ArrayList<>(); is Early Evaluation

        //Testing
        System.out.println("Zero parameterized Constructor (using Supplier)");
        listString.add("1");

        listString2.addAll(listString);
        listString2.add("2");

        listString3.addAll(listString2);
        listString3.add("3");

        System.out.println("List Anonymous : " + listString + "\nList Lambda : " + listString2 + "\nList Method Ref : " + listString3);
        System.out.println("--------------------------------------------------------------------");
    }


    private static void oneArgumentConstructor()
    {
        // Anonymous Class
        Function<String, Integer> functionAnonymous = new Function<String, Integer>()
        {
            @Override
            public Integer apply(String str)
            {
                return new Integer(str);
            }
        };

        Integer integerAnonymous = functionAnonymous.apply("353");
        System.out.println("Integer Anonymous : " + integerAnonymous);

        // Lambda Function
        Function<String, Integer> functionLambda = (str) -> new Integer(str);
        // Note : It is just like anonymous function => look at apply() function and this lambda => same parameter is passed & "Same" body
        Integer integerLambda = functionLambda.apply("7");
        System.out.println("Integer Lambda : " + integerLambda);

        // Method Reference
        Function<String, Integer> functionMethodRef = Integer::new;
        Integer integerMethodRef = functionMethodRef.apply("4444");
        System.out.println("Integer Method Ref : " + integerMethodRef);

        System.out.println("--------------------------------------------------------------------");
    }


    private static void twoArgumentConstructor() throws Exception
    {
        // Anonymous Class
        BiFunction<String, String, List<String>> biFunctionAnonymous = new BiFunction<String, String, List<String>>()
        {
            @Override
            public List<String> apply(String t1, String t2) throws Exception
            {
                return Arrays.asList(t1, t2);
            }
        };
        List<String> listStringAnonymous = biFunctionAnonymous.apply("Himanshu", "Nagpal");
        listStringAnonymous.stream().forEach(System.out::println);

        // Lambda Function
        BiFunction<String, String, List<String>> biFunctionLambda = (a, b) -> Arrays.asList(a, b);
        List<String> listStringLambda = biFunctionLambda.apply("Bhawna", "Nagpal");
        listStringLambda.stream().forEach(System.out::println);

        // Method Reference
        BiFunction<String, String, List<String>> biFunctionMethodRef = Arrays::asList;
        List<String> listStringMethodRef = biFunctionMethodRef.apply("Harry", "Nagpal");
        listStringMethodRef.stream().forEach(System.out::println);
    }

}
