import java.util.ArrayList;
import java.util.List;
public class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (ShoppingCartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new ShoppingCartItem(product, quantity));
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public ShoppingCartItem getItem(String productId) {
        for (ShoppingCartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

}
