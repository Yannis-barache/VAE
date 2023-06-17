import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
public class ApplicationVAE extends Application{

    private Scene scene;
    private ScrollPane sc;
    
    @Override
    public void init() {

    }

     public void fenetreConnexion() {
        BorderPane root = new FenetreConnexion(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }

    public void fenetreRegiser() {
        BorderPane root = new FenetreInscription(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }

    public void fenetreAccueil() {
        BorderPane root = new BorderPane();
        root.setTop(new Menu(this,0));
        root.setCenter(new FenetreAccueil(this));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.sc = new ScrollPane(root);
         // this.scene = new Scene(new BorderPane(sc,null,null,null,null),400,400);
        this.scene.setRoot(sc);   
    }

    public void fenetreCreationVente() {
        BorderPane root = new BorderPane();
        this.sc = new ScrollPane(root);
        root.setCenter(new FenetreCreationVente(this));
        root.setTop(new Menu(this,1));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(sc);   
    }

    public void fenetreMesVentes() {
        BorderPane root = new BorderPane();
        this.sc = new ScrollPane(root);
        root.setCenter(new FenetreMesVentes(this));
        root.setTop(new Menu(this,2));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(sc);   
    }

    public void fenetreMesEncheres() {
        BorderPane root = new BorderPane();
        this.sc = new ScrollPane(root);
        root.setCenter(new FenetreMesEncheres(this));
        root.setTop(new Menu(this,3));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(sc);   
    }


    //...............;

    public void fenetreMonProfil() {
        BorderPane root = new BorderPane();
        this.sc = new ScrollPane(root);
        root.setCenter(new FenetreMonProfil(this));
        root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(sc);   
    }


    @Override
    public void start(Stage stage) {
        BorderPane root = new FenetreConnexion(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene = new Scene(root,1200,600);
        stage.setScene(this.scene);
        stage.setTitle("VAE - Groupe C");
        //Vue de base
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}