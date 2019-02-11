package game;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import gui.LoadImages;

/**
 * Klasa zawiera pojedynczego gracza
 * 
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Player {
	/** Pozycja gracza na ekranie */
	private Vector2f position;
	/** Ilo�� punkt�w zdobyta przez gracza podczas gry */
	private int points;
	/** Typ gracza */
	private PlayerType type;
	/** Grafika gracza */
	private RectangleShape background;
	
	/** Czy gracz aktualnie skacze */
	private boolean isJumping = false;
	/** Kierunek skoku (g�ra/d�) */
	private int jumpDirection = 0;

	/**
	 * Konstruktor zeruje wszystkie pola
	 */
	public Player(){
		setPosition(new Vector2f(0, 0));
		setPoints(0);
		setType(PlayerType.PLAYER1);
		setBackground(LoadImages.getplayerBg());
	}
	
	/**
	 * Konstruktor pozwala ustawi� parametry gracza
	 * @param x Pozycja x gracza
	 * @param y Pozycja y gracza
	 * @param type Typ gracza
	 */
	public Player(float x, float y, PlayerType type){
		setPosition(new Vector2f(x, y));
		setPoints(0);
		setType(type);
		if(getType() == PlayerType.PLAYER1)
			setBackground(LoadImages.getplayerBg());
		else
			setBackground(LoadImages.getopponentBg());
	}

	/**
	 * Metoda pozwala uzyska� dost�p do pozycji gracza
	 * @return Pozycja gracza na ekranie
	 */
	public Vector2f getPosition() {
		return position;
	}
	
	/**
	 * Metoda pozwala zmieni� pozycj� gracza
	 * @param position Nowa pozycja gracza na ekranie
	 */
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	/**
	 * Metoda pozwala uzyska� dost�p do ilo�ci punkt�w gracza
	 * @return Ilo�� punkt�w gracza
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Metoda pozwala zmieni� ilo�� punkt�w gracza
	 * @param points Nowa ilo�� punkt�w gracza
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Metoda pozwala uzyska� dost�p do typu gracza
	 * @return Typ gracza
	 */
	public PlayerType getType() {
		return type;
	}

	/**
	 * Metoda pozwala zmieni� typ gracza
	 * @param type Nowy typ gracza
	 */
	public void setType(PlayerType type) {
		this.type = type;
	}

	/**
	 * Metoda pozwwala uzyska� dost�p do grafiki gracza
	 * @return Grafika gracza
	 */
	public RectangleShape getBackground() {
		return background;
	}

	/**
	 * Metoda pozwala zmieni� grafik� gracza
	 * @param background Nowa grafika gracza
	 */
	public void setBackground(RectangleShape background) {
		this.background = background;
	}

	/**
	 * Metoda zwraca informacj� czy gracz skacze
	 * @return true je�li gracz skacze, false je�li nie
	 */
	public boolean isJumping() {
		return isJumping;
	}

	/**
	 * Metoda pozwala ustawi� stan skou gracza
	 * @param isJumping Nowy stan skoku gracza
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	/**
	 * Metoda pozwala uzyska� dost�p do kierunku skoku gracza
	 * @return Kierunek skoku
	 */
	public int getJumpDirection() {
		return jumpDirection;
	}

	/**
	 * Metoda pozwala ustawi� kierunek skoku gracza
	 * @param jumpDirection Kierunek skoku gracza
	 */
	public void setJumpDirection(int jumpDirection) {
		this.jumpDirection = jumpDirection;
	}
}