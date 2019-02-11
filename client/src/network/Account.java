package network;

import java.io.Serializable;

/**
 * Konto gracza, za pomoc¹ którego po³¹czono siê z gr¹
 * @author Kamil Piec
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Login gracza */
	private String login;
	/** Has³o gracza */
	private String password;
	/** Komenda dla serwera */
	private String type;
	
	/** Czy gracz siê zalogowa³ */
	private boolean isOnline = false;
	/** Czy gracz jest w grze */
	private boolean isInGame = false;
	
	/**
	 * Konstruktor ustawiaj¹cy pola klasy 
	 * @param login Login gracza
	 * @param password Has³o gracza
	 * @param type Komenda dla serwera
	 */
	public Account(String login, String password, String type){
		this.login = login.trim();
		this.password = password.trim();
		this.type = type.trim();
	}

	/**
	 * Metoda zwraca login gracza
	 * @return Login gracza
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metoda ustawia login gracza
	 * @param login Nowy login gracza
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metoda zwraca has³o gracza
	 * @return Has³o gracza
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metoda zmienia has³o gracza
	 * @param password Nowe has³o gracza
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Metoda zwraca komendê dla serwera
	 * @return Komenda dla serwera
	 */
	public String getType() {
		return type;
	}

	/**
	 * Metoda ustawia komendê dla serwera
	 * @param type Komenda dla serwera
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Metoda zwraca informacjê czy gracz jest online
	 * @return true jeœli gracz jest online
	 */
	public boolean isOnline() {
		return isOnline;
	}

	/**
	 * Metoda pozwala ustawiæ stan online gracza
	 * @param isOnline Nowy stan online
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * Metoda zwraca informacjê czy gracz jest w grze
	 * @return true jeœli gracz jest w grze
	 */
	public boolean isInGame() {
		return isInGame;
	}

	/**
	 * Metoda pozwala zmieniæ stan gry gracza
	 * @param isInGame Nowy stan gry gracza
	 */
	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}
}
