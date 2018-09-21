package java8.lambda.and.method.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class InstanceMethodReference
{
    public static List<Double> calculateOnShipment(List<Shipment> shipmentList, Function<Shipment, Double> function)
    {
        List<Double> resultDoubleList = new ArrayList<>();
        for (Shipment shipment : shipmentList)
        {
            resultDoubleList.add(function.apply(shipment));
        }
        return resultDoubleList;
    }


    public static void main(String[] args)
    {
        List<Shipment> shipmentList = new ArrayList<>(Arrays.asList(new Shipment(4, 7.77), new Shipment(12, 4.44)));

        // Anonymous Class / Function
        System.out.println("<<-------------------- Using Anonymous Class -------------------->>");
        calculateOnShipment(shipmentList, new Function<Shipment, Double>()
        {
            @Override
            public Double apply(Shipment t)
            {
                return t.calculateWeight();
            }
        }).stream().forEach(System.out::println);

        // Lambda Expression
        System.out.println("<<------------------- Using Lambda Expression ------------------->>");
        calculateOnShipment(shipmentList, s -> s.calculateWeight()).stream().forEach(System.out::println);

        // Method Refernce
        System.out.println("<<-------------------- Using Method Reference ------------------->>");
        calculateOnShipment(shipmentList, Shipment::calculateWeight).stream().forEach(System.out::println);;

    }

}


class Shipment
{
    private int number;
    private Double subItemsWeight;


    public Shipment(int number, Double subItemsWeight)
    {
        super();
        this.number = number;
        this.subItemsWeight = subItemsWeight;
    }


    public Double calculateWeight()
    {
        Double weight = number * subItemsWeight;
        return weight;
    }
}
