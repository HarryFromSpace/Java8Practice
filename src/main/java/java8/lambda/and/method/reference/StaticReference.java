package java8.lambda.and.method.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

// Static Method Reference
public class StaticReference
{
    public static Boolean isMoreThanFifty(int num1, int num2)
    {
        return (num1 + num2) > 50;
    }


    public static List<Integer> findNumber(List<Integer> numberList, BiPredicate<Integer, Integer> predicate)
    {
        List<Integer> resultList = new ArrayList<>();
        for (Integer number : numberList)
            if (predicate.test(number, number + 20))
                resultList.add(number);
        return resultList;
    }


    public static void main(String[] args)
    {
        List<Integer> numberList = Arrays.asList(4, 7, 12, 26, 15, 18, 22, 29, 14, 16);

        System.out.println("<<---------------- Using Anonymous Class ---------------->>");
        List<Integer> resultByAnonymousClass = StaticReference.findNumber(numberList, new BiPredicate<Integer, Integer>()
        {
            @Override
            public boolean test(Integer t, Integer u)
            {
                return StaticReference.isMoreThanFifty(t, u);
            }
        });
        resultByAnonymousClass.stream().forEach(System.out::println);

        System.out.println("<<------------------- Using Lambda Expression -------------------->>");
        List<Integer> resultByLambda = StaticReference.findNumber(numberList, (a, b) -> StaticReference.isMoreThanFifty(a, b));
        resultByLambda.stream().forEach(System.out::println);

        System.out.println("<<-------------------- Using Method Reference -------------------->>");
        List<Integer> resultByMethodRef = StaticReference.findNumber(numberList, StaticReference::isMoreThanFifty);
        resultByMethodRef.stream().forEach(System.out::println);
    }

}
