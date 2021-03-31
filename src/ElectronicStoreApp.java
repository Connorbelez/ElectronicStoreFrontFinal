import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;



public class ElectronicStoreApp extends Application{
    ElectronicStore model;

    //constructor
    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage){
        Pane aPane = new Pane();
        //create the view
        ElectronicStoreAppView view1 = new ElectronicStoreAppView();


        //populate the initial values
        String[] stockList = new String[model.getCurProducts()];
        for (int i = 0; i < model.getCurProducts(); i++) {
            stockList[i] = model.getStock().get(i).toString();
        }
        view1.getStockList().setItems(FXCollections.observableArrayList(stockList));
        view1.getSalesFeild().setText(String.valueOf(model.getNumSales()));
        view1.getRevFeild().setText(String.valueOf(model.getRevenue()));


        //disable buttons
        ButtonPane buttonPane = view1.getButtonPane();
        buttonPane.getAddToCart().setDisable(true);
        view1.getButtonPane().getRemoveFromCart().setDisable(true);
        view1.getButtonPane().getCompleteSale().setDisable(true);


        //set the $/sale field
        if(model.getNumSales()>0){
        view1.getDolarSalesFeild().setText(String.valueOf(model.getDollarPerSale()));
        }else {
            view1.getDolarSalesFeild().setText("NA");
        }

        //Set up popular items listview
            String[] first3List = new String[3];
            for (int i = 0; i < 3; i++) {
                first3List[i] = stockList[i];
            }
            view1.getPopList().setItems(FXCollections.observableArrayList(first3List));

        aPane.getChildren().add(view1);


        //eventhandling//

        //StockList
        view1.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view1.getCartList().getSelectionModel().select(-1);
                int selectedIndex = view1.getStockList().getSelectionModel().getSelectedIndex();
                //updateview
                view1.getCartList().getSelectionModel().select(-1);
                view1.Update(model,selectedIndex);
            }
        });

        //cartlist
        view1.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                int selectedIndex = view1.getCartList().getSelectionModel().getSelectedIndex();
                //updateview
                view1.getStockList().getSelectionModel().select(-1);
                view1.Update(model,selectedIndex);
            }
        });

        //buttons
        view1.getButtonPane().getAddToCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                int selectedIndex = view1.getStockList().getSelectionModel().getSelectedIndex();
                view1.Update(model,selectedIndex);

                if(model.getStock().get(selectedIndex).getStockQuantity()<=1){
                    model.addToCart(model.getStock().get(selectedIndex));
                    view1.setUpdateText(String.valueOf(model.getCartCost()));
                    view1.getCartList().getSelectionModel().select(-1);
                    view1.getStockList().getSelectionModel().select(-1);
                    view1.Update(model,-1);
                }else {
                    model.addToCart(model.getStock().get(selectedIndex));
                    view1.setUpdateText(String.valueOf(model.getCartCost()));
                    view1.getCartList().getSelectionModel().select(-1);
                    view1.getStockList().getSelectionModel().select(selectedIndex);
                    view1.Update(model,selectedIndex);
                }
            }
        });

        view1.getButtonPane().getRemoveFromCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                int selectedIndex = view1.getCartList().getSelectionModel().getSelectedIndex();

                model.removeFromCart(model.getCart().get(selectedIndex));
                view1.setUpdateText(String.valueOf(model.getCartCost()));
                view1.getCartList().getSelectionModel().select(-1);
                view1.getStockList().getSelectionModel().select(-1);
                view1.Update(model,-1);
            }
        });
        view1.getButtonPane().getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {


                model = ElectronicStore.createStore();
                view1.setUpdateText("0");
                view1.getCartList().getSelectionModel().select(-1);
                view1.getStockList().getSelectionModel().select(-1);
                view1.Update(model,-1);
            }
        });
        view1.getButtonPane().getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.sellProducts();
                model.findPopularProducts();

                view1.setUpdateText("0");

                view1.getCartList().getSelectionModel().select(-1);
                view1.getStockList().getSelectionModel().select(-1);
                view1.Update(model,-1);

            }
        });


        primaryStage.setTitle("Electronics Store Application - "+model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

    }





    public static void main(String[] args) {
        launch(args);
    }
}
