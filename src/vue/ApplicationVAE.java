import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ApplicationVAE extends Application{
    
    @Override
    public void init() {

    }

    private Scene Scene() {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        // root.setTop();
        root.setCenter(new FenetreConnexion());
        return new Scene(root,1800,1000);   
    }









    
    @Override
    public void start(Stage stage) {
        stage.setTitle("VAE - Groupe C");
        stage.setScene(this.Scene());
        //Vue de base
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}