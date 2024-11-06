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

    public int getQuantity(){
        return this.quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getInfo(){
        return "Product ID: " + this.productId + "Name: " + this.name + "Price: " + this.price + "Quantity: " + this.quantity;
    }
}
