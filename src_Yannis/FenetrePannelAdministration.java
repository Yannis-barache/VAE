import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
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
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetrePannelAdministration extends BorderPane{
    
    private ApplicationVAE appli;


    public FenetrePannelAdministration(ApplicationVAE appli) {
        super();
        this.appli = appli;
        this.content();
    }

    private void content() {
        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        VBox content = new VBox(); //VBox

        //Titre
        HBox titleContent = new HBox();
        Label title = new Label("Bienvenu sur la page d'administration : Yannis"  ); //this.appli.getUtilisateur().getPseudo()
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleContent.getChildren().add(title);


        //
        TilePane tileButtons = new TilePane();  //TilePane

        //Ajout des boutons à la page
        Button manageUsers = new Button("Gérer les utilisateurs");
        Button manageItems = new Button("Gérer les items");
        Button manageSales = new Button("Gérer les ventes");
        Button managePurchases = new Button("Gérer les achats");

        manageUsers.setPrefSize(500, 100);
        manageItems.setPrefSize(500, 100);
        manageSales.setPrefSize(200, 100);
        managePurchases.setPrefSize(200, 100);
        manageUsers.setEffect(ds);
        manageItems.setEffect(ds);
        manageSales.setEffect(ds);
        managePurchases.setEffect(ds);

        manageUsers.setOnAction(e -> {
            this.appli.fenetreManageUsers();
        });

        manageSales.setOnAction(e -> {
            this.appli.fenetreManageSales();
        });
        

        tileButtons.getChildren().addAll(manageUsers, manageItems, manageSales, managePurchases);
        tileButtons.setPadding(new Insets(10, 10, 10, 10));

        content.getChildren().addAll(title,tileButtons);

        this.setTop(new Label("Le menu "));
        this.setCenter(content);


    
    }

}