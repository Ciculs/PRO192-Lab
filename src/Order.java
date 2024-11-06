
import java.util.List;

public class Order{
    private int orderId;
    private List<Product> products;
    private double totalAmount;
    Customer customer;
    
    public Order(int orderId, Customer customer){
        this.orderId=orderId;
    }
    
            
    public void addProduct(Product product,int quantity){
        if (quantity > product.getQuantity()) {
            System.out.println("Not enough quantity in stock.");
        } else {
            product.updateQuantity(product.getQuantity() - quantity);
            double totalAmount=this.calculateTotal(product);
            this.products.add(product);
            System.out.println("Product added to order.");
        }
    }
    
    public double calculateTotal(Product product){
        return product.getPrice() * product.getQuantity();
    }
    public String getOrderDetails(){
         StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Customer: ").append(customer.getName()).append("\n");
        details.append("Products:\n");

        for (Product product : products) {
            details.append(" - ").append(product.getName()).append("\n");
        }

        details.append("Total Amount: ").append(totalAmount).append("\n");
        return details.toString();
        
    }
}

