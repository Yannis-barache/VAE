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

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

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
        this.sc = new ScrollPane(root);
        root.setTop(new Menu(this,0));
        root.setCenter(new FenetreAccueil(this));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
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
        
        //TEST DE VENTES
        List<List<Map<String,String>>> ventes = new ArrayList<List<Map<String,String>>>();

        List<Map<String,String>> v1 = new ArrayList<Map<String,String>>();
        Map<String,String> tv1 = new HashMap<>();
        tv1.put("titre","Canapé repliable Castorama 180cm");
        Map<String,String> pbv1 = new HashMap<>();
        pbv1.put("prixBase","180");
        v1.add(tv1);
        v1.add(pbv1);

        List<Map<String,String>> v2 = new ArrayList<Map<String,String>>();
        Map<String,String> tv2 = new HashMap<>();
        tv2.put("titre","Set Lego8799 de 2008");
        Map<String,String> pbv2 = new HashMap<>();
        pbv2.put("prixBase","50.50");
        v2.add(tv2);
        v2.add(pbv2);

        ventes.add(v1);
        ventes.add(v2);

        // System.out.println(ventes.get(0).get(0).get("titre"));
        //

        BorderPane root = new BorderPane();
        this.sc = new ScrollPane(root);
        root.setCenter(new FenetreMesVentes(this,ventes));
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