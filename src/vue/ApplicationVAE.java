import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ApplicationVAE extends Application{

    private Scene scene;
    
    @Override
    public void init() {

    }

    public void fenetreAccueil() {
        BorderPane root = new FenetreAccueil(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }

    public void fenetreRegiser() {
        BorderPane root = new FenetreInscription(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }


    @Override
    public void start(Stage stage) {
        BorderPane root = new FenetreConnexion(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene = new Scene(root,1800,1000);
        stage.setScene(this.scene);
        stage.setTitle("VAE - Groupe C");
        //Vue de base
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}