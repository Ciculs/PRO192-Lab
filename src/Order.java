
public class Order extends Customer extends Product{
    private int orderId;
    private String customer;
    private List<Product> products;
    private double totalAmount;
    
    public void Order(int orderId,Customer c){
        this.orderId=orderId;
        super(customerId,name,email);
        
    }
            
    public void addProduct(Product product,int quantity){
        if (quantity > product.getQuantityInStock()) {
            System.out.println("Not enough quantity in stock.");
        } else {
            product.setQuantityInStock(product.getQuantityInStock() - quantity);
            double totalAmount=this.calculateTotal();
            this.products.add(product);
            System.out.println("Product added to order.");
        }
    }
    
    public double calculateTotal(){
        return product.getPrice() * quantity;
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

