import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.filechooser.FileNameExtensionFilter;

public class ControleurBouton implements EventHandler<ActionEvent> {
    private ApplicationVAE appli;
    private FenetreMonProfil fenetreMonProfil;

    public ControleurBouton(ApplicationVAE appli,FenetreMonProfil fenetreMonProfil) {
        this.appli=appli;
        this.fenetreMonProfil=fenetreMonProfil;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        try{
    
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            


            Button bouton = (Button) actionEvent.getSource();
            ButtonType buttonClicked = alert.getResult();
            
            if (bouton.getText().equals("Modifier")){
                this.fenetreMonProfil.modeTF();
                this.fenetreMonProfil.getButton().setText("Sauvegarder");
                System.out.println("modifier");
            } else{
                if (bouton.getText().equals("Sauvegarder")){
                    String pseudo= fenetreMonProfil.getPseudo();
                    String mail= fenetreMonProfil.getMail();
                    String mdp= fenetreMonProfil.getMdp();
                    if(!Valide.emailValide(mail)){
                        this.fenetreMonProfil.setErreur("Veuillez entrer un email valide");
                    } else {
                        try{
                            this.appli.getUtilisateur().setPseudo(pseudo);
                            this.appli.getUtilisateur().setMail(mail);
                            this.appli.getUtilisateur().setMdp(mdp);
                            this.appli.getUtilisateurBD().modifierUtilisateur(this.appli.getUtilisateur());
                            System.out.println(this.appli.getUtilisateur());
                            this.fenetreMonProfil.modeTF();
                            this.fenetreMonProfil.getButton().setText("Modifier");
                            this.appli.fenetreMonProfil();
                        }
                        catch(SQLIntegrityConstraintViolationException ex){
                            this.fenetreMonProfil.setErreur("Pseudo déjà utilisé");
                        }
                        
                    }
                } 
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
           
    }

    
}
