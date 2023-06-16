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

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;


public class Menu extends BorderPane {
    
    private ApplicationVAE appli;

    public Menu(ApplicationVAE appli) {
        super();
        this.appli = appli;

        this.content();
    }

    private void content() {

        //Logo
        VBox logoContent = new VBox();
        ImageView logo = new ImageView(new Image("./img/vae2.png"));
        logoContent.getChildren().addAll(logo); 

        //Menu

        GridPane menu = new GridPane();
        Button accueilBtn = new Button("Accueil");
        Button createVBtn = new Button("Créer une vente");
        Button ventesBtn = new Button("Mes Ventes");
        Button encheresBtn = new Button("Mes enchères");
        
        //Ajouts des boutons
        menu.add(accueilBtn,0,0);
        menu.add(createVBtn,1,0);
        menu.add(ventesBtn,2,0);
        menu.add(encheresBtn,3,0);

        menu.setHgap(100);
        menu.setAlignment(Pos.CENTER);

        //Paramètres / Profil
        HBox settingsContent = new HBox();
        Button profil = new Button("Profil");
        settingsContent.getChildren().addAll(profil);


        this.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.setPadding(new Insets(30));
        
        // this.setLeft(logoContent);
        this.setCenter(menu);
        this.setRight(settingsContent);



    }
}
