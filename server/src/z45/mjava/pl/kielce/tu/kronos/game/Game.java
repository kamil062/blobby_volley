package game;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import main.Config;

/**
 * Klasa zawiera polecenia pozwalaj�ce obs�ugiwa� operacje na bazie danych
 * @author Kamil Piec
 */
public class Game {
	/**
	 * Dla podanego gracza metoda zwraca informacj� czy jest on w jakiej� grze
	 * @param player Nazwa gracza
	 * @return true je�li gracz jest w grze
	 */
	public static boolean isInGame(String player){
		CallableStatement cs = null;
		boolean ret = false;
		try {
			cs = Config.getDbConnection().prepareCall("{call isInGame(?,?)}");
			cs.setString(1, player);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.executeUpdate();
			if(cs.getString(2).equals("0"))
				ret = false;
			else
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}	
	
	/**
	 * Dla podanego gracza metoda tworzy now� gr�
	 * @param player Nazwa gracza
	 */
	public static void createGame(String player){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call createGame(?)}");
			cs.setString(1, player);
			System.out.println(player);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda zwraca informacj� czy istnieje jaka� gra, w kt�rej kto� czeka na drugiego gracza
	 * @return Je�eli znaleziono gr� to jej identyfikator, w przeciwnym wypadku 0
	 */
	public static int isFreeGame(){
		CallableStatement cs = null;
		int ret = 0;
		try {
			cs = Config.getDbConnection().prepareCall("{call isFreeGame(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.executeUpdate();
			ret = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda do��cza gracza do danej gry
	 * @param player Nazwa gracza
	 * @param game ID gry
	 * @return Informacja zwrotna z bazy danych czy operacja si� uda�a
	 */
	public static int joinToGame(String player, int game){
		CallableStatement cs = null;
		int ret = 0;
		try {
			cs = Config.getDbConnection().prepareCall("{call joinToGame(?,?,?)}");
			cs.setString(1, player);
			cs.setInt(2, game);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.executeUpdate();
			ret = cs.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda usuwa danego gracza z gry
	 * @param player Nazwa gracza
	 */
	public static void exitFromGame(String player){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call exitFromGame(?)}");
			cs.setString(1, player);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda usuwa wszystkie gry, w kt�rych nie ma �danych graczy
	 */
	public static void deleteEmptyGames(){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call deleteEmptyGames}");
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda aktualizuje pozycj� gracza w bazie danych
	 * @param player Nazwa gracza
	 * @param x Pozycja x
	 * @param y Pozycja y
	 */
	public static void updatePos(String player, int x, int y){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call updatePos(?,?,?)}");
			cs.setString(1, player);
			cs.setInt(2, x);
			cs.setInt(3, y);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda zwraca pozycj� przeciwnika danego gracza
	 * @param player Nazwa gracza
	 * @return Tablica z pozycj� gracza
	 */
	public static int[] getOpponentPos(String player){
		CallableStatement cs = null;
		int[] ret = new int[2];
		try {
			cs = Config.getDbConnection().prepareCall("{call getOpponentPos(?,?,?)}");
			cs.setString(1, player);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.executeUpdate();
			ret[0] = cs.getInt(2);
			ret[1] = cs.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda zwraca punkty graczy w grze, w kt�rej znajduje si� zadany gracz
	 * @param player Nazwa gracza
	 * @return Tablica z ilo�ci� punkt�w obu graczy w grze
	 */
	public static int[] getPoints(String player){
		CallableStatement cs = null;
		int[] ret = new int[2];
		try {
			cs = Config.getDbConnection().prepareCall("{call getPoints(?,?,?)}");
			cs.setString(1, player);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.executeUpdate();
			ret[0] = cs.getInt(2);
			ret[1] = cs.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda przyznaje punkt przeciwnikowi danego gracza
	 * @param player Nazwa gracza
	 */
	public static void scoreOpponent(String player){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call scoreOpponent(?)}");
			cs.setString(1, player);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda przyznaje punkt danemu graczowi
	 * @param player Nazwa gracza
	 */
	public static void scorePlayer(String player){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call scorePlayer(?)}");
			cs.setString(1, player);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda zwraca pozycj� pi�ki z gry, w kt�rej znajduje si� dany gracz
	 * @param player Nazwa gracza
	 * @return Pozycja pi�ki
	 */
	public static int[] getBallPos(String player){
		CallableStatement cs = null;
		int[] ret = new int[2];
		try {
			cs = Config.getDbConnection().prepareCall("{call getBallPos(?,?,?)}");
			cs.setString(1, player);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.executeUpdate();
			ret[0] = cs.getInt(2);
			ret[1] = cs.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda aktualizuje pozycj� pi�ki w grze, w kt�rej znajduje si� dany gracz
	 * @param player Nazwa gracza
	 * @param x Pozycja x pi�ki
	 * @param y Pozycja y pi�ki
	 */
	public static void updateBallPos(String player, int x, int y){
		CallableStatement cs = null;
		try {
			cs = Config.getDbConnection().prepareCall("{call updateBallPos(?,?,?)}");
			cs.setString(1, player);
			cs.setInt(2, x);
			cs.setInt(3, y);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda zwraca informacj� o tym czy gra danego gracza si� ju� rozpocz�a
	 * @param player Nazwa gracza
	 * @return 1 je�li gra si� rozpocze�a
	 */
	public static int isGameRunning(String player){
		CallableStatement cs = null;
		int ret = 0;
		try {
			cs = Config.getDbConnection().prepareCall("{call isGameRunning(?,?)}");
			cs.setString(1, player);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.executeUpdate();
			ret = cs.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * Metoda zwraca informacj� czy gra danego gracza si� sko�czy�a
	 * @param player Nazwa gracza
	 * @return 1 je�li wygra� gracz 1, 2 je�li wygra� gracz 2, 0 w przeciwnym wypadku
	 */
	public static int checkEnd(String player){
		int[] points = getPoints(player);
		if(points[0] == 9) return 1;
		if(points[1] == 9) return 2;
		return 0;
	}
}
