import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class Main{
    private static Scanner sc = new Scanner(System.in);
    private static int pass = 123;
    private static int ID = 0; 
    private static Store store = new Store();
    private static int IDproduct = 0; 

    public static void main(String[] args) {
        while (true){
            System.out.println("Enter password: (1 if you are customer)");
            int s = Integer.parseInt(sc.nextLine());

            if (s == pass){
                System.out.print("1. Add Product\n2. Print Orders\n3. List Products\n");

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
            }
            else{
                if (s != 1) {
                    System.out.println("Wrong password");

                    String filePath = "output.txt";
                    try (FileWriter fw = new FileWriter(filePath, true);  // Mở ở chế độ "append"
                        BufferedWriter bw = new BufferedWriter(fw)) {
                        
                        String res = "";
                        for (Order x : store.getAllOrders()){
                            res += x.getOrderDetails() + "\n";
                        }

                        bw.write(res);
                        System.out.println("Ghi thêm vào file thành công!");
                    } catch (IOException e) {
                        System.out.println("Error.");
                    }
                    break;
                }

                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                ID += 1;
                Customer nwCustomer = new Customer(ID, name, email);
                
                Order nwOrder = store.createOrder(nwCustomer);
                System.out.println(nwOrder.getOrderDetails());  
            }
        }
    }   
}
