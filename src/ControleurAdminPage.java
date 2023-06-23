import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurAdminPage implements EventHandler<ActionEvent> {

    /**
     * L'application VAE
     */
    private ApplicationVAE appli;


    /**
     * Constructeur de la classe ControleurAdminPage
     * @param appli l'application VAE
     */
    public ControleurAdminPage(ApplicationVAE appli) {
        this.appli=appli;
    }

    /**
     * Méthode qui gère les actions sur les boutons de la page admin
     * @param actionEvent l'action sur le bouton
     */
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
