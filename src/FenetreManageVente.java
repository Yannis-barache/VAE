import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FenetreManageVente extends VBox {
    ApplicationVAE appli;
    Label alerte;

    public FenetreManageVente(ApplicationVAE appli){
        super();
        this.appli = appli;
        this.alerte = new Label();

        this.content();
    }


    /**
     * Création du contenu de la fenêtre de gestion des utilisateurs
     */
    private void content() {
        Label label=new Label("Gestion des Ventes");

        this.getChildren().add(label);
        this.setPadding(new Insets(10,10,10,10));
        List<Vente> ventes = new ArrayList<>();
        try{
           ventes = this.appli.getVenteBD().listeVentesEnCours();
        }catch (SQLException e){
           this.getChildren().add(new Label("Il n'y a pas d'utilisateurs dans la base de données"));
        }
        

        try{
            if (ventes.size()>0){
                for (Vente vente : ventes) {
                    GridPane saleContent = new GridPane();
                    saleContent.setAlignment(Pos.CENTER);
                    saleContent.setPadding(new Insets(10, 10, 10, 10));
                    saleContent.setStyle("-fx-background-color: #FFFFFF;");
                    saleContent.setEffect(new DropShadow());
                    saleContent.setHgap(10);
                    saleContent.setVgap(10);
        
        
                    ImageView ventePic=new ImageView();
                    try {
                        List<Photo> liste =this.appli.getPhotoBD().rechercherPhotosParObjet(vente.getObjet());
                        System.out.println(vente.getObjet().getLesPhotos());
                        for(Photo ph : liste){
                            vente.getObjet().ajoutePhoto(ph);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {
                        System.out.println(vente.getObjet().getLesPhotos());
                        ventePic = new ImageView(vente.getObjet().getLesPhotos().get(0).getImg());
                    } catch (Exception e) {
                        ventePic = new ImageView(new Image("file:./img/blank.png"));
                    }
        
                    Label titre = new Label(vente.getObjet().getNom());
                    Label description = new Label(vente.getObjet().getDescription());
                    Label tempsRestant = new Label("Temps restant : " + vente.tempsRestant());
                    Button delete = new Button("Supprimer");
        
                    delete.setOnAction(new ControleurAdminVente(this.appli, vente ));
        
                    ventePic.setFitHeight(100);
                    ventePic.setFitWidth(100);
                    saleContent.add(ventePic, 0, 0,2,3);
                    saleContent.add(titre, 2, 0);
                    saleContent.add(description, 2, 1);
                    saleContent.add(tempsRestant, 2, 2);
                    saleContent.add(delete, 0, 3,2,1);
        
                    this.getChildren().add(saleContent);
        
                }
            }
        } catch (IndexOutOfBoundsException ex){
            this.alerte.setText("Aucune vente");
        }






    }

    /**
     * Permet de changer le texte de l'alerte
     * @param alerte le texte de l'alerte
     */
    public void setAlerte(String alerte){
        this.alerte.setText(alerte);
    }

}
