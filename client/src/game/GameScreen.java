package game;

import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import gui.LoadImages;
import gui.Window;
import main.Config;
import network.Get;
import network.Send;

/**
 * Klasa s�u�y do obs�ugi gry.
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class GameScreen {
	/** Pole zawiera pierwsz� posta� */
	public static Player playerOne = new Player(10, 400, PlayerType.PLAYER1);
	/** Pole zawiera drug� posta� */
	public static Player playerTwo = new Player(850, 400, PlayerType.PLAYER2);

	/** Pole zawiera referencj� do postaci, kt�r� steruje gracz */
	private static Player player;
	/** Pole zawieta referencj� do postaci, kt�r� steruje przeciwnik */
	private static Player opponent;

	/** Pole zawiera kierunek poziomy pi�ki;
	 * 1 - ruch w prawo
	 * 0 - ruch w lewo
	 */
	private static int ballXDirection = 1;
	/** Pole zawiera kierunek pionowy pi�ki
	 * 1 - ruch w g�r�
	 * 0 - ruch w d�
	 */
	private static int ballYDirection = 1;
	
	/**
	 * Metoda rysuje na ekranie t�o gry
	 */
	public static void DrawBackground(){
		Window.getWindow().draw(LoadImages.getGameBg());
	}
	
	/**
	 * Metoda rysuje na ekranie siatk�
	 */
	public static void DrawNet(){
		LoadImages.getNet().setPosition(500, 250);
		Window.getWindow().draw(LoadImages.getNet());
	}
	
	/**
	 * Metoda rysuje na ekranie pi�k�
	 */
	public static void DrawBall(){
		Window.getWindow().draw(LoadImages.getBall());
	}
	
	/**
	 * Metoda rysuje na ekranie obydwu graczy
	 */
	public static void DrawPlayers(){
		//Ustawienie pozycji graczy
		playerOne.getBackground().setPosition(playerOne.getPosition());
		playerTwo.getBackground().setPosition(playerTwo.getPosition());
		
		//Narysowanie graczy
		Window.getWindow().draw(playerOne.getBackground());
		Window.getWindow().draw(playerTwo.getBackground());
	}
	
	/**
	 * Metoda rysuje na ekranie punktacj�
	 */
	public static void DrawPoints(){
		//Pobranie grafik z punktami
		RectangleShape points1 = LoadImages.getNumber(playerOne.getPoints());
		RectangleShape points2 = LoadImages.getNumber(playerTwo.getPoints());

		//Ustawienie pozycji grafik
		points1.setPosition(0, 0);
		points2.setPosition(Config.getWindowSize().x - points1.getSize().x, 0);

		//Narysowanie grafik
		Window.getWindow().draw(points1);
		Window.getWindow().draw(points2);
	}
	
	/**
	 * Metoda sprawdza stan klawiszy odpowiadaj�cych za poruszanie si� graczem <br>
	 * W przypadku wykrycia naci�ni�cia przycisku realizuje odpowiedni� akcj� (ruch/skok)
	 */
	public static void CheckKeys(){
		//Ruch w lewo
	    if(Window.keyLeftPressed){
	    	if(LoadImages.getNet().getGlobalBounds().intersection(player.getBackground().getGlobalBounds()) == null)
	    		player.setPosition(new Vector2f(player.getPosition().x - 3, player.getPosition().y));
	    	else
	    		player.setPosition(new Vector2f(player.getPosition().x + 6, player.getPosition().y));
	    }
	 
	    //Ruch w prawo
	    if(Window.keyRightPressed)
	    	if(LoadImages.getNet().getGlobalBounds().intersection(player.getBackground().getGlobalBounds()) == null)
    			player.setPosition(new Vector2f(player.getPosition().x + 3, player.getPosition().y));
	    	else
    			player.setPosition(new Vector2f(player.getPosition().x - 6, player.getPosition().y));
	  
	    //Skok
	    if(player.isJumping()){
	    	if(player.getJumpDirection() == 0){
	    		if(player.getPosition().y > 150){
	    			player.setPosition(new Vector2f(player.getPosition().x, player.getPosition().y - 5));
	    		} else {
	    			player.setJumpDirection(1);
	    		}
	    	} else {
	    		if(player.getPosition().y < 400){
	    			player.setPosition(new Vector2f(player.getPosition().x, player.getPosition().y + 5));
	    		} else {
	    			player.setJumpDirection(0);
	    			player.setJumping(false);
	    		}
	    	}
	    } else {
	    	if(Window.keyUpPressed) player.setJumping(true);
	    }
	}
	
	/**
	 * Metoda odpowiada za akcje zwi�zane z ruchem pi�ki, m.in. <br>
	 * - poruszanie pi�k� po ekranie <br>
	 * - wykrywanie kolizji <br>
	 * - zaliczanie punkt�w graczom <br>
	 */
	public static void MoveBall(){
		//Pocz�tkowa pozycja pi�ki
		float x = LoadImages.getBall().getPosition().x;
		float y = LoadImages.getBall().getPosition().y;
			
		//Ruch nast�puje, dop�ki trwa gra
		if(isGameRunning()){
			//Sprawdzanie czy pi�ka upad�a na pod�og� i zaliczenie punktu odpowiedniemu graczowi
			if(y >= 400) {
				if(x <= 500) Send.SendObject("scoreOpponent:"+Config.getMainAccount().getLogin());
				else Send.SendObject("scorePlayer:"+Config.getMainAccount().getLogin());
				if(player == playerOne) {
					LoadImages.getBall().setPosition(900, 300);
					ballXDirection = 1;
					ballYDirection = 1;
				} else {
					LoadImages.getBall().setPosition(20, 300);
					ballXDirection = 0;
					ballYDirection = 1;
				}
				
				return;
			}
			
			//Modyfikacja po�o�enia pi�ki
			if(ballXDirection == 1) x = x + 6; else x = x - 6;
			if(ballYDirection == 1) y = y - 3; else y = y + 3;
			
			//Kolizje
			if(x <= 0 || x >= 920) ballXDirection = Math.abs(ballXDirection - 1);
			if(y <= 0 || y >= 520) ballYDirection = Math.abs(ballYDirection - 1);
			
			if(LoadImages.getBall().getGlobalBounds().intersection(playerOne.getBackground().getGlobalBounds()) != null){
				ballYDirection = Math.abs(ballYDirection - 1);
				y = y - 3;
			}
			if(LoadImages.getBall().getGlobalBounds().intersection(playerTwo.getBackground().getGlobalBounds()) != null){
				ballYDirection = Math.abs(ballYDirection - 1);
				y = y - 3;
			}
			if(LoadImages.getBall().getGlobalBounds().intersection(LoadImages.getNet().getGlobalBounds()) != null){
				ballXDirection = Math.abs(ballXDirection - 1);
			}
			
			LoadImages.getBall().setPosition(x, y);
		}
	}
	
	/**
	 * Metoda pobiera z serwera pozycj� pi�ki i j� aktualizuje <br>
	 * Korzysta z niej gracz numer 2 aby mie� aktualn� pozycj�
	 */
	public static void GetBallPos(){
		try {
			Send.SendObject("getBallPos:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			int[] pos = (int[]) o;
			LoadImages.getBall().setPosition(pos[0], pos[1]);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda wysy�a na serwer pozycj� pi�ki <br>
	 * Korzysta z niej gracz numer 1 aby serwer m�g� przekaza� pozycj� graczowi numer 2
	 */
	public static void UpdateBallPos(){
		Send.SendObject("updateBallPos:"+Config.getMainAccount().getLogin()+":"+(int) LoadImages.getBall().getPosition().x+":"+(int) LoadImages.getBall().getPosition().y);
	}
	
	/**
	 * Metoda ��czy gracza z serwerem i tworzy now� gr� lub do��cza gracza do ju� istniej�cej gry
	 */
	public static void ConnectToGame(){
		Config.getMainAccount().setType("connect");
		Send.SendObject(Config.getMainAccount());
		try {
			Object o = Get.GetObject();
			Object o2 = Get.GetObject();
			
			if(o.equals("Po��czenie z gr� nieudane")){
				JOptionPane.showMessageDialog(null, "B��d, spr�buj jeszcze raz");
			} else {
				JOptionPane.showMessageDialog(null, o.toString());
				Config.getMainAccount().setInGame(true);
				if(o2.equals("1")) {
					player = playerOne;
					opponent = playerTwo;
				}
				if(o2.equals("2")) {
					player = playerTwo;
					opponent = playerOne;
				}
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda s�u�y do aktualizacji pozycji graczy. Wysy�a pozycj� gracza na serwer a tak�e
	 * pobiera pozycj� przeciwnika z serwera aby m�c j� zaktualizowa� na ekranie
	 */
	public static void UpdatePos(){
		try {
			Send.SendObject("updatePos:"+Config.getMainAccount().getLogin()+":"+(int)player.getPosition().x+":"+(int)player.getPosition().y);
			Send.SendObject("getOpponentPos:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			int[] pos = (int[]) o;
			opponent.setPosition(new Vector2f(pos[0], pos[1]));
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda pobiera z serwera punktacj� graczy i zapisuje j�
	 */
	public static void UpdatePoints(){
		try {
			Send.SendObject("getPoints:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			int[] points = (int[]) o;
			playerOne.setPoints(points[0]);
			playerTwo.setPoints(points[1]);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda pobiera z serwera informacj� o stanie gry
	 * @return true je�li gra trwa, false je�li gracz czeka na przeciwnika lub przeciwnik si� roz��czy�
	 */
	public static boolean isGameRunning(){
		try {
			Send.SendObject("isGameRunning:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			if(o.equals(0)) return false;
			else return true;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metoda wysy�a na serwer zapytanie czy gra si� sko�czy�a (kt�ry� z graczy zdoby� 9 punkt�w) <br>
	 * Je�eli gra si� zako�czy�a wy�wietlana jest stosowna informacja i gra jest wy��czana
	 */
	public static void CheckEnd(){
		try {
			Send.SendObject("checkEnd:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			if(o.equals(1)) {
				JOptionPane.showMessageDialog(null, "Wygra� gracz 1");
				Window.closeWindow();
			}
			if(o.equals(2)) {
				JOptionPane.showMessageDialog(null, "Wygra� gracz 2");
				Window.closeWindow();
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda s�u�y do realizowania wszystkich akcji zwi�zanych z obs�ug� gry poprzez wywo�anie odpowiednich metod, m.in.: <br>
	 * - ruch graczy <br>
	 * - sprawdzanie stanu gry <br>
	 * - po��czenie z serwerem <br>
	 * - rysowanie element�w gry <br>
	 */
	public static void DoGameScreen(){
		CheckEnd();
		if(!Config.getMainAccount().isInGame()) {
			ConnectToGame();
			LoadImages.getBall().setPosition(20, 300);
		} else {
			UpdatePos();
			UpdatePoints();
			if(player == playerOne) {
				MoveBall();
				UpdateBallPos();
			} else {
				GetBallPos();
			}
		}
		CheckKeys();
		DrawBackground();
		DrawPoints();
		DrawNet();
		DrawBall();
		DrawPlayers();
	}
}
