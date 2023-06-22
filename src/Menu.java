import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
    private int associatedView;

    public Menu(ApplicationVAE appli,int index) {
        super();
        this.appli = appli;
        this.associatedView = index;

        this.content();
    }

    private void content() {

        //Logo
        VBox logoContent = new VBox();
        ImageView logo = new ImageView(new Image("file:./img/vae.png"));
        logo.setFitWidth(120);
        logo.setFitHeight(120);
        logo.setPreserveRatio(true);
        logoContent.getChildren().addAll(logo); 

        //Menu
        GridPane menu = new GridPane();

        Button accueilBtn = new Button("Accueil");
        Button createVBtn = new Button("Créer une vente");
        Button ventesBtn = new Button("Mes Ventes");
        Button encheresBtn = new Button("Mes enchères");
        
        accueilBtn.setOnAction(new ControleurMenu(this.appli));
        accueilBtn.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        accueilBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        accueilBtn.setTextFill(Color.web("white"));
        accueilBtn.setUnderline(true);
        accueilBtn.setStyle("-fx-translate-x: 0;");
        accueilBtn.setStyle("-fx-translate-y: 0;");
        accueilBtn.setFocusTraversable(false);

        createVBtn.setOnAction(new ControleurMenu(this.appli));
        createVBtn.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        createVBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        createVBtn.setTextFill(Color.web("white"));
        createVBtn.setUnderline(true);
        createVBtn.setStyle("-fx-translate-x: 0;");
        createVBtn.setStyle("-fx-translate-y: 0;");
        createVBtn.setFocusTraversable(false);

        ventesBtn.setOnAction(new ControleurMenu(this.appli));
        ventesBtn.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        ventesBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ventesBtn.setTextFill(Color.web("white"));
        ventesBtn.setUnderline(true);
        ventesBtn.setStyle("-fx-translate-x: 0;");
        ventesBtn.setStyle("-fx-translate-y: 0;");
        ventesBtn.setFocusTraversable(false);

        encheresBtn.setOnAction(new ControleurMenu(this.appli));
        encheresBtn.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        encheresBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        encheresBtn.setTextFill(Color.web("white"));
        encheresBtn.setUnderline(true);
        encheresBtn.setStyle("-fx-translate-x: 0;");
        encheresBtn.setStyle("-fx-translate-y: 0;");
        encheresBtn.setFocusTraversable(false);

        //Ajouts des boutons
        menu.add(accueilBtn,0,0);
        menu.add(createVBtn,1,0);
        menu.add(ventesBtn,2,0);
        menu.add(encheresBtn,3,0);

        menu.setHgap(100);
        menu.setAlignment(Pos.CENTER);

        //Paramètres / Profil
        HBox profilContent = new HBox();
        Button profil = new Button(this.appli.getUtilisateur().getPseudo());

        profil.setOnAction(new ControleurMenu(this.appli));
        profil.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        profil.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        profil.setTextFill(Color.web("white"));
        profil.setUnderline(true); 
        profil.setStyle("-fx-translate-x: 0;");
        profil.setStyle("-fx-translate-y: 0;");
        profil.setFocusTraversable(false);



        ContextMenu contextMenu = new ContextMenu();
        
        MenuItem monProfilItem = new MenuItem("Mon profil");
        monProfilItem.setStyle("-fx-font-family: Verdana; -fx-font-weight: bold; -fx-font-size: 20;");        
        monProfilItem.setOnAction(new ControleurMenu(this.appli));

        MenuItem pannelAdministrationItem = new MenuItem("Pannel Administration");
        pannelAdministrationItem.setVisible(false);
        pannelAdministrationItem.setStyle("-fx-font-family: Verdana; -fx-font-weight: bold; -fx-font-size: 20;");
        pannelAdministrationItem.setOnAction(new ControleurPannelAdministration(this.appli));
        if(this.appli.getUtilisateur().isAdmin()){
            pannelAdministrationItem.setVisible(true);
        }
        
        MenuItem deconnexionItem = new MenuItem("Déconnexion");
        deconnexionItem.setStyle("-fx-font-family: Verdana; -fx-font-weight: bold; -fx-font-size: 20;");
        deconnexionItem.setOnAction(new ControleurRetourPageConnec(this.appli));
        
        contextMenu.getItems().addAll(monProfilItem, pannelAdministrationItem, deconnexionItem);
        profil.setContextMenu(contextMenu);

        profil.setOnAction(event -> {
            contextMenu.show(profil, Side.BOTTOM, 0, 0);
        });
    
        profilContent.getChildren().addAll(profil);

        //Indicateur de la vue actuelle 
        if (this.associatedView == 0) accueilBtn.setTextFill(Color.web("#FEE159"));
        if (this.associatedView == 1) createVBtn.setTextFill(Color.web("#FEE159"));
        if (this.associatedView == 2) ventesBtn.setTextFill(Color.web("#FEE159"));
        if (this.associatedView == 3) encheresBtn.setTextFill(Color.web("#FEE159"));
        if (this.associatedView == 4) profil.setTextFill(Color.web("#FEE159"));

        //Couleur de fond du menu
        this.setBackground(new Background(new BackgroundFill(Color.web("#5D48D7"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.setPadding(new Insets(30));
        
        //Placement des containers dans le menu
        this.setLeft(logoContent);
        this.setCenter(menu);
        this.setRight(profilContent);
    }
}
