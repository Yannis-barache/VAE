public class Vente {
    private int identifiant;
    private int prixBase;
    private int prixMin;
    private String debutVente;
    private String finVente;
    private String status;
    private Objet objet;
    /**
     * Constructeur de la classe Vente.
     *
     * @param identifiant L'identifiant de la vente.
     * @param prixBase    Le prix de base de la vente.
     * @param prixMin     Le prix minimum de la vente.
     * @param debutVente  La date de début de la vente.
     * @param finVente    La date de fin de la vente.
     * @param status      Le statut de la vente.
     * @param objet       L'objet mis en vente.
     */
    public Vente(int identifiant, int prixBase, int prixMin, String debutVente, String finVente, String status, Objet objet) {
        this.identifiant = identifiant;
        this.prixBase = prixBase;
        this.prixMin = prixMin;
        this.debutVente = debutVente;
        this.finVente = finVente;
        this.status = status;
        this.objet = objet;
    }

    /**
     * Obtient l'identifiant de la vente.
     *
     * @return L'identifiant de la vente.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit l'identifiant de la vente.
     *
     * @param identifiant Le nouvel identifiant de la vente.
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Obtient le prix de base de la vente.
     *
     * @return Le prix de base de la vente.
     */
    public int getPrixBase() {
        return prixBase;
    }

    /**
     * Définit le prix de base de la vente.
     *
     * @param prixBase Le nouveau prix de base de la vente.
     */
    public void setPrixBase(int prixBase) {
        this.prixBase = prixBase;
    }

    /**
     * Obtient le prix minimum de la vente.
     *
     * @return Le prix minimum de la vente.
     */
    public int getPrixMin() {
        return prixMin;
    }

    /**
     * Définit le prix minimum de la vente.
     *
     * @param prixMin Le nouveau prix minimum de la vente.
     */
    public void setPrixMin(int prixMin) {
        this.prixMin = prixMin;
    }

    /**
     * Obtient la date de début de la vente.
     *
     * @return La date de début de la vente.
     */
    public String getdebutVente() {
        return debutVente;
    }

    /**
     * Définit la date de début de la vente.
     *
     * @param debutVente La nouvelle date de début de la vente.
     */
    public void setdebutVente(String debutVente) {
        this.debutVente = debutVente;
    }

    /**
     * Obtient la date de fin de la vente.
     *
     * @return La date de fin de la vente.
     */
    public String getFinVente() {
        return finVente;
    }

    /**
     * Définit la date de fin de la vente.
     *
     * @param finVente La nouvelle date de fin de la vente.
     */
    public void setFinVente(String finVente) {
        this.finVente = finVente;
    }

    /**
     * Obtient le statut de la vente.
     *
     * @return Le statut de la vente.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Définit le statut de la vente.
     *
     * @param status Le nouveau statut de la vente.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtient l'objet mis en vente.
     *
     * @return L'objet mis en vente.
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * Définit l'objet mis en vente.
     *
     * @param objet Le nouvel objet mis en vente.
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }


}
