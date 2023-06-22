import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData ;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class FenetreMesVentes extends BorderPane {
    
    private ApplicationVAE appli;
    private List<Vente> ventes;

    public FenetreMesVentes(ApplicationVAE appli,List<Vente> ventes) {
        super();
        this.appli = appli;
        this.ventes = ventes;

        this.content();
    }

    private void content() {
        
        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Ventes
        VBox ventesContent = new VBox(100);

        //Titre
        HBox titleContent = new HBox();
        Label title = new Label("Mes ventes : ");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        Label countVente = new Label(String.valueOf(this.ventes.size()));
        countVente.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        countVente.setTextFill(Color.web("black"));
        titleContent.getChildren().addAll(title,countVente);
        //Ajout du titre
        ventesContent.getChildren().add(titleContent);



        if (this.ventes.size() > 0) {
            for (Vente vente : this.ventes) {

                VBox venteContent = new VBox();
                HBox splitVente = new HBox();

                VBox leftSide = new VBox();

                //et image (gauche) 
                ImageView ventePic ;
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
                    System.out.println("erreur");
                    ventePic = new ImageView(new Image("file:./img/blank.png"));
                }
                ventePic.setFitHeight(350);
                ventePic.setPreserveRatio(true);    

                leftSide.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 3 0 0");
                leftSide.setPadding(new Insets(0,30,0,0));
                leftSide.maxWidth(300);
                leftSide.getChildren().addAll(ventePic);

                //Informations (droite)
                GridPane rightSide = new GridPane();

                //Titre
                Label venteTitle = new Label(vente.getObjet().getNom());
                venteTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                venteTitle.setTextFill(Color.web("#5D48D7"));  
                venteTitle.setPadding(new Insets(0,0,35,0));

                //Prix actuel
                Label actualPriceLabel = new Label("Prix actuel : ");
                actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPriceLabel.setTextFill(Color.web("black"));
                int actualPriceValue = vente.getPrixBase();
                try {
                    Enchere dernierEnchere = this.appli.getVenteBD().derniereEnchere(vente);
                    if (dernierEnchere != null){
                        actualPriceValue = dernierEnchere.getMontant();
                    }
                }
                catch(SQLException ex) {}
                Label actualPrice = new Label(String.valueOf(actualPriceValue));
                actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPrice.setTextFill(Color.web("#5D48D7"));  
                actualPrice.setAlignment(Pos.BASELINE_RIGHT);

                //Temps restant
                Label remainTimeLabel = new Label("Temps restant : ");
                remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTimeLabel.setTextFill(Color.web("black"));
                Label remainTime = new Label(vente.tempsRestant());
                remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTime.setTextFill(Color.web("#5D48D7"));  
                remainTime.setAlignment(Pos.BASELINE_RIGHT);

                //Nombre enchères
                Label nbEnchereslabel = new Label("Nombre d'enchère : ");
                nbEnchereslabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                nbEnchereslabel.setTextFill(Color.web("black"));
                int encheresCount = 0;
                try {
                    encheresCount = this.appli.getVenteBD().nbTotalEnchereSurUneVente(vente);
                }
                catch(SQLException ex) {
                }
                Label nbEncheres = new Label(String.valueOf(encheresCount));
                nbEncheres.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                nbEncheres.setTextFill(Color.web("#5D48D7"));
                nbEncheres.setAlignment(Pos.BASELINE_RIGHT);

                //Bouton
                Tooltip saleToolTip = new Tooltip(String.valueOf(vente.getIdentifiant()));
                VBox editContent = new VBox();
                Button edit = new Button("Modifier");
                edit.setTooltip(saleToolTip);
                Tooltip.uninstall(edit, saleToolTip);
                edit.setEffect(ds);
                edit.setOnAction((key) -> this.appli.fenetreEditionVente(getVente(saleToolTip.getText())));
                edit.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                edit.setPadding(new Insets(10,30,10,30));
                edit.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
                edit.setAlignment(Pos.BASELINE_RIGHT);
                editContent.setPadding(new Insets(28,0,0,0));
                editContent.getChildren().add(edit);

                //Placement dans les informations (partie de droite)
                rightSide.add(venteTitle,0,0,3,1);
                rightSide.add(actualPriceLabel,0,1,1,1);
                rightSide.add(actualPrice,2,1,1,1);
                rightSide.add(remainTimeLabel,0,2,1,1);
                rightSide.add(remainTime,2,2,1,1);
                rightSide.add(nbEnchereslabel,0,3,1,1);
                rightSide.add(nbEncheres,2,3,1,1);
                rightSide.add(editContent,0,4,2,1);
                rightSide.setMaxWidth(700);
                rightSide.setPadding(new Insets(0,0,0,30));
                rightSide.setHgap(30);
                rightSide.setVgap(30);


                //Placement dans le container de vente
                splitVente.setPadding(new Insets(30));
                splitVente.getChildren().addAll(leftSide,rightSide);
                
                venteContent.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
                venteContent.setAlignment(Pos.CENTER);
                venteContent.getChildren().addAll(splitVente);
                venteContent.setEffect(ds);

                ventesContent.setPadding(new Insets(50,200,200,200));
                ventesContent.getChildren().add(venteContent);
            }
            this.setCenter(ventesContent);
        } else {
            VBox emptyVentesContent = new VBox(10);
            Label emptyVentes = new Label("Vous n'avez pas de vente en cours");
            emptyVentes.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            emptyVentes.setTextFill(Color.web("black")); 
            Button createVente = new Button("Mettre en ligne une vente maintenant !");
            createVente.setOnMouseEntered(event -> {
                createVente.setCursor(Cursor.HAND);
            });
            createVente.setOnAction((key) -> this.appli.fenetreCreationVente());
            createVente.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            createVente.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
            createVente.setTextFill(Color.web("#5D48D7"));

            emptyVentesContent.setAlignment(Pos.CENTER);
            emptyVentesContent.setPadding(new Insets(100));
            emptyVentesContent.getChildren().addAll(emptyVentes,createVente);

            this.setCenter(emptyVentesContent);
        }
    }

    private Vente getVente(String id) {
        for (Vente vente : this.ventes) {
            if (String.valueOf(vente.getIdentifiant()).equals(id)) {
                return vente;
            }
        }
        return null;
    }
}