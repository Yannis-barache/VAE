public class Enchere {
    private int identifiant;
    private Vente vente;
    private Utilisateur encherisseur;
    private double montant;
    private String date;

    /**
     * Constructeur de la classe Enchere.
     *
     * @param identifiant   L'identifiant de l'enchère.
     * @param vente         La vente associée à l'enchère.
     * @param encherisseur  L'utilisateur encherisseur.
     * @param montant       Le montant de l'enchère.
     * @param date          La date de l'enchère.
     */
    public Enchere(int identifiant, Vente vente, Utilisateur encherisseur, double montant, String date) {
        this.identifiant = identifiant;
        this.vente = vente;
        this.encherisseur = encherisseur;
        this.montant = montant;
        this.date = date;
    }

    /**
     * Obtient l'identifiant de l'enchère.
     *
     * @return L'identifiant de l'enchère.
     */
    public int getIdentifiant() {
        return identifiant;
    }


    /**
     * Obtient la vente associée à l'enchère.
     *
     * @return La vente associée à l'enchère.
     */
    public Vente getVente() {
        return vente;
    }


    /**
     * Obtient l'utilisateur encherisseur.
     *
     * @return L'utilisateur encherisseur.
     */
    public Utilisateur getEncherisseur() {
        return encherisseur;
    }

    /**
     * Obtient le montant de l'enchère.
     *
     * @return Le montant de l'enchère.
     */
    public double getMontant() {
        return montant;
    }

    /**
     * Obtient la date de l'enchère.
     *
     * @return La date de l'enchère.
     */
    public String getDate() {
        return date;
    }

}
