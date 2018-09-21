package java8.lambda.and.method.reference;

import java.util.function.Consumer;

class Car
{
    private String name;
    private String owner;


    public Car()
    {
        super();
    }


    public Car(String name, String owner)
    {
        super();
        this.name = name;
        this.owner = owner;
    }


    public String getName()
    {
        return name;
    }


    public String getOwner()
    {
        return owner;
    }

}


class Mechanic
{
    public void fix(Car car)
    {
        System.out.println("Fixing Car : " + car.getName());
    }
}


public class InstanceMethodReference2
{
    public static void execute(Car car, Consumer<Car> consumer)
    {
        consumer.accept(car);
    }


    public static void main(String[] args)
    {

        Mechanic mechanic = new Mechanic();
        Car car = new Car("Audi", "Himanshu");

        // Anonymous Function
        System.out.println("<<---------------- Using Anonymous Class/Function ---------------->>");
        execute(car, new Consumer<Car>()
        {
            @Override
            public void accept(Car c)
            {
                mechanic.fix(c);
            }
        });

        // Lambda Expression
        System.out.println("<<-------------------- Using Lambda Expression ------------------->>");
        execute(car, c -> mechanic.fix(c));

        // Method Reference
        System.out.println("<<-------------------- Using Method Reference -------------------->>");

        execute(car, mechanic::fix);

    }
}
