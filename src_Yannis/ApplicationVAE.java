import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.sql.*;

public class ApplicationVAE extends Application{

    private Scene scene;
    // private ScrollPane sc;
    // private ConnexionMySQL laConnexion;
    // private ScriptJDBC script;
    // private UtilisateurBD utilisateurBD;
    // private StatutBD statutBD;
    // private PhotoBD photoBD;
    // private ObjetBD objetBD;
    // private VenteBD venteBD;
    // private EnchereBD enchereBD;
    // private CategorieBD categorieBD;
    // private Label notifReussie;
    // private Utilisateur utilisateur;


    @Override
    public void init() {
        // try{
        //     ConnexionMySQL laConnexion= new ConnexionMySQL();
        //     this.notifReussie = new Label();
        //     try{
        //         laConnexion.connecter("localhost", "VAE", " ", "");
        //         this.script = new ScriptJDBC(laConnexion);
        //         this.objetBD =new ObjetBD(laConnexion);
        //         this.utilisateurBD = new UtilisateurBD(laConnexion);
        //         this.statutBD = new StatutBD(laConnexion);
        //         this.photoBD = new PhotoBD(laConnexion);
        //         this.categorieBD = new CategorieBD(laConnexion);
        //         this.enchereBD = new EnchereBD(laConnexion);
        //         this.venteBD = new VenteBD(laConnexion);

        //     } catch (SQLException ex){
        //         System.out.println("Erreur SQL : " + ex.getMessage());
        //     }


        // }
        // catch(ClassNotFoundException ex){
        //     System.out.println("Erreur SQL : " + ex.getMessage());

        // }
    }

    // public void fenetreConnexion() {
    //     BorderPane root = new FenetreConnexion(this,this.notifReussie);
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     this.scene.setRoot(root);
    // }

    // public void fenetreRegiser() {
    //     BorderPane root = new FenetreInscription(this);
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     this.scene.setRoot(root);
    // }

    // public void fenetreAccueil() {
    //     List<Vente> ventesEnCours = new ArrayList<Vente>();
    //     // try {
    //     //     ventesEnCours = this.venteBD.listeVentesEnCours(); //Get les ventes en cours à affiché dans l'accueil
    //     // }
    //     // catch(SQLException ex) {}

    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setTop(new Menu(this,0));
    //     root.setCenter(new FenetreAccueil(this,ventesEnCours));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setVvalue(0.1);
    //     sc.setHvalue(1.5);
    //     this.scene.setRoot(sc);

    // }

    // public void fenetreCreationVente() {
    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setCenter(new FenetreCreationVente(this));
    //     root.setTop(new Menu(this,1));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setFitToHeight(true);
    //     //Augmenter la vitesse de scroll
    //     sc.setVvalue(0.5);
    //     sc.setHvalue(0.5);

    //     this.scene.setRoot(sc);
    // }

    // public void fenetreMesVentes() {

    //     Utilisateur utilisateur = getUtilisateur();
    //     // utilisateur.getVentes().clear();
    //     try {
    //         utilisateurBD.ventesUtilisateur(utilisateur); //Récupérer les ventes de la DB de l'utilisateur
    //     }
    //     catch(SQLException ex) {System.out.println(ex);}

    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setCenter(new FenetreMesVentes(this,utilisateur.getVentes()));
    //     root.setTop(new Menu(this,2));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setFitToHeight(true);
    //     this.scene.setRoot(sc);
    // }

    // public void fenetreEditionVente(Vente vente) {
    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setCenter(new FenetreEditionVente(this,vente));
    //     root.setTop(new Menu(this,2));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setFitToHeight(true);
    //     this.scene.setRoot(sc);
    // }

    // public void fenetreMesEncheres() {

    //     Utilisateur utilisateur = getUtilisateur();
    //     utilisateur.getEncheres().clear();
    //     try {
    //         utilisateurBD.encheresUtilisateur(utilisateur); //Récupérer les ventes de la DB de l'utilisateur
    //     }
    //     catch(SQLException ex) {System.out.println(ex);}


    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setCenter(new FenetreMesEncheres(this,utilisateur.getEncheres()));
    //     root.setTop(new Menu(this,3));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setFitToHeight(true);
    //     this.scene.setRoot(sc);
    // }

    // public void fenetreEnchere(Vente vente,String precFenetre) {
    //     BorderPane root = new BorderPane();
    //     ScrollPane sc = new ScrollPane(root);
    //     root.setCenter(new FenetreEnchere(this,vente,precFenetre));
    //     root.setTop(new Menu(this,3));
    //     root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     sc.setFitToWidth(true);
    //     sc.setFitToHeight(true);
    //     this.scene.setRoot(sc);
    // }

    // public void fenetreMonProfil() {
    //     // BorderPane root = new BorderPane();
    //     // ScrollPane sc = new ScrollPane(root);
    //     // sc.setPrefWidth(1920);
    //     // root.setCenter(new FenetreMonProfil(this));
    //     // root.setTop(new Menu(this,4));
    //     // root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     // sc.setFitToWidth(true);
    //     // sc.setFitToHeight(true);
    //     // this.scene.setRoot(sc);
    // }

    // public void fenetrePannelAdministration() {
    //     // BorderPane root = new BorderPane();
    //     // ScrollPane sc = new ScrollPane(root);
    //     // sc.setPrefWidth(1920);
    //     // root.setCenter(new FenetrePannelAdministration(this));
    //     // root.setTop(new Menu(this,4));
    //     // root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
    //     // sc.setFitToWidth(true);
    //     // sc.setFitToHeight(true);
    //     // this.scene.setRoot(sc);
    // }

    public void fenetreManageUsers(){
        System.out.println("fenetreManageUsers");
        BorderPane root = new BorderPane();
        System.out.println("fenetreManageUsers2");
        ScrollPane sc = new ScrollPane(root);
        System.out.println("fenetreManageUsers3");
        sc.setPrefWidth(1920);
        System.out.println("fenetreManageUsers4");
        root.setCenter(new FenetreManageUsers(this));
        System.out.println("fenetreManageUsers5");
        // root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        System.out.println("fenetreManageUsers6");
        sc.setFitToWidth(true);
        this.scene.setRoot(sc);
        System.out.println("fenetreManageUsers7");

    }

    public void fenetreManageSales(){
        System.out.println("fenetreManageSales");
        BorderPane root = new BorderPane();
        System.out.println("fenetreManageSales2");
        ScrollPane sc = new ScrollPane(root);
        System.out.println("fenetreManageSales3");
        sc.setPrefWidth(1920);
        System.out.println("fenetreManageSales4");
        root.setCenter(new FenetreManageVente(this));
        System.out.println("fenetreManageSales5");
        // root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        System.out.println("fenetreManageSales6");
        sc.setFitToWidth(true);
        this.scene.setRoot(sc);
        System.out.println("fenetreManageSales7");

    }

    // public ConnexionMySQL getConnexionMySQL() {
    //     return this.laConnexion;
    // }

    // public ScriptJDBC getScriptJDBC() {
    //     return this.script;
    // }

    // public Utilisateur getUtilisateur() {
    //     return this.script.getUtilisateur();
    // }

    // public UtilisateurBD getUtilisateurBD(){
    //     return this.utilisateurBD;
    // }

    // public ObjetBD getObjetBD(){
    //     return this.objetBD;
    // }

    // public StatutBD getStatutBD() {
    //     return this.statutBD;
    // }

    // public VenteBD getVenteBD() {
    //     return this.venteBD;
    // }

    // public EnchereBD getEnchereBD() {
    //     return this.enchereBD;
    // }

    // public CategorieBD getCategorieBD() {
    //     return this.categorieBD;
    // }

    // public PhotoBD getPhotoBD() {
    //     return this.photoBD;
    // }

    // public void setNotifReussie(String notif){
    //     this.notifReussie.setText(notif);
    // }

    // public Label getNotifReussie(){
    //     return this.notifReussie;
    // }

    




    @Override
    public void start(Stage stage) {

        BorderPane root = new FenetrePannelAdministration(this);
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