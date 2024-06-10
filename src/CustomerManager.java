import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private Map<String, Customer> customers = new HashMap<>();

    // Add a customer
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    // Update a customer
    public void updateCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    // Delete a customer
    public void deleteCustomer(String customerId) {
        customers.remove(customerId);
    }

    // Search for a customer
    public Customer searchCustomer(String customerId) {
        return customers.get(customerId);
    }

    // Display customer details
    public void displayCustomerDetails(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Customer Details: " + customer);
        } else {
            System.out.println("Customer not found.");
        }
    }
}
