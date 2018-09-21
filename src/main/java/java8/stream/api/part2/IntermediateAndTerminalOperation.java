package java8.stream.api.part2;

import java.util.Arrays;

public class IntermediateAndTerminalOperation
{
    public static void main(String[] args)
    {
        // 1) Stream operations are combined into pipeline to process Stream.
        // 2) All operations are either intermediate or terminal.
        // 3) Intermediate operation are those which return stream itself alowing for further operations on stream.
        // 4) Intermediate operations are always lazy, they donot process stream at call site.
        // 5) Intermediate operation will only process the stream when there is terminal operation.
        // 6)  Intermediate operations:- filter(), map(), flatmap() etc.
        //     Terminal operations:- sum(), max(), min(), collect(), reduce(), average(), forEach() etc.

        //This code verifies that intermediate operations are lazy, they will process data only when there is terminal function on call site.
        Arrays.stream(new int[] {1, 2, 3}).filter(e -> {
            System.out.println("Printing array element : " + e);
            return e >= 2;
        });

        Arrays.stream(new int[] {1, 2, 3}).filter(e -> {
            System.out.println("Printing array element with terminal operator (sum) : " + e);
            return e >= 2;
        }).sum();

    }
}
