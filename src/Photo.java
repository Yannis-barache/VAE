import java.sql.Blob;

public class Photo {
    private int identifiant;
    private String titre;
    private Blob img;

    /**
     * Constructeur de la classe Photo.
     *
     * @param identifiant L'identifiant de la photo.
     * @param titre       Le titre de la photo.
     * @param img         Le chemin de l'image de la photo.
     */
    public Photo(int identifiant, String titre, Blob img) {
        this.identifiant = identifiant;
        this.titre = titre;
        this.img = img;
    }

    /**
     * Obtient l'identifiant de la photo.
     *
     * @return L'identifiant de la photo.
     */
    public int getIdentifiant() {
        return identifiant;
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
    public Blob getImg() {
        return img;
    }

    /**
     * Définit le chemin de l'image de la photo.
     *
     * @param img Le nouveau chemin de l'image de la photo.
     */
    public void setImg(Blob img) {
        this.img = img;
    }
}
