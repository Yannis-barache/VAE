import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData ;
import javafx.scene.control.ButtonType ;
import javafx.scene.text.FontWeight;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class Page_de_connec extends Application{
    private TextField nomUtilisateur;
    private PasswordField motDePasse;
    private Button connexion;
    private Button inscription;
    private Button quitter;
    private Button motDePasseOublie;


    public void init(){
        this.nomUtilisateur = new TextField();
        this.motDePasse = new PasswordField();
        this.connexion = new Button("Connexion");
        this.inscription = new Button("Inscription");
        this.quitter = new Button("Quitter");
        this.motDePasseOublie = new Button("Mot de passe oublié");

    }


    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setLeft(this.bandeBleue());
        fenetre.setCenter(this.);
        return new Scene(fenetre, 800, 1000);
    }

    public void start(Stage primaryStage) throws Exception{

    }

}
