import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.util.Callback;
public class FenetreCreationVente extends GridPane {
    
    private ApplicationVAE appli;
    private TextField titleSale,basePriceSale,minPriceSale,startHourValue,endHourValue, startMinuteValue, endMinuteValue;
    private TextArea descSale;
    private DatePicker startSale,endSale;
    private ComboBox<String> categorySaleCB;
    private Label alertErreur;
    private List<Map<String,String>> filesSale;
    private Label filesCountLabel;
    private VBox filesSaleNames;

    public FenetreCreationVente(ApplicationVAE appli) {
        super();
        this.appli = appli;
        this.alertErreur = new Label();
        this.filesSaleNames = new VBox();
        this.content();
    }

    private void content() {

         //Shadows
         DropShadow ds = new DropShadow();
         ds.setOffsetY(6.0f);
         ds.setOffsetX(4.0f);
         ds.setColor(Color.web("lightgray"));

        //Titre page
        Label title = new Label("Créer une vente");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));

        //Titre vente
        VBox titleSaleContent = new VBox();
        Label titleSaleLabel = new Label("Titre");
        titleSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        titleSaleLabel.setTextFill(Color.web("#5D48D7"));
        titleSale= new TextField();
        titleSale.setEffect(ds);
        titleSale.setPrefHeight(40);
        titleSale.setPrefWidth(350);
        titleSale.setPromptText("Insérer le titre");
        titleSale.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        titleSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        titleSaleContent.getChildren().addAll(titleSaleLabel,titleSale);

        //Description
        VBox descSaleContent = new VBox();
        Label descSaleLabel = new Label("Description");
        descSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        descSaleLabel.setTextFill(Color.web("#5D48D7"));
        descSale = new TextArea();
        descSale.setPromptText("Insérez une description de l'objet");
        descSale.setEffect(ds);
        descSale.setPrefHeight(170);
        descSale.setPrefWidth(350);
        descSale.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        descSale.setStyle("-fx-control-inner-background: #F8F8F8");
        descSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        descSaleContent.getChildren().addAll(descSaleLabel,descSale);

        //Ajout d'images

        //Liste d'images

        VBox filesSaleContent = new VBox();
        HBox filesSaleLabels = new HBox();
        Label filesSaleLabel = new Label("Ajouter des images ");
        filesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filesSaleLabel.setTextFill(Color.web("#5D48D7"));
        this.filesCountLabel = new Label(String.valueOf("0/4"));
        this.filesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.filesCountLabel.setTextFill(Color.web("#5D48D7"));
        filesSaleLabels.getChildren().addAll(filesSaleLabel,this.filesCountLabel);

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

        this.filesSale = new ArrayList<Map<String,String>>();

        Button openButton = new Button("+");
        openButton.setEffect(ds);
        openButton.setOnAction(new ControleurChoixPhoto(appli,this,filesSaleFC,filesSale));
        openButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        openButton.setPadding(new Insets(10,30,10,30));
        openButton.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        filesSaleContent.getChildren().addAll(filesSaleLabels,openButton,filesSaleNames);

        //Catégorie
        VBox categorySaleContent = new VBox();
        Label categorySaleLabel = new Label("Catégorie");
        categorySaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        categorySaleLabel.setTextFill(Color.web("#5D48D7"));
        List<String> filtersList = this.appli.getScriptJDBC().getCategories();
        categorySaleCB = new ComboBox<String>();
        categorySaleCB.getItems().addAll(filtersList);
        categorySaleCB.setEffect(ds);
        categorySaleCB.setPrefHeight(50);
        categorySaleCB.setPrefWidth(300);
        categorySaleCB.setStyle("-fx-control-inner-background: #F8F8F8");
        categorySaleCB.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        categorySaleContent.getChildren().addAll(categorySaleLabel,categorySaleCB);

        //Prix de base
        VBox basePriceSaleContent = new VBox();
        Label basePriceSaleLabel = new Label("Prix de base");
        basePriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        basePriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        basePriceSale = new TextField();
        basePriceSale.setPromptText("Insérez le prix de base");
        basePriceSale.setEffect(ds);
        basePriceSale.setPrefHeight(40);
        basePriceSale.setPrefWidth(350);
        basePriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        basePriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        basePriceSale.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        basePriceSaleContent.getChildren().addAll(basePriceSaleLabel,basePriceSale);

        //Prix minimum
        VBox minPriceSaleContent = new VBox();
        Label minPriceSaleLabel = new Label("Prix minimum");
        minPriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        minPriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        minPriceSale = new TextField();
        minPriceSale.setPromptText("Insérez le prix minimum");
        minPriceSale.setEffect(ds);
        minPriceSale.setPrefHeight(40);
        minPriceSale.setPrefWidth(350);
        minPriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        minPriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        minPriceSale.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        minPriceSaleContent.getChildren().addAll(minPriceSaleLabel,minPriceSale);

        //Date de début
        VBox startSaleContent = new VBox();
        Label startSaleLabel = new Label("Date de début");
        startSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startSaleLabel.setTextFill(Color.web("#5D48D7"));
        startSale = new DatePicker();
        startSale.getEditor().setDisable(true);
        startSale.setPromptText("Choisissez la date du début");
            
        // Définition de la cellule de date pour la date de début
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
        startSale.setDayCellFactory(startSaleCellFactory);
        startSale.setEffect(ds);
        startSale.setPrefHeight(50);
        startSale.setPrefWidth(350);
        startSale.setStyle("-fx-control-inner-background: #F8F8F8");
        startSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        startSaleContent.getChildren().addAll(startSaleLabel,startSale);

        //Date de fin
        VBox endSaleContent = new VBox();
        Label endSaleLabel = new Label("Date de fin");
        endSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endSaleLabel.setTextFill(Color.web("#5D48D7"));
        endSale = new DatePicker();
        endSale.getEditor().setDisable(true);
        endSale.setPromptText("Choisissez la date de fin");
        // Définition de la cellule de date pour la date de fin
        Callback<DatePicker, DateCell> endSaleCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate selectedStartDate = startSale.getValue();
                        if (selectedStartDate != null && item.isBefore(selectedStartDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #F8F8F8; -fx-text-fill: gray;");
                        }
                    }
                };
            }
        };                     
        endSale.setDayCellFactory(endSaleCellFactory);
        endSale.setEffect(ds);
        endSale.setPrefHeight(50);
        endSale.setPrefWidth(350);
        endSale.setStyle("-fx-control-inner-background: #F8F8F8");
        endSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        endSaleContent.getChildren().addAll(endSaleLabel,endSale);

        endSale.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && startSale.getValue() != null && newValue.isBefore(startSale.getValue())) {
                endSale.setValue(startSale.getValue());
            }
        });

        startSale.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && endSale.getValue() != null && newValue.isAfter(endSale.getValue())) {
                endSale.setValue(newValue);
            }
        });

        //Heure de début
        VBox startHourSaleContent = new VBox();
        Label startTimeLabel = new Label("Heure de début");
        startTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startTimeLabel.setTextFill(Color.web("#5D48D7"));

        //Grille pour l'heure
        GridPane startTimeValues = new GridPane();

        //L'heure
        startHourValue = new TextField();

        //Pas plus de 2 caractères
        startHourValue.textProperty().addListener((observable,oldValue,newValue) -> {
            if (newValue.length() > 2) {
                startHourValue.setText(oldValue);
            }
        });
        startHourValue.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                String text = startHourValue.getText();
                if (text.isEmpty()) {
                    startHourValue.setText("00");
                } else {
                    int value = Integer.parseInt(text);
                    if (value > 23) {
                        startHourValue.setText("23");
                    }
                }
            }
        });
        startHourValue.setEffect(ds);
        startHourValue.setPrefHeight(40);
        startHourValue.setPrefWidth(70);
        startHourValue.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startHourValue.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        startHourValue.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        //Indicatif heure
        VBox startHourLabelContent = new VBox();
        Label sHourLabel = new Label("h");
        sHourLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        sHourLabel.setTextFill(Color.web("black"));
        startHourLabelContent.setAlignment(Pos.CENTER);
        startHourLabelContent.getChildren().add(sHourLabel);
        //Minutes
        startMinuteValue = new TextField();

        //Pas plus de 2 caractères
        startMinuteValue.textProperty().addListener((observable,oldValue,newValue) -> {
            if (newValue.length() > 2) {
                startMinuteValue.setText(oldValue);
            }
        });
        startMinuteValue.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                String text = startMinuteValue.getText();
                if (text.isEmpty()) {
                    startMinuteValue.setText("00");
                } else {
                    int value = Integer.parseInt(text);
                    if (value > 59) {
                        startMinuteValue.setText("59");
                    }
                }
            }
        });
        //Style
        startMinuteValue.setEffect(ds);
        startMinuteValue.setPrefHeight(40);
        startMinuteValue.setPrefWidth(70);
        startMinuteValue.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startMinuteValue.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        startMinuteValue.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        //Indicatif minute
        VBox startMinuteLabelContent = new VBox();
        Label startMinuteLabel = new Label("m");
        startMinuteLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startMinuteLabel.setTextFill(Color.web("black"));
        startMinuteLabelContent.setAlignment(Pos.CENTER);
        startMinuteLabelContent.getChildren().add(startMinuteLabel);

        //Ajout dans la grille
        startTimeValues.setHgap(10);
        startTimeValues.add(startHourValue,0,0,1,1);
        startTimeValues.add(startHourLabelContent,1,0,1,1);
        startTimeValues.add(startMinuteValue,2,0,1,1);
        startTimeValues.add(startMinuteLabelContent,3,0,1,1);  
        
        //Ajout overall
        startHourSaleContent.getChildren().addAll(startTimeLabel,startTimeValues);


        //Heure de fin
        VBox endHourSaleContent = new VBox();
        Label endTimeLabel = new Label("Heure de fin");
        endTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endTimeLabel.setTextFill(Color.web("#5D48D7"));
        //Grille pour l'heure
        GridPane endTimeValues = new GridPane();

        //L'heure
        endHourValue = new TextField();

        //Pas plus de 2 caractères
        endHourValue.textProperty().addListener((observable,oldValue,newValue) -> {
            if (newValue.length() > 2) {
                endHourValue.setText(oldValue);
            }
        });
        endHourValue.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                String text = endHourValue.getText();
                if (text.isEmpty()) {
                    endHourValue.setText("00");
                } else {
                    int value = Integer.parseInt(text);
                    if (value > 23) {
                        endHourValue.setText("23");
                    }
                }
            }
        });
        endHourValue.setEffect(ds);
        endHourValue.setPrefHeight(40);
        endHourValue.setPrefWidth(70);
        endHourValue.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endHourValue.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        endHourValue.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        //Indicatif heure
        VBox endHourLabelContent = new VBox();
        Label eHourLabel = new Label("h");
        eHourLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        eHourLabel.setTextFill(Color.web("black"));
        endHourLabelContent.setAlignment(Pos.CENTER);
        endHourLabelContent.getChildren().add(eHourLabel);

        //Minutes
        this.endMinuteValue = new TextField();

        //Pas plus de 2 caractères
        endMinuteValue.textProperty().addListener((observable,oldValue,newValue) -> {
            if (newValue.length() > 2) {
                endMinuteValue.setText(oldValue);
            }
        });
        endMinuteValue.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (!newFocus) {
                String text = endMinuteValue.getText();
                if (text.isEmpty()) {
                    endMinuteValue.setText("00");
                } else {
                    int value = Integer.parseInt(text);
                    if (value > 59) {
                        endMinuteValue.setText("59");
                    }
                }
            }
        });
        //Style
        endMinuteValue.setEffect(ds);
        endMinuteValue.setPrefHeight(40);
        endMinuteValue.setPrefWidth(70);
        endMinuteValue.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endMinuteValue.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        endMinuteValue.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        //Indicatif minute
        VBox endMinuteLabelContent = new VBox();
        Label endMinuteLabel = new Label("m");
        endMinuteLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endMinuteLabel.setTextFill(Color.web("black"));
        endMinuteLabelContent.setAlignment(Pos.CENTER);
        endMinuteLabelContent.getChildren().add(endMinuteLabel);

        //Ajout dans la grille
        endTimeValues.setHgap(10);
        endTimeValues.add(endHourValue,0,0,1,1);
        endTimeValues.add(endHourLabelContent,1,0,1,1);
        endTimeValues.add(endMinuteValue,2,0,1,1);
        endTimeValues.add(endMinuteLabelContent,3,0,1,1);  
        
        //Ajout overall
        endHourSaleContent.getChildren().addAll(endTimeLabel,endTimeValues);

        //Buttons
        VBox cancelContent = new VBox();
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);
        cancel.setOnAction((key) -> this.appli.fenetreAccueil());
        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        cancelContent.getChildren().add(cancel);

        VBox sendContent = new VBox();
        Button send = new Button("Mettre en ligne");
        send.setOnAction(new ControleurInsererVente(appli, this));
        //send.setOnAction(new ControleurRetourMesVentes(this.appli));
        send.setEffect(ds);
        send.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        send.setPadding(new Insets(10,30,10,30));
        send.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        sendContent.getChildren().add(send);
        sendContent.getChildren().add(this.alertErreur);
        sendContent.setAlignment(Pos.TOP_RIGHT);

        //Configuration de la grille
        this.setHgap(100);
        this.setVgap(50);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50));

        //Ajouts des containers dans la grille
        this.add(title,0,0);
        this.add(titleSaleContent,0,1,2,1);
        this.add(descSaleContent,0,2,2,2);
        this.add(filesSaleContent,0,4,1,1);
        this.add(categorySaleContent,1,4,1,1);
        
        this.add(basePriceSaleContent,2,1,1,1);
        this.add(minPriceSaleContent,3,1,1,1);
        this.add(startSaleContent,2,3,1,1);
        this.add(startHourSaleContent,3,3,1,1);
        this.add(endSaleContent,2,4,1,1);
        this.add(endHourSaleContent,3,4,1,1);

        this.add(cancelContent,0,5,1,1);
        this.add(sendContent,3,5,1,1);
    }


    public void majNomImage() {
        // Ajout des titres des image à côté des boutons
        this.filesSaleNames.getChildren().clear();
        this.filesSaleNames.setSpacing(10);
        this.filesSaleNames.setPadding(new Insets(10,10,10,10));
        this.filesSaleNames.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.filesSaleNames.setPrefHeight(200);
        this.filesSaleNames.setPrefWidth(200);
        int i = 0;
        for (Map<String,String> file : this.filesSale) {
            i++;
            HBox fileContent = new HBox();
            fileContent.setSpacing(10);
            fileContent.setPadding(new Insets(10,10,10,10));
            Label fileName = new Label();
            Button bouton = new Button("X");
            bouton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            bouton.setPadding(new Insets(5,10,5,10));
            bouton.setBackground(new Background(new BackgroundFill(Color.web("#e0584f"),CornerRadii.EMPTY,Insets.EMPTY)));
            bouton.setTextFill(Color.web("#FFFFFF"));
            bouton.setOnAction(new ControleurSupImageCrea(this.getListePhotos(),i,this));

            for (String key : file.keySet()){
                
                fileName.setText(key);
                fileContent.getChildren().addAll(fileName,bouton);
            }
            System.out.println(file.get(0));
            fileName.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            fileName.setTextFill(Color.web("#5D48D7"));
            filesSaleNames.getChildren().add(fileContent);
        }
    }

    public TextArea getDescSale() {
        return this.descSale;
    }

    public TextField getTitleSale() {
        return this.titleSale;
    }

    public TextField getBasePriceSale() {
        return this.basePriceSale;
    }

    public TextField getMinPriceSale() {
        return this.minPriceSale;
    }

    public DatePicker getStartSale() {
        return this.startSale;
    }

    public DatePicker getEndSale() {
        return this.endSale;
    }

    public int getHeureDebut(){

        int hour = Integer.parseInt(this.startHourValue.getText());
        return  hour;
    }

    public int getHeureFin(){
            
            int hour = Integer.parseInt(this.endHourValue.getText());
            return  hour;
    }


    public int getMinuteDebut(){
            
            int minute = Integer.parseInt(this.startMinuteValue.getText());
            return  minute;
    }

    public int getMinuteFin(){
            
            int minute = Integer.parseInt(this.endMinuteValue.getText());
            return  minute;
    }

    

    public String getCategorySale() {
        return this.categorySaleCB.getValue();
    }

    public void setAlertErreur(String alert) {
        this.alertErreur.setText(alert);
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(this.startSale.getValue().getYear(),this.startSale.getValue().getMonth(),this.startSale.getValue().getDayOfMonth(),Integer.parseInt(this.startHourValue.getText()),Integer.parseInt(this.startMinuteValue.getText()));
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(this.endSale.getValue().getYear(),this.endSale.getValue().getMonth(),this.endSale.getValue().getDayOfMonth(),Integer.parseInt(this.endHourValue.getText()),Integer.parseInt(this.endMinuteValue.getText()));
    }

    public List<Map<String,String>> getListePhotos(){
        return this.filesSale;
    }

    public void setNbPics(int value) {
        this.filesCountLabel.setText(String.valueOf(value)+"/4");
    }
    


    
}
