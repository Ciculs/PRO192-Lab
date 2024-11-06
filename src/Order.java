
import java.util.List;
import java.util.Scanner;

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
            totalAmount=this.calculateTotal(product);
            this.products.add(product);
            System.out.println("Product added to order.");
        }
    }
    
    public double calculateTotal(Product product){
        return product.getPrice() * product.getQuantity();
    }
    
    public String getOrderDetails(){
        String res = "";
        res += "Order ID: " + this.orderId + ", ";
        res += customer.getInfo() + ", ";
        res += "Products: \n";
        for (Product product : products) {
            res += product.getInfo() + "\n";
        }

        res += "Total Amount: " + totalAmount;
        
        return res;
        
    }
}

