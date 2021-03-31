//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.ArrayList;

public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts,numSales;
  private String name;
  private ArrayList<Product> stock; //Array to hold all products
  private double revenue;
  private double dollarPerSale;
  private int cartCount;
  private ArrayList<Product> cart;
  private double cartCost;
  private Product[] popularProducts;
  private ArrayList<Product> soldProducts;
  
  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new ArrayList<>();
    curProducts = 0;
    numSales = 0;
    cartCount = 0;
    cart = new ArrayList<>();
    cartCost = 0;
    popularProducts = new Product[3];
    soldProducts = new ArrayList<>();
  }
  
  public String getName(){
    return name;
  }
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock.add(newProduct);
     curProducts++;
     return true;
    }
    return false;
  }
  

  //modified the origional method,
  //updates revenue, number of sales, clears the cart, calls the sellUnits on product and adds the products to
  //the sold product array
  public void sellProducts(){
    numSales++;
    revenue += cartCost;
    for (Product P:cart
         ) {
      P.sellUnits(P.getCartQuantity());
    }
    soldProducts.addAll(cart);
    cart.clear();
    cartCount=0;
    cartCost =0;
    }

//im sure theres a better way of doing this, if the TA marking this has time, please point me to a resourse in the comments.
  //THis is the source of the issue im pretty sure!
  public void findPopularProducts(){
    Product p1, p2, p3;

      if(soldProducts.size()==1){
        popularProducts[0]=soldProducts.get(0);
        popularProducts[1]=stock.get(1);
        popularProducts[2]=stock.get(2);
      }else if(soldProducts.size()==2){
        if(soldProducts.get(0).getSoldQuantity()>soldProducts.get(1).getSoldQuantity()){
          popularProducts[0] = soldProducts.get(0);
          popularProducts[1] = soldProducts.get(1);
          popularProducts[2]=stock.get(2);
        }else{
          popularProducts[0] = soldProducts.get(1);
          popularProducts[1] = soldProducts.get(0);
          popularProducts[2]=stock.get(2);
        }
      } else{
        for (Product p:soldProducts
             ) {
          if(p.getSoldQuantity()>=soldProducts.get(0).getSoldQuantity()){
            popularProducts[0]=p;
          }}
        for (Product p:soldProducts
             ) {
          if(p.getSoldQuantity()>=soldProducts.get(1).getSoldQuantity() && !p.equals(popularProducts[0])){
            popularProducts[1]=p;
          }
        }
        for (Product p:soldProducts
             ) {
          if(p.getSoldQuantity()>=soldProducts.get(2).getSoldQuantity() && !p.equals(popularProducts[0]) && !p.equals(popularProducts[1])){
            popularProducts[2] = p;
          }
        }

        }


//
//
//      p1 = soldProducts.get(0);
//
//      if(curProducts>2) {
//        p2 = stock.get(1);
//        p3 = stock.get(2);
//      }else {
//        p2 = popularProducts[1];
//        p3 = popularProducts[2];
//      }
//
//      if(numSales == 2){
//        if (soldProducts.get(0).getSoldQuantity() > soldProducts.get(1).getSoldQuantity()) {
//          p1 = soldProducts.get(0);
//          p2 = soldProducts.get(1);
//
//        }else{
//        p1 = soldProducts.get(1);
//        p2 = soldProducts.get(0);
//        }
//      }
//      if(numSales>=3){
//      for (Product p:soldProducts
//           ) {
//        if(p.getSoldQuantity()> p1.getSoldQuantity()){
//          p1 = p;
//        }else if(p.getSoldQuantity()> p2.getSoldQuantity()){
//          p2 = p;
//        }else if(p.getSoldQuantity()>p3.getSoldQuantity()){
//          p3 = p;
//        }
//      }}
//
//
//      popularProducts[0] = p1;
//      popularProducts[1] = p2;
//      popularProducts[2] = p3;

//    String[] sPopularProducts = new String[popularProducts.length];
//    for (int i = 0; i < popularProducts.length; i++) {
//      if(popularProducts[i] !=null){
//        sPopularProducts[i] = popularProducts[i].toString();
//      }
//    }
//    return sPopularProducts;

  }



  public void addToCart(Product p){
    if(p.getStockQuantity()>0) {
      if (!cart.contains(p)) {
        cartCount++;
        cart.add(p);
      }
      p.addedToCart();
      cartCost += p.getPrice();
      if (p.getStockQuantity()<1){
        stock.remove(p);
        curProducts --;
      }

    }
  }

  public void removeFromCart(Product p){
    if(!stock.contains(p)){
      curProducts ++;
      stock.add(p);
    }
    if (p.getCartQuantity()<2){
      cart.remove(p);
      cartCount--;
    }
    p.removedFromCart();
    cartCost -= p.getPrice();
  }


//get methods

  public ArrayList<Product> getSoldProducts() {return soldProducts;}

  public ArrayList<Product> getCart() {return cart; }
  public double getRevenue(){ return revenue;}
  public ArrayList<Product> getStock(){return stock;}
  public int getNumSales() { return numSales; }
  public double getDollarPerSale(){ //stops the method from destroying the universe by dividing by zero.
    if (numSales>0) {
      dollarPerSale = revenue / numSales;
      return dollarPerSale;
    }else{
      return 0;
    }
  }
  public double getCartCost() {return cartCost; }
  public int getCurProducts() { return curProducts; }
  public int getCartCount() { return cartCount; }
  public Product[] getPopularProducts() { return popularProducts; }

  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 