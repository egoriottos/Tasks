import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String,Double> orderStream = orders.stream().collect(Collectors
                .groupingBy(Order::getProduct,Collectors.summingDouble(Order::getCost)));
        orderStream.forEach((e,e2)-> System.out.println(e+", "+e2));
        System.out.println("---------------------------------------");
        List<Map.Entry<String,Double>> sortedStream = orderStream.entrySet()
                .stream().sorted((e1,e2)->Double.compare(e2.getValue(), e1.getValue()))
                .toList();
        sortedStream.forEach(e-> System.out.println(e));
        System.out.println("-----------------------------------------");

        List<Map.Entry<String,Double>> firstThreeMostExpensive = sortedStream.stream()
                .limit(3).toList();

        firstThreeMostExpensive.forEach(e-> System.out.println(e));


    }
}
class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}
