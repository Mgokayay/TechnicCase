import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static List<Order> orders = new ArrayList<Order>();

    public static void main(String[] args) {
        initializeOrders();

        System.out.println("1. soru çıktısı");
        System.out.println("total : ");
        calculateTotal();
        System.out.println("2. soru çıktısı");
        System.out.println("average : ");
        calculateAverage();
        System.out.println("3. soru çıktısı");
        System.out.println("avgPrice : ");
        productAveragePrice();
        System.out.println("4. soru çıktısı");
        System.out.println("product based orders :");
        calculateProductOrderQuantities();
    }

    public static void initializeOrders() {
        orders.add(new Order(1000, 2000, 12, 100.51));
        orders.add(new Order(1000, 2001, 31, 200.00));
        orders.add(new Order(1000, 2002, 22, 150.86));
        orders.add(new Order(1000, 2003, 41, 250.00));
        orders.add(new Order(1000, 2004, 55, 244.00));
        orders.add(new Order(1001, 2001, 88, 44.531));
        orders.add(new Order(1001, 2002, 121, 88.11));
        orders.add(new Order(1001, 2004, 74, 211.00));
        orders.add(new Order(1001, 2002, 14, 88.11));
        orders.add(new Order(1002, 2003, 2, 12.10));
        orders.add(new Order(1002, 2004, 3, 22.30));
        orders.add(new Order(1002, 2003, 8, 12.10));
        orders.add(new Order(1002, 2002, 16, 94.00));
        orders.add(new Order(1002, 2005, 9, 44.10));
        orders.add(new Order(1002, 2006, 19, 90.00));
    }

    public static void calculateTotal() {
        double total = orders.stream()
                .mapToDouble(order -> order.quantity * order.unitPrice).sum();
        System.out.println("Total : " + total);
    }

    public static void calculateAverage(){
        double averagePrice = orders.stream()
                .mapToDouble(order -> order.unitPrice).average().orElse(0);
         System.out.println("Average : " + averagePrice);
    }

    public static void productAveragePrice(){
        Map<Integer, Double> productAvg = orders.stream()
                .collect(Collectors.groupingBy(order -> order.productNumber,
                         Collectors.averagingDouble(order -> order.unitPrice)));

        productAvg.forEach((product,avgPrice) ->
                System.out.println("product average : " + avgPrice + " : " + product));
    }

    public static void calculateProductOrderQuantities() {
        Map<Integer, Map<Integer, Integer>> productOrders = new HashMap<>();


        for (Order order : orders) {

            int productNumber = order.productNumber;

            int orderNumber = order.orderNumber;

            int quantity = order.quantity;


            if (!productOrders.containsKey(productNumber)) {
                productOrders.put(productNumber, new HashMap<>());
            }


            productOrders.get(productNumber).put(orderNumber, quantity);
        }


        for (Map.Entry<Integer, Map<Integer, Integer>> entry : productOrders.entrySet()) {
            int productNumber = entry.getKey();
            System.out.println("Product Number: " + productNumber);
            for (Map.Entry<Integer, Integer> orderEntry : entry.getValue().entrySet()) {
                System.out.println("    Order Number: " + orderEntry.getKey() + ", Quantity: " + orderEntry.getValue());
            }
        }
    }
    }
