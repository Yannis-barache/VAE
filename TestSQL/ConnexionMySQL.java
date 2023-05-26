import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql;
	private boolean connecte=false;
	public ConnexionMySQL() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("org.mariadb.jdbc.Driver");
	}

	/**
	 * Permet de se connecter à la base de données MySQL en utilisant le driver JDBC et les paramètres de connexion
	 * @param nomServeur Un String correspondant au nom du serveur
	 * @param nomBase Un String correspondant au nom de la base de données
	 * @param nomLogin Un String correspondant au nom du login
	 * @param motDePasse Un String correspondant au mot de passe
	 * @throws SQLException Si la connexion échoue
	 */
	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		this.mysql=null;
		this.connecte=false;
		this.mysql = DriverManager.getConnection(
					"jdbc:mysql://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
		this.connecte=true;
	}


	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}


	/**
	 * Permet de savoir si la connexion est établie
	 * @return Un booléen indiquant si la connexion est établie, true si c'est le cas, false sinon
	 */
    public boolean isConnecte() { return this.connecte;}
	
	/**
	 * Permet de créer un objet Blob
	 * @return Un objet Blob 
	 * @throws SQLException Si la création échoue
	 * @see Blob
	 */
    public Blob createBlob()throws SQLException{
		return this.mysql.createBlob();
	}

	/**
	 * Permet de créer un objet Statement
	 * @return Un objet Statement permettant d'effectuer des requêtes SQL
	 * @throws SQLException Si la création échoue
	 * @see Statement
	 */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	/**
	 * Permet de créer un objet PreparedStatement
	 * @param requete Un String correspondant à la requête SQL
	 * @return Un objet PreparedStatement permettant d'effectuer des requêtes SQL
	 * @throws SQLException Si la création échoue
	 * @see PreparedStatement
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
}
