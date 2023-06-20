public class Statut{
    private int identifiant;
    private String nom;

    /**
     * Constructeur de la classe statut.
     *
     * @param identifiant L'identifiant du statut.
     * @param nom         Le nom du statut
     */
    public Statut(int identifiant, String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }

    /**
     * Obtient l'identifiant du statut.
     *
     * @return L'identifiant du statut.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le nom du statut.
     *
     * @return Le nom du statut.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du statut.
     *
     * @param nom Le nouveau nom du statut.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}