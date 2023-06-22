import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
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

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import javax.swing.text.LabelView;

import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreMonProfil extends GridPane {
    
    private ApplicationVAE appli;

    private TextField tfContentPseudo,tfContentMail,tfContentMDP;

    private Label tfContentNDV;
    private boolean modification = false;

    private Button button;
    private Button buttonSupprimer;

    private Label alerteErreur;

    public FenetreMonProfil(ApplicationVAE appli) {
        super();
        this.appli = appli;
        this.alerteErreur = new Label("");
        this.content();
    }

    private void content() {

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));
        
        GridPane gridPaneProfil = new GridPane();
        gridPaneProfil.setAlignment(Pos.TOP_CENTER);    
        gridPaneProfil.setHgap(50);
        gridPaneProfil.setVgap(50);
        gridPaneProfil.setStyle("-fx-background-color: white");

        Label labelTitre = new Label("Mon profil");
        labelTitre.setFont(Font.font("Verdana", FontWeight.BOLD, 35));

        Label labelPseudo = new Label("Pseudo : ");
        labelPseudo.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        Label labelMail = new Label("Mail : ");
        labelMail.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        Label labelMDP = new Label("Mot de passe : ");
        labelMDP.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        Label labelNDV = new Label("Nombre de vente : ");
        labelNDV.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        this.tfContentPseudo = new TextField(this.appli.getUtilisateur().getPseudo());
        this.tfContentPseudo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentPseudo.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentPseudo.setEditable(false);

        this.alerteErreur.setStyle("-fx-text-fill: red;");
    
        this.tfContentMail = new TextField(this.appli.getUtilisateur().getMail());
        this.tfContentMail.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentMail.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentMail.setEditable(false);

        this.tfContentMDP = new TextField(this.appli.getUtilisateur().getMdp());
        this.tfContentMDP.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentMDP.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentMDP.setEditable(false);

        this.tfContentNDV = new Label(""+this.appli.getUtilisateur().getVentes().size());
        this.tfContentNDV.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentNDV.setStyle("-fx-text-fill: #5D48D7;");

        
        this.button = new Button("Modifier");
        this.button.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.button.setPadding(new Insets(10,30,10,30));
        this.button.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.button.setOnAction(new ControleurBouton(appli, this));
        this.button.setEffect(ds);

        this.buttonSupprimer = new Button("Supprimer mon profil");
        this.buttonSupprimer.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.buttonSupprimer.setPadding(new Insets(10,30,10,30));
        this.buttonSupprimer.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.buttonSupprimer.setOnAction(new ControleurSupprimerUtilisateur(appli, this));
        this.buttonSupprimer.setEffect(ds);

        gridPaneProfil.add(labelTitre,0,0);
        gridPaneProfil.add(labelPseudo,0,2);
        gridPaneProfil.add(labelMail,0,3);
        gridPaneProfil.add(labelMDP,0,4);
        gridPaneProfil.add(labelNDV,0,5);
        gridPaneProfil.add(tfContentPseudo,1,2);
        gridPaneProfil.add(tfContentMail,1,3);
        gridPaneProfil.add(tfContentMDP,1,4);
        gridPaneProfil.add(tfContentNDV,1,5);
        gridPaneProfil.add(button,0,6);
        gridPaneProfil.add(alerteErreur,0,7);
        gridPaneProfil.add(buttonSupprimer,1,6);


        HBox container = new HBox(gridPaneProfil);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(150); 

        Pane centerPane = new Pane(container);
        centerPane.setPrefSize(400, 300);

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50)); 
        this.getChildren().add(centerPane);
        

    }
    
    public void setErreur(String alert){
        this.alerteErreur.setText(alert);
    }


    public String getPseudo(){
        return this.tfContentPseudo.getText();
    }

    public String getMail(){
        return this.tfContentMail.getText();
    }

    public String getMdp(){
        return this.tfContentMDP.getText();
    }

    public Button getButton(){
        return this.button;
    }


    public Button getButtonSupprimer(){
        return this.buttonSupprimer;
    }

    private void invBool(){
        this.modification=!this.modification;
    }

    public void modeTF(){
        this.invBool();
        this.tfContentMDP.setEditable(modification);
        this.tfContentMail.setEditable(modification);
        this.tfContentPseudo.setEditable(modification);
    }

    public boolean getModification(){
        return this.modification;
    }
}
