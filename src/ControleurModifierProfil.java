import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class ControleurModifierProfil implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreMonProfil fenetreMonProfil;


    public ControleurModifierProfil(ApplicationVAE appli, FenetreMonProfil fenetreMonProfil) {
        this.appli=appli;
        this.fenetreMonProfil=fenetreMonProfil;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment modifier votre profil ?");
            alert.setContentText("Appuyez sur OK pour confirmer");
            alert.showAndWait();
            String pseudo= fenetreMonProfil.getPseudo();
            String mail= fenetreMonProfil.getMail();
            String mdp= fenetreMonProfil.getMdp();
            
            Button bouton = (Button) actionEvent.getSource();
            if (bouton.getText().equals("Sauvegarder")){
                
                System.out.println("sauvegarder");

                this.appli.getUtilisateur().setPseudo(pseudo);
                if(Valide.emailValide(mail)){
                    this.appli.getUtilisateur().setMail(mail);
                }else{
                    this.fenetreMonProfil.setErreur("Adresse non valide");
                }
                this.appli.getUtilisateur().setMdp(mdp);

                this.appli.getUtilisateurBD().modifierUtilisateur(this.appli.getUtilisateur());
            } 
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
                this.fenetreMonProfil.setErreur("Pseudo déjà utilisé");
        }
    }

 }
    

