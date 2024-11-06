import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private List<Product> products;
    private List<Order> orders;
    private int orderCnt = 0;
    Scanner sc = new Scanner(System.in);

    public Store(){
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public void addProduct(Product product){
        products.add(product);
        for (Product x : products){
            if (x.getProductId() == product.getProductId()){
                int Current = product.getQuantity();
                product.updateQuantity(Current + 1);
            }
        }    
    }
    
    public void addProduct(Product product, int add){
        products.add(product);
        for (Product x : products){
            if (x.getProductId() == product.getProductId()){
                int Current = product.getQuantity();
                product.updateQuantity(Current + add);
            }
        }    
    }

    /// in ra cac product cho thang customer chon
    public Order createOrder(Customer customer){
        orderCnt += 1;
        Order res = new Order(orderCnt, customer);
        System.out.println("List of Products in store");
        for (Product x : products){
            System.out.println(x);
        }
        System.out.print("How many product do you need:");
        
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; ++ i){
            System.out.print("ID: ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Quantity: ");
            int need = Integer.parseInt(sc.nextLine());
            
            for (Product x : products){
                if (x.getProductId() == ID){
                    int Current = x.getQuantity();
                    if (Current < need){
                        System.out.println("Not enough quantity in stock.");
                        break;
                    }
                    res.addProduct(x, need);
                    break;
                }
            }
        }
        
        for (int i = products.size() - 1; i >= 0; -- i){
            if (products.get(i).getQuantity() == 0){
                products.remove(i);
            }
        }
        
        return res;
    }
    
    public List<Order> getAllOrders(){
        return orders;
    }
    
    public List<Product> getProducts(){
        return products;
    }
}
