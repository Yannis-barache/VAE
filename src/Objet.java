import java.util.ArrayList;
import java.util.List;

public class Objet {
    private int identifiant;
    private String nom;
    private String description;
    private Categorie categorie;
    private List<Photo> lesPhotos;
    private Utilisateur vendeur;

    /**
     * Constructeur de la classe Objet.
     *
     * @param identifiant  L'identifiant de l'objet.
     * @param nom          Le nom de l'objet.
     * @param description  La description de l'objet.
     * @param categorie    La catégorie de l'objet.
     * @param lesPhotos    La liste des photos de l'objet.
     * @param vendeur      Le vendeur de l'objet.
     */
    public Objet(int identifiant, String nom, String description, Categorie categorie, Utilisateur vendeur) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.lesPhotos = new ArrayList<>(4);
        this.vendeur = vendeur;
    }

    /**
     * Constructeur de la classe Objet.
     *
     * @param nom          Le nom de l'objet.
     * @param description  La description de l'objet.
     * @param categorie    La catégorie de l'objet.
     * @param lesPhotos    La liste des photos de l'objet.
     * @param vendeur      Le vendeur de l'objet.
     */
    public Objet(String nom, String description, Categorie categorie, Utilisateur vendeur) {
        this.identifiant = 0;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.lesPhotos = new ArrayList<>(4);
        this.vendeur = vendeur;
    }

    /**
     * Obtient l'identifiant de l'objet.
     *
     * @return L'identifiant de l'objet.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le nom de l'objet.
     *
     * @return Le nom de l'objet.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'objet.
     *
     * @param nom Le nouveau nom de l'objet.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la description de l'objet.
     *
     * @return La description de l'objet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de l'objet.
     *
     * @param description La nouvelle description de l'objet.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient la catégorie de l'objet.
     *
     * @return La catégorie de l'objet.
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * Définit la catégorie de l'objet.
     *
     * @param categorie La nouvelle catégorie de l'objet.
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    /**
     * Obtient la liste des photos de l'objet.
     *
     * @return La liste des photos de l'objet.
     */
    public List<Photo> getLesPhotos() {
        return lesPhotos;
    }

    /**
     * Définit la liste des photos de l'objet.
     *
     * @param lesPhotos La nouvelle liste des photos de l'objet.
     * @return true si la liste des photos a été définie avec succès, false sinon.
     */
    public boolean setLesPhotos(ArrayList<Photo> lesPhotos) {
        if (lesPhotos.size() > 4){
            return false;
        }
        this.lesPhotos = lesPhotos;
        return true;
    }

    /**
     * Ajoute une photo à la liste des photos de l'objet.
     *
     * @param photo La photo à ajouter.
     * @return true si la photo a été ajoutée avec succès, false sinon.
     */
    public boolean ajoutePhoto(Photo photo) {
        if (this.lesPhotos.size() < 4){
            this.lesPhotos.add(photo);
            return true;
        }
        return false;
        
    }

    /**
     * Obtient le vendeur de cet objet.
     *
     * @return Le vendeur de cet objet.
     */
    public Utilisateur getVendeur(){
        return this.vendeur;
    }

    

    @Override
    public String toString(){
        return ""+this.getIdentifiant()+" "+this.getNom()+" "+this.getDescription()+" "+this.getCategorie()+" "+this.getVendeur()+" "+this.getLesPhotos();
    }
}
