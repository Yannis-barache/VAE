import java.sql.*;

public class ConnexionMySQL {

	/**
	 * La connexion à la base de données.
	 */
	private Connection mysql;

	/**
	 * Indique si la connexion est établie.
	 */
	private boolean connecte=false;

	/**
	 * Constructeur de la classe ConnexionMySQL.
	 * @throws ClassNotFoundException Si le driver n'est pas trouvé.
	 */
	public ConnexionMySQL() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("org.mariadb.jdbc.Driver");
	}

	/**
	 * Connecte à la base de données.
	 * @param nomServeur Le nom du serveur.
	 * @param nomBase    Le nom de la base de données.
	 * @param nomLogin   Le nom du login.
	 * @param motDePasse Le mot de passe.
	 * @throws SQLException Si la connexion échoue.
	 */
	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		this.mysql=null;
		this.connecte=false;
		this.mysql = DriverManager.getConnection(
					"jdbc:mysql://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
		this.connecte=true;
	}

	/**
	 * Ferme la connexion à la base de données.
	 * @throws SQLException Si la fermeture échoue.
	 */
	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

	/**
	 * Indique si la connexion est établie.
	 * @return true si la connexion est établie, false sinon.
	 */
    public boolean isConnecte() { return this.connecte;}

	/**
	 * Crée un objet Blob.
	 * @return Un objet Blob.
	 * @throws SQLException Si la création échoue.
	 */
    public Blob createBlob()throws SQLException{
		return this.mysql.createBlob();
	}

	/**
	 * Crée un objet Statement.
	 * @return Un objet Statement.
	 * @throws SQLException Si la création échoue.
	 */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	/**
	 * Crée un objet PreparedStatement.
	 * @param requete La requête SQL à préparer.
	 * @return Un objet PreparedStatement.
	 * @throws SQLException Si la création échoue.
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
