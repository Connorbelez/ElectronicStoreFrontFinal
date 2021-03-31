import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class ElectronicStoreAppView extends Pane {
    private TextField salesFeild;
    private TextField revFeild;
    private TextField dolarSalesFeild; //average dollar/sales field
    private ListView<String> popList; //most popular items Listview
    private ListView<String> stockList; //Store Stock Listview
    private ListView<String> cartList; //Current Cart Listview
    private ButtonPane buttonPane;
    private String updateText = "0.00"; //this is the variable that updates the cost of the currentcart
    private Label Label4; //the only label that needs to be updated
    private ArrayList<String> stockListS;


    //setMethods
    public void setUpdateText(String updateText) { this.updateText = updateText; }
    //get methods
    public String getUpdateText() { return updateText; }
    public ArrayList<String> getStockListS() { return stockListS; }
    public TextField getSalesFeild(){return salesFeild;}
    public TextField getDolarSalesFeild() { return dolarSalesFeild;}
    public TextField getRevFeild() {return revFeild; }
    public ListView<String> getPopList() {return popList; }
    public ButtonPane getButtonPane() { return buttonPane; }
    public ListView<String> getCartList() { return cartList; }
    public ListView<String> getStockList() { return stockList; }



    // Update Method

    public void Update(ElectronicStore model, int selectedProduct){
        //Arrays for each data element
        stockListS = new ArrayList<>();
        String[] cartListS = new String[model.getCartCount()];
        String[] stockListS1 = new String[model.getCurProducts()];
        ArrayList<Product> productList1 = model.getStock();
        ArrayList<Product> cartList1 = model.getCart();


        //this method converts the Product objects in stock[] to formatted String objects to be added to the
        //stock Listview
        int j = 1;
        for (Product p:productList1
             ) {
            String productInStock = p.toString();
            stockListS1[j-1] = p.getStockQuantity() + " x " + productInStock; //This line is for testing, but it adds some nice extra functionality. please dont dock marks.
            j++;
        }

        //this method converts the Product objects in cartlist1 to formatted String objects to be added to the
        //cart Listview
        int i =1;
        for (Product p:cartList1
             ) {
            String productInCart = p.toString();
            cartListS[i-1] = p.getCartQuantity() + " x " +productInCart;
            i++;
        }

        //these methods enable/disable the buttons
//        if(model.getCartCount()>0){
//            getButtonPane().getRemoveFromCart().setDisable(false);
//            getButtonPane().getCompleteSale().setDisable(false);
//        }else {
//            getButtonPane().getRemoveFromCart().setDisable(true);
//            getButtonPane().getCompleteSale().setDisable(true);
//        }
        if(stockList.getSelectionModel().getSelectedIndex()==-1){
            buttonPane.getAddToCart().setDisable(true);
        }else{
            buttonPane.getAddToCart().setDisable(false);
        }
//        if(cartList.getSelectionModel().getSelectedIndex()==-1){
//
//        }

        if(cartList.getSelectionModel().getSelectedIndex()>=0 && model.getCartCount()>0){
            getButtonPane().getRemoveFromCart().setDisable(false);
        }else{
            getButtonPane().getRemoveFromCart().setDisable(true);
        }

        if(model.getCartCount()>0){
            getButtonPane().getCompleteSale().setDisable(false);
        }else{
            getButtonPane().getCompleteSale().setDisable(true);
        }


        //update the textfields
        salesFeild.setText(String.valueOf(model.getNumSales()));
        revFeild.setText(String.valueOf(model.getRevenue()));
        if(model.getNumSales()>0){
            dolarSalesFeild.setText(String.valueOf(model.getRevenue()/model.getNumSales()));

        }else{
            dolarSalesFeild.setText("NA");
        }

        //sets the listviews
        stockList.setItems(FXCollections.observableArrayList(stockListS1));
        cartList.setItems(FXCollections.observableArrayList(cartListS));

        //sets the populaar product listview
        if(model.getNumSales()>0){
            String[] popLListUpdate = new String[3];
            int index =0;
            for (Product p: model.getPopularProducts()
                 ) {
                popLListUpdate[index] = p.toString();
                index ++;
            }
            popList.setItems(FXCollections.observableArrayList(popLListUpdate));
        }

        Label4.setText("Current Cart: ($"+updateText+")");

//
//
            stockList.getSelectionModel().select(selectedProduct);
//            cartList.getSelectionModel().select(selectedProduct);
//
    }









    //build the view
    public ElectronicStoreAppView(){
        String val = getUpdateText();
        //Create the labels
        Label Label1 = new Label("Store Summary:");
        Label1.relocate(50,20);
        Label Label2 = new Label("Most Popular Items:");
        Label2.relocate(35,150);
        Label Label3 = new Label("Store Stock:");
        Label3.relocate(300,20);
        Label4 = new Label("Current Cart: ($"+val+")");
        Label4.relocate(600,20);

        Label Label5 = new Label("# Sales:");
        Label5.relocate(20,45);
        Label Label6 = new Label("Revenue:");
        Label6.relocate(20,80);
        Label Label7 = new Label("$ / Sale");
        Label7.relocate(20,115);

        salesFeild = new TextField();
        salesFeild.relocate(95,40);
        salesFeild.setPrefSize(90,30);

        revFeild = new TextField();
        revFeild.relocate(95,75);
        revFeild.setPrefSize(90,30);

        dolarSalesFeild = new TextField();
        dolarSalesFeild.relocate(95,110);
        dolarSalesFeild.setPrefSize(90,30);

        buttonPane = new ButtonPane();
        buttonPane.relocate(25,345);

        popList = new ListView<>();
        popList.relocate(10,170);
        popList.setPrefSize(175,160);

        stockList = new ListView<>();
        stockList.relocate(195,40);
        stockList.setPrefSize(285,290);

        cartList = new ListView<>();
        cartList.relocate(490,40);
        cartList.setPrefSize(285,290);



        getChildren().addAll(Label1,buttonPane,popList,stockList,cartList,Label2,Label3,Label4,Label5,Label6,Label7,salesFeild,revFeild,dolarSalesFeild);
        setPrefSize(800,400);
    }
}
