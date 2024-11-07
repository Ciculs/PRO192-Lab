import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main{
    private static Scanner sc = new Scanner(System.in);
    private static int pass = 123;
    private static int ID = 0; 
    private static Store store = new Store();
    private static int IDproduct = 0; 
    private static List<String> account = new ArrayList<>();
    private static List<String> password = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static int numAcc = 0;

    private static void Master(){
        System.out.println("Welcome Master! What do you wanna do today?");
        System.out.println("1. Add Product\n2. Print Orders\n3. List Products\n4. Exit");

        int k = Integer.parseInt(sc.nextLine());
        if (k == 1){
            System.out.print("How many products do you want to add: ");
            int n = Integer.parseInt(sc.nextLine());

            //IDproduct += 1;
            for (int i = 0; i < n; ++ i){
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Product Price: ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.print("Enter Product Quantity: ");
                int quantity = Integer.parseInt(sc.nextLine());
                Product nwProduct = new Product(i + 1, name, price, quantity);
                store.addProduct(nwProduct);
            }
        }

        if (k == 2){
            for (Order x : store.getAllOrders()){
                System.out.println(x.getOrderDetails());
            }
        }

        if (k == 3){
            for (Product x : store.getProducts()){
                System.out.println(x.getInfo());
            }
        }
        
        if (k == 4) return;
    }
    
    private static void customer(int x){
        System.out.println("Welcome user " + customers.get(x).getName());
//        System.out.print("Enter Name: ");
//        String name = sc.nextLine();
//        System.out.print("Enter Email: ");
//        String email = sc.nextLine();
//        ID += 1;
        Customer nwCustomer = customers.get(x);
        
        while (true){
            System.out.println("What do you wanna do?");
            System.out.println("1. Buy something");
            System.out.println("2. Generate invoice");
            System.out.println("3. Exit");
            
            int type = Integer.parseInt(sc.nextLine());
            
            if (type == 1){
                Order nwOrder = store.createOrder(nwCustomer);
            }
            else if (type == 2){
                List<Order> invoices = new ArrayList<>();
                for (Order z : store.getAllOrders()){
                    if (z.getCustomer().getCustomerId() == nwCustomer.getCustomerId()){
                        invoices.add(z);
                    }
                }
                
                for (Order z : invoices){
                    System.out.println(z.getOrderDetails());
                }
            }
            else break;
        }
            //System.out.println(nwOrder.getOrderDetails());
        
        System.out.println("Thanks for your present!");
    }
    
    private static void loadAccountFromFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int turn = 0;
            while ((line = reader.readLine()) != null){
                if (turn == 0){
                    numAcc += 1;
                    account.add(line);
                }
                else{
                    password.add(line);
                }
                turn ^= 1;
            }

            System.out.println("Accounts loaded from " + filename + " successfuly!");

        } 
        catch (IOException e){
            System.out.println("An error occurred while loading the Accounts.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
       
        int CurrentOrderID = 0;
        store.setOrders(Order.loadOrdersFromFile("src/order.txt"));
        for (Order x : store.getAllOrders()){
//            System.out.println(x.getOrderDetails());
            CurrentOrderID = x.getOrderId() > CurrentOrderID ? x.getOrderId() : CurrentOrderID;
        }
        store.setOrderCnt(CurrentOrderID);
        
        store.setProducts(Product.loadProductsFromFile("src/product.txt"));
//        for (Product x : store.getProducts()){
//            System.out.println(x.getInfo());
//        }
        
        loadAccountFromFile("src/account.txt");
        
//        System.out.println(account.size());
//        System.out.println(password.size());
//        System.out.println(numAcc);
        
//        for (int i = 0; i < numAcc; ++ i){
//           System.out.println(account.get(i));
//            System.out.println(password.get(i));
//        }
        
        customers = Customer.loadCustomerFromFile("src/customer.txt");
        
//        for (Customer x : customers){
//            System.out.println(x.getInfo());
//        }
        
        while (true){
            System.out.println("    Welcome!    ");
            System.out.println("What do you wanna do?");
            System.out.println("1. Login");
            System.out.println("2. Create new account");
            System.out.println("3. Exit");
            
            int x = Integer.parseInt(sc.nextLine());
            
            if (x == 1){
                int success = -1;
                for (int _ = 1; _ <= 2; ++ _){
                    System.out.print("Enter Acc: ");
                    String Acc = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String Pass = sc.nextLine();
                    for (int i = 0; i < numAcc; ++ i){
                        if (Acc.equals(account.get(i)) && Pass.equals(password.get(i))){
                            success = i;
                            break;
                        }
                    }
                    if (success != -1) break;
                }
                if (success == -1){
                    System.out.println("Seem like you dont have an account!");
                    continue;
                }
                
                System.out.println("Login successfully!");
                if (success == 0){
                    Master();
                }
                else{
                    customer(success);
                }
            }
            else if (x == 2){
                numAcc += 1;
                System.out.print("Enter your Name: ");
                String name = sc.nextLine();
                System.out.print("Enter your Gmail: ");
                String gmail = sc.nextLine();
                System.out.print("Enter your Account: ");
                String Acc = sc.nextLine();
                System.out.print("Enter your Password: ");
                String Pass = sc.nextLine();
                account.add(Acc);
                password.add(Pass);
                customers.add(new Customer(numAcc - 1, name, gmail));
                System.out.println("Create account successfully!");
                //System.out.println(numAcc - 1 + " " + customers.size());
                customer(numAcc - 2);
            }
            else{
                writeFileAccount("src/account.txt");
                writeFileCustomer("src/customer.txt");
                writeFileProduct("src/product.txt");
                writeFileOrder("src/order.txt");
                break;
            }
            
        }
    }   

    private static void writeFileAccount(String filename) {
        try (FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw)) {

            String s = "";
            for (int i = 0; i < numAcc; ++ i){
                s += account.get(i) + "\n" + password.get(i);
                if (i != numAcc - 1) s += "\n";
            }
            
            bw.write(s);
            System.out.println("Write to " + filename + " successfully!");
        } catch (IOException e) {
            System.out.println("Something went wrong when try to write to " + filename);
        }
    }
    
    private static void writeFileCustomer(String filename) {
        try (FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw)) {

            String s = "";
            for (int i = 0; i < numAcc; ++ i){
                s += customers.get(i).getInfo();
                if (i != numAcc - 1) s += "\n";
            }
            
            bw.write(s);
            System.out.println("Write to " + filename + " successfully!");
        } catch (IOException e) {
            System.out.println("Something went wrong when try to write to " + filename);
        }
    }
    
    private static void writeFileOrder(String filename) {
        try (FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw)) {

            String s = "";
            List<Order> orders = store.getAllOrders();
            for (int i = 0; i < orders.size(); ++ i){
                s += orders.get(i).getOrderDetails();
                if (i != orders.size() - 1) s += "\n";
            }
            
            bw.write(s);
            System.out.println("Write to " + filename + " successfully!");
        } catch (IOException e) {
            System.out.println("Something went wrong when try to write to " + filename);
        }
    }
    
    private static void writeFileProduct(String filename) {
        try (FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw)){

            String s = "";
            List<Product> products = store.getProducts();
            for (int i = 0; i < products.size(); ++ i){
                s += products.get(i).getInfo();
                if (i != products.size() - 1) s += "\n";
            }
            
            bw.write(s);
            System.out.println("Write to " + filename + " successfully!");
        } catch (IOException e) {
            System.out.println("Something went wrong when try to write to " + filename);
        }
    }
    
    
}