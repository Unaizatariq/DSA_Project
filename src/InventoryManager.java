import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class InventoryManager {
    private Map<String, Integer> stockLevels = new HashMap<>();
    private PriorityQueue<Product> reorderQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getStockQuantity(), p2.getStockQuantity()));

    // Set stock level for a product
    public void setStockLevel(String productId, int quantity, ProductManager productManager) {
        stockLevels.put(productId, quantity);
        Product product = productManager.getProduct(productId);
        if (product != null) {
            product.setStockQuantity(quantity);
            reorderQueue.offer(product);
        }
    }

    // Monitor inventory and generate reorder alerts
    public void monitorInventory() {
        while (!reorderQueue.isEmpty()) {
            Product product = reorderQueue.poll();
            if (product.getStockQuantity() < 10) { // Assuming 10 as low stock threshold
                System.out.println("Reorder needed for product: " + product.getName());
            }
        }
    }

    // Get stock levels
    public Map<String, Integer> getStockLevels() {
        return stockLevels;
    }

    public void updateStockLevels(Map<Product, Integer> items) {
    }
}
