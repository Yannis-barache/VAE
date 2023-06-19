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
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

public class FenetreEditionVente extends GridPane {
    
    private ApplicationVAE appli;
    private Map<String,String> vente;

    public FenetreEditionVente(ApplicationVAE appli,Map<String,String> vente) {
        super();
        this.appli = appli;
        this.vente = vente;

        this.content();
    }

    private void content() {

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Titre
        VBox titleContent = new VBox();
        System.out.println(this.vente.get("id"));
        Label title = new Label("Modifier une vente");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        titleContent.getChildren().add(title);

        //Ajout du titre
        this.add(titleContent,1,0,1,1);
    }

    
}
