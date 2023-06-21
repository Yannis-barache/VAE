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
        return "Enchere(Vente : "+this.getVente()+", Encherrisseur : "+this.getEncherisseur().getPseudo()+", Montant : "+this.getMontant()+", Date : "+this.getDate()+")";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (obj == null){
            return false;       
        }

        if (obj instanceof Enchere){
            Enchere e = (Enchere) obj;
            if (e.getVente().getIdentifiant() == this.getVente().getIdentifiant() && this.getDate().equals(e.getDate()) && this.getEncherisseur().getIdentifiant() == e.getEncherisseur().getIdentifiant()) {
                return true;
            }
        }
        return false;
    }
}
