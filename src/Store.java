import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Product> products;
    private List<Order> orders;
    private int orderCnt = 0;
    private Scanner sc = new Scanner(System.in);

    public void setOrderCnt(int orderCnt) {
        this.orderCnt = orderCnt;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public Store(){
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public void addProduct(Product product){
        boolean check = false;
        for (Product x : products){
            if (x.getProductId() == product.getProductId()){
                int Current = product.getQuantity();
                x.updateQuantity(Current + x.getQuantity());
                check = true;
            }
        }  
        if (!check) products.add(product);
    }

    /// in ra cac product cho thang customer chon
    public Order createOrder(Customer customer){
        orderCnt += 1;
        Order res = new Order(orderCnt, customer);
        System.out.println("List of Products in store");
        for (Product x : products){
            System.out.println(x.getInfo());
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
                    Product nwProduct = new Product(x.getProductId(), x.getName(), x.getPrice(), Current);
                    res.addProduct(nwProduct, need);
                    if (Current >= need) x.updateQuantity(Current - need);
                    break;
                }
            }
        }
        
        /*for (int i = products.size() - 1; i >= 0; -- i){
            if (products.get(i).getQuantity() == 0){
                products.remove(i);
            }
        }*/
        
        orders.add(res);

        return res;
    }
    
    public List<Order> getAllOrders(){
        return orders;
    }
    
    public List<Product> getProducts(){
        return products;
    }
}