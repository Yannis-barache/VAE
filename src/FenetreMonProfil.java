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

import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreMonProfil extends GridPane {
    
    private ApplicationVAE appli;

    private TextField tfContentPseudo,tfContentMail,tfContentMDP,tfContentNDV;

    private boolean modification = true;

    private Button button;

    public FenetreMonProfil(ApplicationVAE appli) {
        super();
        this.appli = appli;

        this.content();
    }

    private void content() {
        
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
        this.tfContentPseudo.setEditable(modification);
 
        this.tfContentMail = new TextField(this.appli.getUtilisateur().getMail());
        this.tfContentMail.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentMail.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentMail.setEditable(modification);

        this.tfContentMDP = new TextField(this.appli.getUtilisateur().getMdp());
        this.tfContentMDP.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentMDP.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentMDP.setEditable(modification);

        this.tfContentNDV = new TextField(""+this.appli.getUtilisateur().getVentes().size());
        this.tfContentNDV.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.tfContentNDV.setStyle("-fx-text-fill: #5D48D7;");
        this.tfContentNDV.setEditable(modification);

        
        this.button = new Button("Modifier");
        this.button.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.button.setPadding(new Insets(10,30,10,30));
        this.button.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));



        gridPaneProfil.add(labelTitre,0,0);
        gridPaneProfil.add(labelPseudo,0,2);
        gridPaneProfil.add(labelMail,0,3);
        gridPaneProfil.add(labelMDP,0,4);
        gridPaneProfil.add(labelNDV,0,5);
        gridPaneProfil.add(tfContentPseudo,1,2);
        gridPaneProfil.add(tfContentMail,1,3);
        gridPaneProfil.add(tfContentMDP,1,4);
        gridPaneProfil.add(tfContentNDV,1,5);


        VBox container = new VBox(gridPaneProfil, login);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(150); 

        Pane centerPane = new Pane(container);
        centerPane.setPrefSize(400, 300);

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50)); 
        this.getChildren().add(centerPane);
        

    }
    
}
