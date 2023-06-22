import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;
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
import javafx.scene.control.skin.TextAreaSkin;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.sql.SQLException;
import java.text.Bidi;
import java.util.ArrayList;

public class FenetreEditionVente extends GridPane {
    
    private ApplicationVAE appli;
    private Vente vente;
    private TextField newTitle;
    private DatePicker endSale;
    private TextArea newDesc;
    private ComboBox<String> categorySaleCB;
    private List<Photo> listePhoto;
    private List<Map<String,String>> listePhotoBD;
    private int nbPics;
    private Label alerte;

    public FenetreEditionVente(ApplicationVAE appli,Vente vente) {
        super();
        this.appli = appli;
        this.vente = vente;
        this.alerte = new Label("");
        this.listePhotoBD = new ArrayList<Map<String,String>>();
        try{
            this.listePhoto = this.appli.getPhotoBD().rechercherPhotosParObjet(this.vente.getObjet());
            
        }catch (SQLException ex){
            System.out.println("Peut pas récupérer les images");
        }
        
        this.nbPics = this.listePhoto.size();
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
        System.out.println(this.vente.getIdentifiant());
        Label title = new Label("Modifier une vente");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        titleContent.getChildren().add(title);

        //Nouveau titre
        VBox newTitleContent = new VBox();
        Label newTitleLabel = new Label("Nouveau titre");
        newTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newTitleLabel.setTextFill(Color.web("#5D48D7"));
        this.newTitle = new TextField(this.vente.getObjet().getNom());
        newTitle.setEffect(ds);
        newTitle.setPrefHeight(40);
        newTitle.setPrefWidth(350);
        newTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newTitle.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        newTitleContent.getChildren().addAll(newTitleLabel,newTitle);

        //Nouvelle description
        VBox newDescContent = new VBox();
        Label newDescLabel = new Label("Nouvelle description");
        newDescLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newDescLabel.setTextFill(Color.web("#5D48D7"));
        this.newDesc = new TextArea(this.vente.getObjet().getDescription());
        newDesc.setEffect(ds);
        newDesc.setPrefHeight(170);
        newDesc.setPrefWidth(350);
        newDesc.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newDesc.setStyle("-fx-control-inner-background: #F8F8F8");
        newDesc.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        newTitleContent.getChildren().addAll(newDescLabel,newDesc);

        newDescContent.getChildren().addAll(newDescLabel,newDesc);

        //Ajout d'images
        VBox newFilesContent = new VBox();

        
        HBox newFilesSaleLabels = new HBox();
        Label newFilesSaleLabel = new Label("Ajouter des images ");
        newFilesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newFilesSaleLabel.setTextFill(Color.web("#5D48D7"));

        PhotoBD photoBD = this.appli.getPhotoBD();
        int nbPhotos = 0;
        try {
            nbPhotos = photoBD.rechercherPhotosParObjet(this.vente.getObjet()).size();
        }
        catch(SQLException ex) {}
        Label newFilesCountLabel = new Label(String.valueOf(nbPhotos)+"/4");
        newFilesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newFilesCountLabel.setTextFill(Color.web("#5D48D7"));
        newFilesSaleLabels.getChildren().addAll(newFilesSaleLabel,newFilesCountLabel);
        // Création du file chooser
        FileChooser filesSaleFC = new FileChooser();

        // Ajout des filtres
        filesSaleFC.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Définition du titre    
        filesSaleFC.setTitle("Choisir une image");

        // Définition du répertoire initial
        filesSaleFC.setInitialDirectory(new File(System.getProperty("user.home")));

        Button openButton = new Button("+");
        openButton.setEffect(ds);
        openButton.setOnAction(new ControleurChoixPhoto(appli,this,filesSaleFC,listePhotoBD));
        openButton.setPrefHeight(40);
        openButton.setPrefWidth(40);

        newFilesContent.getChildren().addAll(newFilesSaleLabels,openButton);

        //Catégorie
        VBox newCategoryContent = new VBox();
        Label categorySaleLabel = new Label("Catégorie");
        categorySaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        categorySaleLabel.setTextFill(Color.web("#5D48D7"));
        List<String> filtersList = this.appli.getScriptJDBC().getCategories();
        categorySaleCB = new ComboBox<String>();
        categorySaleCB.getItems().addAll(filtersList);
        categorySaleCB.setValue(this.vente.getObjet().getCategorie().toString());
        // System.out.println(this.vente.getObjet().getCategorie()); //TEMP
        categorySaleCB.setEffect(ds);
        categorySaleCB.setPrefHeight(50);
        categorySaleCB.setPrefWidth(300);
        categorySaleCB.setStyle("-fx-control-inner-background: #F8F8F8");
        categorySaleCB.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        newCategoryContent.getChildren().addAll(categorySaleLabel,categorySaleCB);

        //Date de fin
        VBox newEndContent = new VBox();
        Label newEndLabel = new Label("Nouvelle date de fin");
        newEndLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newEndLabel.setTextFill(Color.web("#5D48D7"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");        
        LocalDate localDate = LocalDate.parse(this.vente.getFinVente(),formatter);
        endSale = new DatePicker(localDate);
        endSale.getEditor().setDisable(true);       

        // Définition de la cellule de date pour la date de fin
        Callback<DatePicker, DateCell> startSaleCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #F8F8F8; -fx-text-fill: gray;");
                        }
                    }
                };
            }
        };
        endSale.setDayCellFactory(startSaleCellFactory);
        endSale.setEffect(ds);
        endSale.setPrefHeight(50);
        endSale.setPrefWidth(350);
        // endSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endSale.setStyle("-fx-control-inner-background: #F8F8F8");
        endSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        newEndContent.getChildren().addAll(newEndLabel,endSale,alerte);

        //Buttons cancel et delete
        HBox canceldeleteContent = new HBox();
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);
        cancel.setOnAction(new ControleurRetourMesVentes(appli));
        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        Button delete = new Button("Supprimer");
        delete.setEffect(ds);
        delete.setOnAction(new ControleurSupprimerVente(appli,this));
        delete.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        delete.setPadding(new Insets(10,30,10,30));
        delete.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        canceldeleteContent.setSpacing(50);
        canceldeleteContent.getChildren().addAll(cancel, delete);
        

        VBox sendContent = new VBox(5);
        Button send = new Button("Sauvegarder les modifications");
        send.setEffect(ds);
        send.setOnAction(new ControleurModifierVente(appli, this)); //SAUVEGARDER LES MODIFS
        send.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        send.setPadding(new Insets(10,30,10,30));
        send.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        Label labelNote = new Label("* les champs non actualisés ne seront pas modifiés");
        labelNote.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelNote.setTextFill(Color.web("black"));
        sendContent.getChildren().addAll(send,labelNote);
        sendContent.setAlignment(Pos.TOP_RIGHT);

        //Ajout du titre
        this.setHgap(100);
        this.setVgap(50);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50));

        this.add(titleContent,0,0,1,1);
        this.add(newTitleContent,0,1,2,1);
        this.add(newDescContent,0,2,2,1);
        this.add(newFilesContent,0,3,1,1);
        this.add(newCategoryContent,1,3,1,1);
        this.add(newEndContent,2,1,1,1);
        
        this.add(canceldeleteContent,0,4,1,1);
        this.add(sendContent,2,4,1,1);
    }    

    public String getNewTitle() {
        return this.newTitle.getText();
    }

    public String getNewDesc() {
        return this.newDesc.getText();
    }

    public String getNewCategory() {
        return this.categorySaleCB.getValue();
    }

    public String getNewEnd() {
        return this.endSale.getValue().toString();
    }

    public Vente getVente() {
        return this.vente;
    }

    public List<Map<String, String>> getNewFiles() {
        return this.listePhotoBD;
    }

    public void setNbPics(int nbPics) {
        this.nbPics = nbPics;
    }

    public void setAlerte(String alerte) {
        this.alerte.setText(alerte);
    }




}