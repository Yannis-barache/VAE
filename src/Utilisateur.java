import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilisateur {
    private int identifiant;
    private String pseudo;
    private String mail;
    private String mdp;
    private boolean actif;
    private boolean admin;
    private List<Vente> ventes;
    private List<Enchere> encheres;

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param identifiant L'identifiant de l'utilisateur.
     * @param pseudo      Le pseudo de l'utilisateur.
     * @param mail        L'adresse e-mail de l'utilisateur.
     * @param mdp         Le mot de passe de l'utilisateur.
     * @param actif       Indique si l'utilisateur est actif ou non.
     * @param admin       Indique si l'utilisateur est administrateur ou non.
     */
    
    public Utilisateur(int identifiant, String pseudo, String mail, String mdp, boolean actif, boolean admin) {
        this.identifiant = identifiant;
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.actif = actif;
        this.admin = admin;
        this.ventes = new ArrayList<>();
        this.encheres = new ArrayList<>();
    }

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param pseudo      Le pseudo de l'utilisateur.
     * @param mail        L'adresse e-mail de l'utilisateur.
     * @param mdp         Le mot de passe de l'utilisateur.
     * @param actif       Indique si l'utilisateur est actif ou non.
     * @param admin       Indique si l'utilisateur est administrateur ou non.
     */
    
     public Utilisateur(String pseudo, String mail, String mdp, boolean actif, boolean admin) {
        this.identifiant = 0;
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.actif = actif;
        this.admin = admin;
        this.ventes = new ArrayList<>();
        this.encheres = new ArrayList<>();
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le pseudo de l'utilisateur.
     *
     * @return Le pseudo de l'utilisateur.
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * Définit le pseudo de l'utilisateur.
     *
     * @param pseudo Le nouveau pseudo de l'utilisateur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Obtient l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param mail La nouvelle adresse e-mail de l'utilisateur.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMdp() {
        return this.mdp;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param mdp Le nouveau mot de passe de l'utilisateur.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Indique si l'utilisateur est actif ou non.
     *
     * @return true si l'utilisateur est actif, false sinon.
     */
    public boolean isActif() {
        return this.actif;
    }

    /**
     * Définit si l'utilisateur est actif ou non.
     *
     * @param actif true si l'utilisateur est actif, false sinon.
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }

    /**
     * Indique si l'utilisateur est administrateur ou non.
     *
     * @return true si l'utilisateur est administrateur, false sinon.
     */
    public boolean isAdmin() {
        return this.admin;
    }

    /**
     * Définit si l'utilisateur est administrateur ou non.
     *
     * @param admin true si l'utilisateur est administrateur, false sinon.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Obtient la liste des ventes de l'utilisateur.
     *
     * @return La liste des ventes de l'utilisateur.
     */
    public List<Vente> getVentes() {
        return this.ventes;
    }

    /**
     * Ajoute une vente à la liste des ventes de l'utilisateur.
     *
     * @param vente La vente à ajouter.
     */
    public void ajouterVente(Vente vente) {
        this.ventes.add(vente);
    }

    /**
     * Supprime une vente de la liste des ventes de l'utilisateur.
     *
     * @param vente La vente à supprimer.
     */
    public void supprimerVente(Vente vente) {
        this.ventes.remove(vente);
    }

        /**
     * Obtient la liste des encheres de l'utilisateur.
     *
     * @return La liste des encheres de l'utilisateur.
     */
    public List<Enchere> getEncheres() {
        return this.encheres;
    }

    /**
     * Ajoute une encheres à la liste des encheres de l'utilisateur.
     *
     * @param encheres L'enchere à ajouter.
     */
    public void ajouterEnchere(Enchere enchere) {
        this.encheres.add(enchere);
    }

    /**
     * Supprime une enchere de la liste des encheres de l'utilisateur.
     *
     * @param enchere L'enchere à supprimer.
     */
    public void supprimerEnchere(Enchere enchere) {
        this.encheres.remove(enchere);
    }

    public void clear(){
        this.encheres= new ArrayList<>();
    }

    @Override
    public String toString(){
        return ""+getIdentifiant()+" "+getPseudo()+" "+getMail()+" "+getMdp()+" Ventes : "+getVentes()+" "+isActif()+" "+isAdmin()+" Encheres :"+getEncheres();
    }


}
