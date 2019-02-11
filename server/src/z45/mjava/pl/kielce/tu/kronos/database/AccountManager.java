package database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import main.Config;

/**
 * Klasa s�u�y do zarz�dzania kontem gracza - logowanie, rejestracja, wylogowywanie
 * @author Kamil Piec
 */
public class AccountManager {
	/**
	 * Metoda s�u�y do tworzenia nowego konta. Wywo�ywana jest procedura sk�adowana w bazie danych i zwracana odpowied� z bazy.
	 * @param login Login nowego gracza
	 * @param password Has�o nowego gracza
	 * @return Wiadomo�� zwrotna z bazy danych
	 */
	public static String registerAccount(String login, String password){
		try {
			CallableStatement cs = Config.getDbConnection().prepareCall("{call addPlayer(?,?,?)}");
			cs.setString(1, login);
			cs.setString(2, password);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.executeUpdate();
			return cs.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "b��d";
	}
	
	/**
	 * Metoda s�u�y do logowania si� na konto. Wywo�ywana jest procedura sk�adowana w bazie danych i zwracana odpowied� z bazy.
	 * @param login Login gracza
	 * @param password Has�o gracza
	 * @return Wiadomo�� zwrotna z bazy danych.
	 */
	public static String[] loginAccount(String login, String password){
		try {
			CallableStatement cs = Config.getDbConnection().prepareCall("{call loginPlayer(?,?,?,?)}");
			cs.setString(1, login);
			cs.setString(2, password);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.executeUpdate();
			String[] ret = new String[2];
			ret[0] = cs.getString(3);
			ret[1] = "" + cs.getInt(4);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metoda s�u�y do wylogowywania gracza. Wywo�ywana jest procedura sk�adowana w bazie danych i zwracana odpowied� z bazy.
	 * @param login Login gracza
	 * @return Wiadomo�� zwrotna z bazy danych
	 */
	public static String logoutAccount(String login){
		try {
			CallableStatement cs = Config.getDbConnection().prepareCall("{call logoutPlayer(?,?)}");
			cs.setString(1, login);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.executeUpdate();
			String ret = "" + cs.getInt(2);
			System.out.println("logout: " + ret);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "b��d";
	}
}
