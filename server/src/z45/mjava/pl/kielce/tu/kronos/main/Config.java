package main;

import java.net.ServerSocket;
import java.sql.Connection;

/**
 * Klasa zawiera konfiguracjê serwera
 * @author Kamil Piec
 */
public class Config {
	/** Maksymalna iloœæ klientów, jaka mo¿e siê pod³¹czyæ do serwera */
	private static int maxClients = 2;
	/** Port, na którym dzia³a serwer */
	private static int port = 8001;
	/** Gniazdo sieciowe serwera */
	private static ServerSocket serverSocket;
	/** Gniazda sieciowe klientów */
	public static Client[] clients = new Client[maxClients];
	/** Adres URL do po³¹czenia z baz¹ danych */
	private static String DBurl = "jdbc:oracle:thin:@localhost:1521/xe";
	/** Nazwa u¿ytkownika bazy danych */
	private static String DBuser = "system";
	/** Has³o u¿ytkownika bazy danych */
	private static String DBpassword = "root";
	/** Uchwyt po³¹czenia z baz¹ danych */
	private static Connection dbConnection;
	
	/**
	 * Metoda zwraca maksymaln¹ iloœæ klientów, jaka mo¿e siê pod³¹czyæ do serwera
	 * @return Iloœæ klientów
	 */
	public static int getMaxClients() {
		return maxClients;
	}

	/**
	 * Metoda pozwala zmieniæ maksymaln¹ iloœæ klientów, jaka mo¿e siê pod³¹czyæ do serwera
	 * @param maxClients Nowa iloœæ klientów
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
	 * Metoda pozwala zmieniæ gniazdo sieciowe serwera
	 * @param serverSocket Nowe gniazdo sieciowe serwera
	 */
	public static void setServerSocket(ServerSocket serverSocket) {
		Config.serverSocket = serverSocket;
	}

	/**
	 * Metoda zwraca adres URL do po³¹czenia z baz¹ danych
	 * @return Adres URL
	 */
	public static String getDBurl() {
		return DBurl;
	}

	/**
	 * Metoda pozwala zmieniæ adres URL do po³¹czenia z baz¹ danych
	 * @param dBurl Nowy adres URL
	 */
	public static void setDBurl(String dBurl) {
		DBurl = dBurl;
	}

	/**
	 * Metoda zwraca nazwê u¿ytkownika bazy danych
	 * @return Nazwa u¿ytkownika bazy danych
	 */
	public static String getDBuser() {
		return DBuser;
	}

	/**
	 * Metoda pozwala zmieniæ nazwê u¿ytkownika bazy danych
	 * @param dBuser Nowa nazwa u¿ytkownika bazy danych
	 */
	public static void setDBuser(String dBuser) {
		DBuser = dBuser;
	}

	/**
	 * Metoda zwraca has³o u¿ytkownika bazy danych
	 * @return Has³o uzytkownika bazy danych
	 */
	public static String getDBpassword() {
		return DBpassword;
	}

	/**
	 * Metoda pozwala zmieniæ has³o u¿ytkownika bazy danych
	 * @param dBpassword Nowe has³o u¿ytkownika bazy danych
	 */
	public static void setDBpassword(String dBpassword) {
		DBpassword = dBpassword;
	}

	/**
	 * Metoda zwraca uchwyt po³¹czenia z baz¹ danych
	 * @return Uchwyt po³¹czenia z baz¹ danych
	 */
	public static Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * Metoda zmienia uchwyt po³¹czenia z baz¹ danych
	 * @param dbConnection Nowy uchwyt po³¹czenia z baz¹ danych
	 */
	public static void setDbConnection(Connection dbConnection) {
		Config.dbConnection = dbConnection;
	}
}
