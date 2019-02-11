package gui;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Klasa s³u¿y do ³adowania wszystkich grafik z dysku
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class LoadImages {
	/** Tekstura t³a menu g³ównego*/
	private static Texture menuBgTexture = new Texture();
	/** Tekstura t³a gry*/
	private static Texture gameBgTexture = new Texture();
	/** Tekstura pierwszego gracza */
	private static Texture playerBgTexture = new Texture();
	/** Tekstura drugiego gracza */
	private static Texture opponentBgTexture = new Texture();
	/** Tekstura siatki */
	private static Texture netTexture = new Texture();
	/** Tekstura pi³ki */
	private static Texture ballTexture = new Texture();
	/** Tekstura przycisku "graj"*/
	private static Texture menuPlayButtonTexture = new Texture();
	/** Tekstura podœwietlonego przycisku "graj"*/
	private static Texture menuPlayButtonHlTexture = new Texture();
	/** Tekstura przycisku "koniec" */
	private static Texture menuExitButtonTexture = new Texture();
	/** Tekstura podœwietlonego przycisku "koniec"*/
	private static Texture menuExitButtonHlTexture = new Texture();
	/** Tekstura cyfr*/
	private static Texture numbersTexture = new Texture();

	/** Grafika t³a menu g³ównego*/
	private static RectangleShape menuBg;
	/** Grafika t³a gry*/
	private static RectangleShape gameBg;
	/** Grafika pierwszego gracza */
	private static RectangleShape playerBg;
	/** Grafika drugiego gracza */
	private static RectangleShape opponentBg;
	/** Grafika siatki */
	private static RectangleShape net;
	/** Grafika pi³ki */
	private static RectangleShape ball;
	/** Grafika przycisku "graj"*/
	private static RectangleShape menuPlayButton;
	/** Grafika podœwietlonego przycisku "graj"*/
	private static RectangleShape menuPlayButtonHl;
	/** Grafika przycisku "koniec" */
	private static RectangleShape menuExitButton;
	/** Grafika podœwietlonego przycisku "koniec"*/
	private static RectangleShape menuExitButtonHl;
	/** Grafika cyfr*/
	private static RectangleShape numbers;
	
	/**
	 * Klasa ³aduje wszystkie grafiki potrzebne do dzia³ania gry
	 * @throws IOException Wyj¹tek w przypadku b³êdów
	 */
	public static void LoadImages() throws IOException{
		menuBgTexture.loadFromFile(Paths.get("src/images/menu_bg.png"));
		gameBgTexture.loadFromFile(Paths.get("src/images/game_bg.png"));
		playerBgTexture.loadFromFile(Paths.get("src/images/game_gracz1.png"));
		opponentBgTexture.loadFromFile(Paths.get("src/images/game_gracz2.png"));
		netTexture.loadFromFile(Paths.get("src/images/game_siatka2.png"));
		ballTexture.loadFromFile(Paths.get("src/images/game_pilka.png"));
		menuPlayButtonTexture.loadFromFile(Paths.get("src/images/menu_graj.png"));
		menuPlayButtonHlTexture.loadFromFile(Paths.get("src/images/menu_graj_h.png"));
		menuExitButtonTexture.loadFromFile(Paths.get("src/images/menu_koniec.png"));
		menuExitButtonHlTexture.loadFromFile(Paths.get("src/images/menu_koniec_h.png"));
		numbersTexture.loadFromFile(Paths.get("src/images/game_numbers.png"));
		
		menuBg = new RectangleShape(new Vector2f(menuBgTexture.getSize().x, menuBgTexture.getSize().y));
		gameBg = new RectangleShape(new Vector2f(gameBgTexture.getSize().x, gameBgTexture.getSize().y));
		playerBg = new RectangleShape(new Vector2f(playerBgTexture.getSize().x, playerBgTexture.getSize().y));
		opponentBg = new RectangleShape(new Vector2f(opponentBgTexture.getSize().x, opponentBgTexture.getSize().y));
		net = new RectangleShape(new Vector2f(netTexture.getSize().x, netTexture.getSize().y));
		ball = new RectangleShape(new Vector2f(ballTexture.getSize().x, ballTexture.getSize().y));
		menuPlayButton = new RectangleShape(new Vector2f(menuPlayButtonTexture.getSize().x, menuPlayButtonTexture.getSize().y));
		menuPlayButtonHl = new RectangleShape(new Vector2f(menuPlayButtonHlTexture.getSize().x, menuPlayButtonHlTexture.getSize().y));
		menuExitButton = new RectangleShape(new Vector2f(menuExitButtonTexture.getSize().x, menuExitButtonTexture.getSize().y));
		menuExitButtonHl = new RectangleShape(new Vector2f(menuExitButtonHlTexture.getSize().x, menuExitButtonHlTexture.getSize().y));
		numbers = new RectangleShape(new Vector2f(numbersTexture.getSize().x, numbersTexture.getSize().y));
		

		menuBg.setTexture(menuBgTexture);
		gameBg.setTexture(gameBgTexture);
		playerBg.setTexture(playerBgTexture);
		opponentBg.setTexture(opponentBgTexture);
		net.setTexture(netTexture);
		ball.setTexture(ballTexture);
		menuPlayButton.setTexture(menuPlayButtonTexture);
		menuPlayButtonHl.setTexture(menuPlayButtonHlTexture);
		menuExitButton.setTexture(menuExitButtonTexture);
		menuExitButtonHl.setTexture(menuExitButtonHlTexture);
		numbers.setTexture(numbersTexture);
	}

	/**
	 * Metoda zwraca grafikê t³a menu g³ównego
	 * @return Grafika t³a menu g³ównego
	 */
	public static RectangleShape getMenuBg(){
		return menuBg;
	}

	/**
	 * Metoda zwraca grafikê t³a gry
	 * @return Grafika t³a gry
	 */
	public static RectangleShape getGameBg(){
		return gameBg;
	}

	/**
	 * Metoda zwraca grafikê gracza numer 1
	 * @return Grafika gracza numer 1
	 */
	public static RectangleShape getplayerBg(){
		return playerBg;
	}

	/**
	 * Metoda zwraca grafikê gracza numer 2
	 * @return Grafika gracza numer 2
	 */
	public static RectangleShape getopponentBg(){
		return opponentBg;
	}

	/**
	 * Metoda zwraca grafikê przycisku "graj"
	 * @return Grafika przycisku "graj"
	 */
	public static RectangleShape getMenuPlayButton(){
		return menuPlayButton;
	}

	/**
	 * Metoda zwraca grafikê podœwietlonego przycisku "graj"
	 * @return Grafika podœwietlonego przycisku "graj"
	 */
	public static RectangleShape getMenuPlayButtonHl(){
		return menuPlayButtonHl;
	}

	/**
	 * Metoda zwraca grafikê przycisku "koniec"
	 * @return Grafika przycisku "koniec"
	 */
	public static RectangleShape getMenuExitButton(){
		return menuExitButton;
	}

	/**
	 * Metoda zwraca grafikê podœwietlonego przycisku "koniec"
	 * @return Grafika podœwietlonego przycisku "koniec"
	 */
	public static RectangleShape getMenuExitButtonHl(){
		return menuExitButtonHl;
	}

	/**
	 * Metoda zwraca grafikê siatki
	 * @return Grafika siatki
	 */
	public static RectangleShape getNet() {
		return net;
	}

	/**
	 * Metoda zwraca grafikê pi³ki
	 * @return Grafika pi³ki
	 */
	public static RectangleShape getBall() {
		return ball;
	}

	/**
	 * Metoda zwraca grafikê cyfr
	 * @return Grafika cyfr
	 */
	public static RectangleShape getNumbers() {
		return numbers;
	}
	
	/**
	 * Metoda zwraca grafikê wybranej cyfry
	 * @param n Cyfra z zakresu 0-9
	 * @return Grafika odpowiedniej cyfry
	 */
	public static RectangleShape getNumber(int n){
		RectangleShape r = new RectangleShape(new Vector2f(36, 41));
		r.setTexture(numbersTexture);
		r.setTextureRect(new IntRect(36*n, 0, 36, 41));
		return r;
	}
}
