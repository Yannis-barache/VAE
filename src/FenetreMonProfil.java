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
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreMonProfil extends GridPane {
    
    private ApplicationVAE appli;

    public FenetreMonProfil(ApplicationVAE appli) {
        super();
        this.appli = appli;

        this.content();
    }

    private void content() {
        
        GridPane gridPaneProfil = new GridPane();
        gridPaneProfil.setAlignment(Pos.CENTER);    
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
        
        Label labelContentPseudo = new Label("MartinTermine");
        labelContentPseudo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        labelContentPseudo.setTextFill(Color.web("#5D48D7"));
 
        Label labelContentMail = new Label("marin.tremine@gmail.com");
        labelContentMail.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        labelContentMail.setTextFill(Color.web("#5D48D7"));

        Label labelContentMDP = new Label("marin1234");
        labelContentMDP.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        labelContentMDP.setTextFill(Color.web("#5D48D7"));
        
        Label labelContentNDV = new Label("46");
        labelContentNDV.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        labelContentNDV.setTextFill(Color.web("#5D48D7"));

        Button login = new Button("Modifier");
        login.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        login.setPadding(new Insets(10,30,10,30));
        login.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));


        gridPaneProfil.add(labelTitre,0,0);
        gridPaneProfil.add(labelPseudo,0,2);
        gridPaneProfil.add(labelMail,0,3);
        gridPaneProfil.add(labelMDP,0,4);
        gridPaneProfil.add(labelNDV,0,5);
        gridPaneProfil.add(labelContentPseudo,1,2);
        gridPaneProfil.add(labelContentMail,1,3);
        gridPaneProfil.add(labelContentMDP,1,4);
        gridPaneProfil.add(labelContentNDV,1,5);


        VBox container = new VBox(gridPaneProfil, login);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(150); 

        Pane centerPane = new Pane(container);
        centerPane.setPrefSize(400, 300);

        this.setAlignment(Pos.CENTER); 
        this.getChildren().add(centerPane);
        

    }
    
}
