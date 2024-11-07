
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order{
    private int orderId;
    private List<Product> products;
    private double totalAmount;
    Customer customer;

    public Order(int orderId, List<Product> products, double totalAmount, Customer customer) {
        this.orderId = orderId;
        this.products = products;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }
    
    public Order(int orderId, Customer customer){
        this.orderId=orderId;
        this.customer = customer;
        products = new ArrayList<Product>();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public void addProduct(Product product,int quantity){
        if (quantity > product.getQuantity()) {
            System.out.println("Not enough quantity in stock.");
        } else {
            product.updateQuantity(quantity);
            this.totalAmount=calculateTotal(product);
            products.add(product);
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

        res += "Total Amount: " + this.totalAmount;
        
        return res;
        
    }
    
    public static List<Order> loadOrdersFromFile(String filename) {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int orderId = 0;
            double totalAmount = 0;
            Customer customer = null;
            List<Product> products = new ArrayList<>();

            while ((line = reader.readLine()) != null){
                if (line.startsWith("Order ID: ")){
                    if (orderId != 0) {
                        orders.add(new Order(orderId, new ArrayList<>(products), totalAmount, customer));
                        products.clear();
                    }

                    orderId = Integer.parseInt(line.split(",")[0].split(": ")[1].trim());
                    String[] customerInfo = line.split(", Customer ID: ")[1].split(", Name: ")[1].split(", Email: ");
                    int customerId = Integer.parseInt(line.split(", Customer ID: ")[1].split(",")[0].trim());
                    String name = customerInfo[0];
                    String email = customerInfo[1];
                    customer = new Customer(customerId, name, email);

                } 
                else if (line.startsWith("Product ID: ")){
                    Product product = Product.fromString(line);
                    products.add(product);

                } 
                else if (line.startsWith("Total Amount: ")){
                    totalAmount = Double.parseDouble(line.split(": ")[1].trim());
                }
            }

            if (orderId != 0){
                orders.add(new Order(orderId, products, totalAmount, customer));
            }

            System.out.println("Orders loaded from " + filename + " successfuly!");

        } 
        catch (IOException e){
            System.out.println("An error occurred while loading the orders.");
            e.printStackTrace();
        }

        return orders;
    }
}
