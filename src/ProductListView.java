import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductListView extends JPanel{
    private JPanel panel;
    private JTable table;

    public ProductListView(List<Product> products) {
        panel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Name", "Category", "Brand", "Price", "Stock Quantity", "Description", "Wholesale Price"};
        Object[][] data = new Object[products.size()][8];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = product.getId();
            data[i][1] = product.getName();
            data[i][2] = product.getCategory();
            data[i][3] = product.getBrand();
            data[i][4] = product.getPrice();
            data[i][5] = product.getStockQuantity();
            data[i][6] = product.getDescription();

        }

        table = new JTable(data, columnNames);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }



}
