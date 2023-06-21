import javafx.scene.image.Image;

public class Photo {
    private int identifiant;
    private String titre;
    private Image img;
    private String chemin;

    /**
     * Constructeur de la classe Photo.
     *
     * @param identifiant L'identifiant de la photo.
     * @param titre       Le titre de la photo.
     * @param img         L'image.
     */
    public Photo(int identifiant, String titre, Image img) {
        this.identifiant = identifiant;
        this.titre = titre;
        this.img = img;
        this.chemin=" ";
    }
    /**
     * Constructeur de la classe Photo.
     *
     * @param titre       Le titre de la photo.
     * @param img         L'image.
     */
    public Photo(String titre, Image img) {
        this.identifiant = 0;
        this.titre = titre;
        this.img = img;
        this.chemin=" ";
    }

    /**
     * Constructeur de la classe Photo.
     *
     * @param titre       Le titre de la photo.
     * @param chemin      Le chemin de l'image de la photo.
     */
    public Photo(String titre, String chemin) {
        System.out.println(chemin);
        this.identifiant = 0;
        this.titre = titre;
        this.chemin = chemin;
        this.img = new Image(this.chemin);
    }

    /**
     * Obtient l'identifiant de la photo.
     *
     * @return L'identifiant de la photo.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le titre de la photo.
     *
     * @return Le titre de la photo.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre de la photo.
     *
     * @param titre Le nouveau titre de la photo.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Obtient le chemin de l'image de la photo.
     *
     * @return Le chemin de l'image de la photo.
     */
    public Image getImg() {
        return img;
    }

    /**
     * Définit le chemin de l'image de la photo.
     *
     * @param img Le nouveau chemin de l'image de la photo.
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * Obtient le chemin de l'image de la photo.
     *
     * @return Le chemin de l'image de la photo.
     */
    public String getChemin() {
        return this.chemin;
    }

    /**
     * Définit le chemin de l'image de la photo.
     *
     * @param img Le nouveau chemin de l'image de la photo.
     */
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    @Override
    public String toString(){
        return ""+this.getIdentifiant()+" "+this.getTitre();
    }
}
