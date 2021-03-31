//Base class for all products the store will sell
public class Product{
 private double price;
 private int stockQuantity;
 private int soldQuantity;
 private int cartQuantity;
 
 public Product(double initPrice, int initQuantity){
   price = initPrice;
   stockQuantity = initQuantity;
   cartQuantity =0;

 }

    public int getCartQuantity() {
        return cartQuantity;
    }
    public void setCartQuantity(int i){
     cartQuantity = i;
    }
    public int getStockQuantity(){
   return stockQuantity;
 }
    public int getSoldQuantity(){
        return soldQuantity;
    }
    public double getPrice(){
        return price;
    }

public void addedToCart(){
    cartQuantity++;
    stockQuantity--;
}

public void removedFromCart(){
     stockQuantity++;
     cartQuantity--;
}

//modified to simply update the sold quantity
 public void sellUnits(int amount){
     soldQuantity += amount;
     cartQuantity -= amount;
    }
}