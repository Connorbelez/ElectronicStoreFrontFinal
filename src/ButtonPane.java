import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class ButtonPane extends Pane {
    private Button resetStore,addToCart,removeFromCart,completeSale;

    public Button getAddToCart() { return addToCart; }
    public Button getCompleteSale() { return completeSale; }
    public Button getResetStore() { return resetStore; }
    public Button getRemoveFromCart() { return removeFromCart; }

    public ButtonPane(){
//        Pane innerPane = new Pane();

        //create the buttons
        resetStore = new Button("Reset Store");
        resetStore.setStyle("-fx-font: 12 arial;");
        resetStore.relocate(0, 0);
        resetStore.setPrefSize(140,45);

        addToCart = new Button("Add to Cart");
        addToCart.setStyle("-fx-font: 12 arial;");
        addToCart.relocate(250, 0);
        addToCart.setPrefSize(140,45);

        removeFromCart = new Button("Remove From Cart");
        removeFromCart.setStyle("-fx-font: 12 arial;");
        removeFromCart.relocate(480, 0);
        removeFromCart.setPrefSize(140,45);

        completeSale = new Button("Complete Sale");
        completeSale.setStyle("-fx-font: 12 arial;");
        completeSale.relocate(620, 0);
        completeSale.setPrefSize(140,45);




        getChildren().addAll(resetStore,addToCart,removeFromCart,completeSale);
        setPrefSize(750,55);


    }



}
