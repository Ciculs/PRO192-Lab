
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int quantity;

    public Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    public int setId(int productId){
        return this.productId = productId;
    }

    public double getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getInfo(){
        return "Product ID: " + this.productId + "\tName: " + this.name + "\tPrice: " + this.price + "\tQuantity: " + this.quantity;
    }
    
    // Method to create a Product from a formatted string
    public static Product fromString(String productString) {
        // Split the input string by tabs or spaces
        String[] parts = productString.split("\\t");
        
        // Parse each field
        int productId = Integer.parseInt(parts[0].split(": ")[1].trim());
        String name = parts[1].split(": ")[1].trim();
        double price = Double.parseDouble(parts[2].split(": ")[1].trim());
        int quantity = Integer.parseInt(parts[3].split(": ")[1].trim());

        // Return a new Product object
        return new Product(productId, name, price, quantity);
    }
    
    public static List<Product> loadProductsFromFile(String filename) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                products.add(fromString(line));
            }

            System.out.println("Products loaded from " + filename + " successfuly!");

        } 
        catch (IOException e){
            System.out.println("An error occurred while loading the products.");
            e.printStackTrace();
        }

        return products;
    }
}