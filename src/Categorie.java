public class Categorie {
    private int identifiant;
    private String nom;

    /**
     * Constructeur de la classe Categorie.
     *
     * @param identifiant L'identifiant de la catégorie.
     * @param nom         Le nom de la catégorie.
     */
    public Categorie(int identifiant, String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }

    /**
     * Obtient l'identifiant de la catégorie.
     *
     * @return L'identifiant de la catégorie.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le nom de la catégorie.
     *
     * @return Le nom de la catégorie.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la catégorie.
     *
     * @param nom Le nouveau nom de la catégorie.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
