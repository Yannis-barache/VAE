import javafx.application.Application;
import javafx.application.Platform;
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

    /**
     * Scene principale de l'application
     */
    private Scene scene;

    /**
     * Le scrollPane de la scene
     */
    private ScrollPane sc;

    /**
     * La connexion à la base de données
     */
    private ConnexionMySQL laConnexion;

    /**
     * Des scripts pour accéder à la base de données
     */
    private ScriptJDBC script;

    /**
     * Les requêtes SQL concernant les utilisateurs
     */
    private UtilisateurBD utilisateurBD;

    /**
     * Les requêtes SQL concernant les statuts
     */
    private StatutBD statutBD;

    /**
     * Les requêtes SQL concernant les photos
     */
    private PhotoBD photoBD;

    /**
     * Les requêtes SQL concernant les objets
     */
    private ObjetBD objetBD;

    /**
     * Les requêtes SQL concernant les ventes
     */
    private VenteBD venteBD;

    /**
     * Les requêtes SQL concernant les enchères
     */
    private EnchereBD enchereBD;

    /**
     * Les requêtes SQL concernant les catégories
     */
    private CategorieBD categorieBD;

    /**
     * Un label notifiant l'utilisateur d'une inscription réussie
     */
    private Label notifReussie;

    /**
     * L'utilisateur connecté
     */
    private Utilisateur utilisateur;


    /**
     * Initialisation des attributs de l'application
     */
    @Override
    public void init() {

        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            this.notifReussie = new Label();
            try{
                laConnexion.connecter("servinfo-mariadb", "DBbarache", "barache", "barache");
                this.script = new ScriptJDBC(laConnexion);
                this.objetBD =new ObjetBD(laConnexion);
                this.utilisateurBD = new UtilisateurBD(laConnexion);
                this.statutBD = new StatutBD(laConnexion);
                this.photoBD = new PhotoBD(laConnexion);
                this.categorieBD = new CategorieBD(laConnexion);
                this.enchereBD = new EnchereBD(laConnexion);
                this.venteBD = new VenteBD(laConnexion);
    
            } catch (SQLException ex){
                System.out.println("Erreur SQL : " + ex.getMessage());
            }

        
        }
        catch(ClassNotFoundException ex){
            System.out.println("Erreur SQL : " + ex.getMessage());
            
        }
    }

    /**
     * Affiche la fenêtre de connexion
     */
     public void fenetreConnexion() {
        BorderPane root = new FenetreConnexion(this,this.notifReussie);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }

    /**
     * Affiche la fenêtre d'inscription
     */
    public void fenetreRegister() {
        BorderPane root = new FenetreInscription(this);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene.setRoot(root);
    }

    /**
     * Affiche la fenêtre d'accueil
     */
    public void fenetreAccueil() {
        List<Vente> ventesEnCours = new ArrayList<Vente>();
        try {
            ventesEnCours = this.venteBD.listeVentesEnCours(); //Get les ventes en cours à affiché dans l'accueil
        }
        catch(SQLException ex) {}

        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setTop(new Menu(this,0));
        root.setCenter(new FenetreAccueil(this,ventesEnCours));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);
        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

           
    }


    /**
     * Affiche la fenêtre de création d'une vente
     */
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


    /**
     * Affiche la fenêtre comportant les ventes de l'utilisateur connecté
     */
    public void fenetreMesVentes() {

        Utilisateur utilisateur = getUtilisateur();
        utilisateur.getVentes().clear();
        try {
            utilisateurBD.ventesUtilisateur(utilisateur); //Récupérer les ventes de la DB de l'utilisateur
        }
        catch(SQLException ex) {System.out.println(ex);}
        
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreMesVentes(this,utilisateur.getVentes()));
        root.setTop(new Menu(this,2));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    /**
     * Affiche la fenêtre de modification d'une vente
     * @param vente la vente à modifier
     */
    public void fenetreEditionVente(Vente vente) {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreEditionVente(this,vente));
        root.setTop(new Menu(this,2));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    /**
     * Affiche la fenêtre comportant les enchères de l'utilisateur connecté
     */
    public void fenetreMesEncheres() {

       Utilisateur utilisateur = getUtilisateur();
       utilisateur.getEncheres().clear();
        try {
            utilisateurBD.encheresUtilisateur(utilisateur); //Récupérer les ventes de la DB de l'utilisateur
        }
        catch(SQLException ex) {System.out.println(ex);}


        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreMesEncheres(this,utilisateur.getEncheres()));
        root.setTop(new Menu(this,3));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    /**
     * Affiche la fenêtre pour enchérir sur une vente
     * @param vente la vente sur laquelle on veut enchérir
     * @param precFenetre la fenêtre précédente (pour pouvoir revenir dessus)
     */
    public void fenetreEnchere(Vente vente,BorderPane precFenetre) {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        root.setCenter(new FenetreEnchere(this,vente,precFenetre));
        root.setTop(new Menu(this,3));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);   
    }

    /**
     * Affiche la fenêtre comportant les informations de l'utilisateur connecté
     */
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

    /**
     * Affiche la fenêtre du panel d'administration
     */
    public void fenetrePanelAdministration() {
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        sc.setPrefWidth(1920);
        root.setCenter(new FenetrePannelAdministration(this));
        root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        this.scene.setRoot(sc);
    }


    /**
     * Affiche la fenêtre permettant à l'administrateur de gérer les utilisateurs
     */
    public void fenetreManageUsers(){
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        sc.setPrefWidth(1920);
        root.setCenter(new FenetreManageUsers(this));
        root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        System.out.println("fenetreManageUsers6");
        sc.setFitToWidth(true);
        this.scene.setRoot(sc);
        System.out.println("fenetreManageUsers7");

    }


    /**
     * Affiche la fenêtre permettant à l'administrateur de gérer les ventes
     */
    public void fenetreManageSales(){
        BorderPane root = new BorderPane();
        ScrollPane sc = new ScrollPane(root);
        sc.setPrefWidth(1920);
        root.setCenter(new FenetreManageVente(this));
        root.setTop(new Menu(this,4));
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
      
        sc.setFitToWidth(true);
        this.scene.setRoot(sc);

    }


    /**
     * Renvoi la connexion à la base de données
     * @return la connexion à la base de données
     */
    public ConnexionMySQL getConnexionMySQL() {
        return this.laConnexion;
    }

    /**
     * Renvoi le script JDBC
     * @return le script JDBC
     */
    public ScriptJDBC getScriptJDBC() {
        return this.script;
    }

    /**
     * Renvoi l'utilisateur connecté
     * @return l'utilisateur connecté
     */
    public Utilisateur getUtilisateur() {
        return this.script.getUtilisateur();
    }

    /**
     * Renvoi l'attribut utilisateurBD pour pouvoir accéder à la base de données
     * @return l'attribut utilisateurBD
     */
    public UtilisateurBD getUtilisateurBD(){
        return this.utilisateurBD;
    }

    /**
     * Renvoi l'attribut objetBD pour pouvoir accéder à la base de données
     * @return l'attribut objetBD
     */
    public ObjetBD getObjetBD(){
        return this.objetBD;
    }

    /**
     * Renvoi l'attribut statutBD pour pouvoir accéder à la base de données
     * @return l'attribut statutBD
     */
    public StatutBD getStatutBD() {
        return this.statutBD;
    }

    /**
     * Renvoi l'attribut venteBD pour pouvoir accéder à la base de données
     * @return l'attribut venteBD
     */
    public VenteBD getVenteBD() {
        return this.venteBD;
    }

    /**
     * Renvoi l'attribut enchereBD pour pouvoir accéder à la base de données
     * @return l'attribut enchereBD
     */
    public EnchereBD getEnchereBD() {
        return this.enchereBD;
    }

    /**
     * Renvoi l'attribut categorieBD pour pouvoir accéder à la base de données
     * @return l'attribut categorieBD
     */
    public CategorieBD getCategorieBD() {
        return this.categorieBD;
    }

    /**
     * Renvoi l'attribut photoBD pour pouvoir accéder à la base de données
     * @return l'attribut photoBD
     */
    public PhotoBD getPhotoBD() {
        return this.photoBD;
    }

    /**
     * Permet de changer le texte de la notification réussie
     * @param notif le texte de la notification
     */
    public void setNotifReussie(String notif){
        this.notifReussie.setText(notif);
    }

    /**
     * Renvoi la notification réussie
     * @return la notification réussie
     */
    public Label getNotifReussie(){
        return this.notifReussie;
    }


    /**
     * Lance l'application
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        BorderPane root = new FenetreConnexion(this,this.notifReussie);
        root.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.scene = new Scene(root); //1200 600
        // stage.setMaximized(true);
        stage.setScene(this.scene);
        stage.setTitle("VAE - Groupe C");
        //Vue de base
        stage.show();
    }

    /**
     * Lance l'application
     * @param args les arguments (inutilisés)
     */
    public static void main(String[] args) {
        launch(args);
    }
}