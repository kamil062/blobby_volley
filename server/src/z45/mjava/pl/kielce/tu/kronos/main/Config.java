package main;

import java.net.ServerSocket;
import java.sql.Connection;

/**
 * Klasa zawiera konfiguracj� serwera
 * @author Kamil Piec
 */
public class Config {
	/** Maksymalna ilo�� klient�w, jaka mo�e si� pod��czy� do serwera */
	private static int maxClients = 2;
	/** Port, na kt�rym dzia�a serwer */
	private static int port = 8001;
	/** Gniazdo sieciowe serwera */
	private static ServerSocket serverSocket;
	/** Gniazda sieciowe klient�w */
	public static Client[] clients = new Client[maxClients];
	/** Adres URL do po��czenia z baz� danych */
	private static String DBurl = "jdbc:oracle:thin:@localhost:1521/xe";
	/** Nazwa u�ytkownika bazy danych */
	private static String DBuser = "system";
	/** Has�o u�ytkownika bazy danych */
	private static String DBpassword = "root";
	/** Uchwyt po��czenia z baz� danych */
	private static Connection dbConnection;
	
	/**
	 * Metoda zwraca maksymaln� ilo�� klient�w, jaka mo�e si� pod��czy� do serwera
	 * @return Ilo�� klient�w
	 */
	public static int getMaxClients() {
		return maxClients;
	}

	/**
	 * Metoda pozwala zmieni� maksymaln� ilo�� klient�w, jaka mo�e si� pod��czy� do serwera
	 * @param maxClients Nowa ilo�� klient�w
	 */
	public static void setMaxClients(int maxClients) {
		Config.maxClients = maxClients;
	}

	/**
	 * Metoda zwraca port serwera
	 * @return Port serwera
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * Metoda zmienia port serwera
	 * @param port Nowy port serwera
	 */
	public static void setPort(int port) {
		Config.port = port;
	}

	/**
	 * Metoda zwraca gniazdo sieciowe serwera
	 * @return Gniazdo sieciowe serwera
	 */
	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * Metoda pozwala zmieni� gniazdo sieciowe serwera
	 * @param serverSocket Nowe gniazdo sieciowe serwera
	 */
	public static void setServerSocket(ServerSocket serverSocket) {
		Config.serverSocket = serverSocket;
	}

	/**
	 * Metoda zwraca adres URL do po��czenia z baz� danych
	 * @return Adres URL
	 */
	public static String getDBurl() {
		return DBurl;
	}

	/**
	 * Metoda pozwala zmieni� adres URL do po��czenia z baz� danych
	 * @param dBurl Nowy adres URL
	 */
	public static void setDBurl(String dBurl) {
		DBurl = dBurl;
	}

	/**
	 * Metoda zwraca nazw� u�ytkownika bazy danych
	 * @return Nazwa u�ytkownika bazy danych
	 */
	public static String getDBuser() {
		return DBuser;
	}

	/**
	 * Metoda pozwala zmieni� nazw� u�ytkownika bazy danych
	 * @param dBuser Nowa nazwa u�ytkownika bazy danych
	 */
	public static void setDBuser(String dBuser) {
		DBuser = dBuser;
	}

	/**
	 * Metoda zwraca has�o u�ytkownika bazy danych
	 * @return Has�o uzytkownika bazy danych
	 */
	public static String getDBpassword() {
		return DBpassword;
	}

	/**
	 * Metoda pozwala zmieni� has�o u�ytkownika bazy danych
	 * @param dBpassword Nowe has�o u�ytkownika bazy danych
	 */
	public static void setDBpassword(String dBpassword) {
		DBpassword = dBpassword;
	}

	/**
	 * Metoda zwraca uchwyt po��czenia z baz� danych
	 * @return Uchwyt po��czenia z baz� danych
	 */
	public static Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * Metoda zmienia uchwyt po��czenia z baz� danych
	 * @param dbConnection Nowy uchwyt po��czenia z baz� danych
	 */
	public static void setDbConnection(Connection dbConnection) {
		Config.dbConnection = dbConnection;
	}
}
