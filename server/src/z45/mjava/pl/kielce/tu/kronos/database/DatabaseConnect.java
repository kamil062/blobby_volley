package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import main.Config;

/**
 * Klasa s�u�y do ��czenia si� z baz� danych
 * @author Kamil Piec
 */
public class DatabaseConnect implements Runnable{
	/** Czy po��czono z baz� danych */
	private static boolean connected = false;
	
	/**
	 * Metoda ��czy serwer z baz� danych.
	 * Nast�puje pr�ba nawi�zania po��czenia i wy�wietlana jest stosowna wiadomo��
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
	 * Metoda ko�czy po��czenie z baz� danych
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
	 * Metoda zawiera polecenie po��czenia si� baz� danych wykonywane w ramach w�tku
	 */
	public void run(){
		connect();
	}

	/**
	 * Metoda zwraca informacj� o po��czeniu
	 * @return true je�li po��czono z baz� danych
	 */
	public static boolean isConnected() {
		return connected;
	}

	/**
	 * Metoda ustawia stan po��czenia z baz� danych
	 * @param connected Stan po��czenia z baz� danych
	 */
	public static void setConnected(boolean connected) {
		DatabaseConnect.connected = connected;
	}
}
