
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private String email;

    public String getName() {
        return name;
    }
    
    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public String getInfo(){
        return "Customer ID: " + this.customerId + ", Name: " + this.name + ", Email: " + this.email;
    }
    
    public static List<Customer> loadCustomerFromFile(String filename){
        List<Customer> customers = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] customerInfo = line.split("Customer ID: ")[1].split(", Name: ")[1].split(", Email: ");
                int customerId = Integer.parseInt(line.split("Customer ID: ")[1].split(",")[0].trim());
                String name = customerInfo[0];
                String email = customerInfo[1];
                customers.add(new Customer(customerId, name, email));
            }

            System.out.println("Customers loaded from " + filename + " successfuly!");

        } 
        catch (IOException e){
            System.out.println("An error occurred while loading the Customers.");
            e.printStackTrace();
        }
        
        return customers;
    }
    
}