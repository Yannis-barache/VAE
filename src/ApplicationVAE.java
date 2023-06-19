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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class ApplicationVAE extends Application{

    private Scene scene;
    private ScrollPane sc;
    private ConnexionMySQL laConnexion;
    private ScriptJDBC script;

    @Override
    public void init() {
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{
                laConnexion.connecter("servinfo-mariadb", "DBbarache", "barache", "barache");
                this.script= new ScriptJDBC(laConnexion);
    
            } catch (SQLException ex){
                System.out.println("Erreur SQL : " + ex.getMessage());
            }

        
        }
        catch(ClassNotFoundException ex){
            System.out.println("Erreur SQL : " + ex.getMessage());
            
        }


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
        ScrollPane sc = new ScrollPane(root);
        root.setTop(new Menu(this,0));
        root.setCenter(new FenetreAccueil(this));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        this.scene.setRoot(sc);   
    }

    public void fenetreCreationVente() {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreCreationVente(this));
        root.setTop(new Menu(this,1));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    public void fenetreMesVentes() {
        
        //TEST DE VENTES
        List<Map<String,String>> ventes = new ArrayList<Map<String,String>>();

        Map<String,String> v1 = new HashMap<String,String>();
        v1.put("id","43");
        v1.put("titre","Canapé repliable Castorama 180cm");
        v1.put("prixBase","180");      
        v1.put("nbEnchere","0");
        
        Map<String,String> v2 = new HashMap<String,String>();
        v2.put("id","10");
        v2.put("titre","Set Lego 7687");
        v2.put("prixBase","55,99");    
        v2.put("nbEnchere","3");

        ventes.add(v1);
        ventes.add(v2);

        //

        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreMesVentes(this,ventes));
        root.setTop(new Menu(this,2));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    public void fenetreEditionVente(Map<String,String> vente) {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreEditionVente(this,vente));
        root.setTop(new Menu(this,2));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    public void fenetreMesEncheres() {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreMesEncheres(this));
        root.setTop(new Menu(this,3));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }


    //...............;

    public void fenetreMonProfil() {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        sc.setPrefWidth(1920);
        root.setCenter(new FenetreMonProfil(this));
        root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    public ConnexionMySQL getConnexionMySQL() {
        return this.laConnexion;
    }

    public ScriptJDBC getScriptJDBC() {
        return this.script;
    }


    @Override
    public void start(Stage stage) {
        BorderPane root = new FenetreConnexion(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene = new Scene(root); //1200 600
        // stage.setMaximized(true);
        stage.setScene(this.scene);
        stage.setTitle("VAE - Groupe C");
        //Vue de base
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}