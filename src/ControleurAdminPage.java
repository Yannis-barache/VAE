import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.filechooser.FileNameExtensionFilter;

public class ControleurAdminPage implements EventHandler<ActionEvent> {
    private ApplicationVAE appli;


    public ControleurAdminPage(ApplicationVAE appli) {
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        Button bouton = (Button) actionEvent.getSource();
        String text = bouton.getText();
        if (text.equals("Gérer les utilisateurs")){
            this.appli.fenetreManageUsers();
        }

        if (text.equals("Gérer les ventes")){
            this.appli.fenetreManageSales();
        }



    }


}
