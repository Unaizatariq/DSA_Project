import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String contactInfo;
    private String address;
    private List<String> purchaseHistory;

    // Constructor
    public Customer(String id, String name, String contactInfo, String address) {
        this.id = String.valueOf(id);
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.purchaseHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<String> getPurchaseHistory() { return purchaseHistory; }
    public void addPurchase(String purchase) { purchaseHistory.add(purchase); }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", address='" + address + '\'' +
                ", purchaseHistory=" + purchaseHistory +
                '}';
    }
}
