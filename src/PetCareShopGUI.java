import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetCareShopGUI extends Application{
    private ProductManager productManager = new ProductManager();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private Connection connection = new Connection();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pet Care Shop Management System");
        showHomePage(primaryStage);
    }

    private MenuBar createMenuBar(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();

        Menu homeMenu = new Menu("Home");
        MenuItem homeMenuItem = new MenuItem("Home");
        homeMenuItem.setOnAction(event -> showHomePage(primaryStage));
        homeMenu.getItems().add(homeMenuItem);

        Menu productsMenu = new Menu("Products");
        MenuItem catProducts = new MenuItem("Cat Products");
        MenuItem dogProducts = new MenuItem("Dog Products");

        catProducts.setOnAction(event -> showProductCategory("cat", primaryStage));
        dogProducts.setOnAction(event -> showProductCategory("dog", primaryStage));

        productsMenu.getItems().addAll(catProducts, dogProducts);

        Menu inventoryMenu = new Menu("Inventory");
        Menu customersMenu = new Menu("Customers");
        Menu ordersMenu = new Menu("Orders");

        menuBar.getMenus().addAll(homeMenu, productsMenu, inventoryMenu, customersMenu, ordersMenu);

        // Set the style of the menu bar
        menuBar.setStyle("-fx-background-color: #90EE90;");

        return menuBar;
    }

    private void showHomePage(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2F2F2; -fx-padding: 20px;");

        MenuBar menuBar = createMenuBar(primaryStage);
        root.setTop(menuBar);

        ImageView homeImageView = new ImageView(new Image("file:C:/Users/Dell 3490/Downloads/petshop1.jpg"));
        homeImageView.setFitWidth(800);
        homeImageView.setFitHeight(600);
        root.setCenter(homeImageView);

        HBox categoryButtons = new HBox(20);
        categoryButtons.setPadding(new Insets(20));
        categoryButtons.setStyle("-fx-alignment: center;");

        Button catButton = new Button("Cat Products & Accessories");
        Button dogButton = new Button("Dog Products & Accessories");

        catButton.setOnAction(event -> showProductCategory("cat", primaryStage));
        dogButton.setOnAction(event -> showProductCategory("dog", primaryStage));

        // Apply styles to the buttons
        catButton.setStyle("-fx-background-color: #8E44AD; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        dogButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        categoryButtons.getChildren().addAll(catButton, dogButton);
        root.setBottom(categoryButtons);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showProductCategory(String category, Stage primaryStage) {
        List<Product> products = createSampleProducts(category);
        productManager.setProducts(products);

        GridPane productGrid = createProductGrid(products, primaryStage);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2F2F2; -fx-padding: 20px;");
        MenuBar menuBar = createMenuBar(primaryStage);
        root.setTop(menuBar);
        root.setCenter(productGrid);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createProductGrid(List<Product> products, Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        int row = 0;
        int col = 0;
        for (Product product : products) {
            BorderPane productPane = createProductPane(product, primaryStage);
            grid.add(productPane, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
        return grid;
    }

    private BorderPane createProductPane(Product product, Stage primaryStage) {
        BorderPane pane = new BorderPane();
        ImageView imageView = new ImageView(new Image(product.getImagePath()));
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setOnMouseClicked(event -> showProductDetail(product, primaryStage));

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("$" + product.getPrice());

        pane.setTop(imageView);
        pane.setCenter(nameLabel);
        pane.setBottom(priceLabel);

        return pane;
    }

    private void showProductDetail(Product product, Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2F2F2; -fx-padding: 20px;");

        MenuBar menuBar = createMenuBar(primaryStage);
        root.setTop(menuBar);

        ImageView productImageView = new ImageView(new Image(product.getImagePath()));
        productImageView.setFitHeight(300);
        productImageView.setFitWidth(300);

        Label nameLabel = new Label("Product Name: " + product.getName());
        nameLabel.setStyle("-fx-font-size: 14px;");
        Label priceLabel = new Label("Price: $" + product.getPrice());
        priceLabel.setStyle("-fx-font-size: 14px;");
        Label descriptionLabel = new Label("Description: " + product.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px;");

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        addToCartButton.setOnAction(event -> addToCart(product, primaryStage));

        VBox productDetails = new VBox(10, productImageView, nameLabel, priceLabel, descriptionLabel, addToCartButton);
        productDetails.setPadding(new Insets(20));

        root.setCenter(productDetails);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addToCart(Product product, Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Add to Cart");
        dialog.setHeaderText("Add " + product.getName() + " to Cart");
        dialog.setContentText("Quantity:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quantityStr -> {
            int quantity = Integer.parseInt(quantityStr);
            shoppingCart.addProduct(product, quantity);
            showCustomerInformationForm(primaryStage);
        });
    }

    private void showCustomerInformationForm(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2F2F2; -fx-padding: 20px;");

        MenuBar menuBar = createMenuBar(primaryStage);
        root.setTop(menuBar);

        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-border-color: #D3D3D3; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 14px;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-padding: 5px; -fx-border-color: #BDC3C7; -fx-border-width: 1px;");
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-font-size: 14px;");
        TextField emailField = new TextField();
        emailField.setStyle("-fx-padding: 5px; -fx-border-color: #BDC3C7; -fx-border-width: 1px;");
        Label contactLabel = new Label("Contact No:");
        contactLabel.setStyle("-fx-font-size: 14px;");
        TextField contactField = new TextField();
        contactField.setStyle("-fx-padding: 5px; -fx-border-color: #BDC3C7; -fx-border-width: 1px;");
        Label addressLabel = new Label("Address:");
        addressLabel.setStyle("-fx-font-size: 14px;");
        TextArea addressField = new TextArea();
        addressField.setStyle("-fx-padding: 5px; -fx-border-color: #BDC3C7; -fx-border-width: 1px;");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String contact = contactField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()) {
                showErrorDialog("Give complete information to proceed order");
            } else {
                showOrderConfirmation(primaryStage, name, email, contact, address);
                try {
                    String record = "Name: " + name + ", Email: " + email + ", Contact: " + contact + ", Address: " + address + ", Cart: " + shoppingCart.toString();
                    connection.writeToFile(record);
                } catch (IOException e) {
                    showErrorDialog("Error saving order information");
                }
            }
        });

        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(emailLabel, 0, 1);
        formGrid.add(emailField, 1, 1);
        formGrid.add(contactLabel, 0, 2);
        formGrid.add(contactField, 1, 2);
        formGrid.add(addressLabel, 0, 3);
        formGrid.add(addressField, 1, 3);
        formGrid.add(submitButton, 1, 4);

        root.setCenter(formGrid);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showOrderConfirmation(Stage primaryStage, String name, String email, String contact, String address) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2F2F2; -fx-padding: 20px;");

        MenuBar menuBar = createMenuBar(primaryStage);
        root.setTop(menuBar);

        Label confirmationLabel = new Label("Order Confirmation");
        confirmationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label nameLabel = new Label("Name: " + name);
        nameLabel.setStyle("-fx-font-size: 14px;");
        Label emailLabel = new Label("Email: " + email);
        emailLabel.setStyle("-fx-font-size: 14px;");
        Label contactLabel = new Label("Contact No: " + contact);
        contactLabel.setStyle("-fx-font-size: 14px;");
        Label addressLabel = new Label("Address: " + address);
        addressLabel.setStyle("-fx-font-size: 14px;");

        VBox confirmationDetails = new VBox(10, confirmationLabel, nameLabel, emailLabel, contactLabel, addressLabel);
        confirmationDetails.setPadding(new Insets(20));

        root.setCenter(confirmationDetails);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Product> createSampleProducts(String category) {
        List<Product> products = new ArrayList<>();
        if (category.equalsIgnoreCase("cat")) {
            products.add(new Product("C1", "Reflex Chicken", "cat", "Reflex", 20.99, 10, "Delicious chicken cat food.",  "product1.jpeg"));
            products.add(new Product("C2", "Reflex Urinary", "cat", "Reflex", 22.99, 10, "Urinary health cat food.",  "product2.jpeg"));
            products.add(new Product("C3", "Reflex Adult", "cat", "Reflex", 19.99, 10, "Adult cat food.",  "product3.jpeg"));
            products.add(new Product("C4", "Reflex Lamb", "cat", "Reflex", 21.99, 10, "Tasty lamb cat food.", "product4.jpeg"));
            products.add(new Product("C5", "Reflex Gourmet", "cat", "Reflex", 25.99, 10, "Gourmet cat food.",  "product5.jpeg"));
            products.add(new Product("C6", "Reflex Sterilised", "cat", "Reflex", 24.99, 10, "Sterilised cat food.", "product6.jpeg"));
        } else if (category.equalsIgnoreCase("dog")) {
            products.add(new Product("D1", "Reflex Labrador", "dog","REFLEX",3500,12, "Healthy dog food", "file:C:/Users/Dell 3490/Downloads/PLUS-ADULT-DOG-FOOD-BEEF-590x800.jpg"));
            products.add(new Product("D2", "Reflex Retriever","dog","REFLEX",4000,29, "Healthy dog food", "file:C:/Users/Dell 3490/Downloads/Puppy-Food.png"));
            products.add(new Product("D3", "Reflex Puppy", "dog","REFLEX",5000, 45,"Healthy dog food", "file:C:/Users/Dell 3490/Downloads/DogLambAndRice.png"));
        }


        return products;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
