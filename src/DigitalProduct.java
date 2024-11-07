public class DigitalProduct extends Product{
    private double fileSize;

    public DigitalProduct(int productId, String name, double price, int quantity, double fileSize) {
        super(productId, name, price, quantity);
        this.fileSize = fileSize;
    }
    
    public String getInfo(){
        return super.getInfo() + " File Size: " + this.fileSize;
    }
}