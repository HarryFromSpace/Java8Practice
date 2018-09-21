package java8.functional.interfaces.demo;

public class myFunctionalInterfacesClass
{

    public static void main(String[] args)
    {
        // Function         => takes one argument and return single value(of any type)
        // BiFunctional     => takes two argument and return single value(of any type)

        // Predicate        => takes one argument and return boolean value
        // BiPredicate      => takes two argument and return boolean value

        // UnaryOperator    => takes one argument and return single value(of same type)
        // BinaryOperator   => takes two argument and return single value(of same type)

        // Consumer         => takes one argument and return nothing.
        // Supplier         => takes zero argument and return single value

        

        // 9)Other Functional Interfaces

        // 9.1)Runnable Interface

        // 9.1.a) Annonymous Class
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Runnable Run() Method");
            }
        };

        // 9.1.b) Lambda expression / Anonymous Function
        Runnable runnable2 = () -> System.out.println("Runnable Run() Method2");

        // 9.1.c) Results
        runnable.run();
        runnable2.run();
        System.out.println("------------------------------------------------------------------------");
        
        
    }

}
