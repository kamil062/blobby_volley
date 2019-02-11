package network;

import java.io.Serializable;

/**
 * Konto gracza, za pomoc� kt�rego po��czono si� z gr�
 * @author Kamil Piec
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Login gracza */
	private String login;
	/** Has�o gracza */
	private String password;
	/** Komenda dla serwera */
	private String type;
	
	/** Czy gracz si� zalogowa� */
	private boolean isOnline = false;
	/** Czy gracz jest w grze */
	private boolean isInGame = false;
	
	/**
	 * Konstruktor ustawiaj�cy pola klasy 
	 * @param login Login gracza
	 * @param password Has�o gracza
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
	 * Metoda zwraca has�o gracza
	 * @return Has�o gracza
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metoda zmienia has�o gracza
	 * @param password Nowe has�o gracza
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Metoda zwraca komend� dla serwera
	 * @return Komenda dla serwera
	 */
	public String getType() {
		return type;
	}

	/**
	 * Metoda ustawia komend� dla serwera
	 * @param type Komenda dla serwera
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Metoda zwraca informacj� czy gracz jest online
	 * @return true je�li gracz jest online
	 */
	public boolean isOnline() {
		return isOnline;
	}

	/**
	 * Metoda pozwala ustawi� stan online gracza
	 * @param isOnline Nowy stan online
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * Metoda zwraca informacj� czy gracz jest w grze
	 * @return true je�li gracz jest w grze
	 */
	public boolean isInGame() {
		return isInGame;
	}

	/**
	 * Metoda pozwala zmieni� stan gry gracza
	 * @param isInGame Nowy stan gry gracza
	 */
	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}
}
