import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ProductManager {
    private Map<String, Product> products = new HashMap<>();

    // Add a product to the manager
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    // Update an existing product
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    // Delete a product by its ID
    public void deleteProduct(String productId) {
        products.remove(productId);
    }

    // Get a product by its ID
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // Set multiple products
    public void setProducts(List<Product> productList) {
        for (Product product : productList) {
            addProduct(product);
        }
    }
}
