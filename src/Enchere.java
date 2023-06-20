public class Enchere {
    private Vente vente;
    private Utilisateur encherisseur;
    private int montant;
    private String date;

    /**
     * Constructeur de la classe Enchere.
     *
     * @param vente         La vente associée à l'enchère.
     * @param encherisseur  L'utilisateur encherisseur.
     * @param montant       Le montant de l'enchère.
     * @param date          La date de l'enchère.
     */
    public Enchere(Vente vente, Utilisateur encherisseur, int montant, String date) {
        this.vente = vente;
        this.encherisseur = encherisseur;
        this.montant = montant;
        this.date = date;
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
    public int getMontant() {
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

    @Override
    public String toString(){
        return ""+this.getVente()+" "+this.getEncherisseur().getPseudo()+" "+this.getMontant()+" "+this.getDate();
    }
}
