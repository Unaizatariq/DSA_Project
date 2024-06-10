import java.util.HashMap;
import java.util.Map;
public class OrderManager {
    private Map<String, Order> orders = new HashMap<>();

    // Create a new order
    public void createOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    // Update an order
    public void updateOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    // Delete an order
    public void deleteOrder(String orderId) {
        orders.remove(orderId);
    }

    // Track an order
    public Order trackOrder(String orderId) {
        return orders.get(orderId);
    }

    // Display order details
    public void displayOrderDetails(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            System.out.println("Order Details: " + order);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Get all orders
    public Map<String, Order> getAllOrders() {
        return orders;
    }
}
