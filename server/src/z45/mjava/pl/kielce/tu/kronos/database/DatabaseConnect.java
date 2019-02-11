package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import main.Config;

/**
 * Klasa s³u¿y do ³¹czenia siê z baz¹ danych
 * @author Kamil Piec
 */
public class DatabaseConnect implements Runnable{
	/** Czy po³¹czono z baz¹ danych */
	private static boolean connected = false;
	
	/**
	 * Metoda ³¹czy serwer z baz¹ danych.
	 * Nastêpuje próba nawi¹zania po³¹czenia i wyœwietlana jest stosowna wiadomoœæ
	 */
	private void connect(){
		try {
			Config.setDbConnection(DriverManager.getConnection(Config.getDBurl(), Config.getDBuser(), Config.getDBpassword()));
	
			if (Config.getDbConnection() != null){
				System.out.println("Database connected");
				setConnected(true);
			}
			else 
				System.out.println("Database connection failed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda koñczy po³¹czenie z baz¹ danych
	 */
	public static void endConnection(){
		try {
			Config.getDbConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Metoda zawiera polecenie po³¹czenia siê baz¹ danych wykonywane w ramach w¹tku
	 */
	public void run(){
		connect();
	}

	/**
	 * Metoda zwraca informacjê o po³¹czeniu
	 * @return true jeœli po³¹czono z baz¹ danych
	 */
	public static boolean isConnected() {
		return connected;
	}

	/**
	 * Metoda ustawia stan po³¹czenia z baz¹ danych
	 * @param connected Stan po³¹czenia z baz¹ danych
	 */
	public static void setConnected(boolean connected) {
		DatabaseConnect.connected = connected;
	}
}
