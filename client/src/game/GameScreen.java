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
 * Klasa s³u¿y do obs³ugi gry.
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class GameScreen {
	/** Pole zawiera pierwsz¹ postaæ */
	public static Player playerOne = new Player(10, 400, PlayerType.PLAYER1);
	/** Pole zawiera drug¹ postaæ */
	public static Player playerTwo = new Player(850, 400, PlayerType.PLAYER2);

	/** Pole zawiera referencjê do postaci, któr¹ steruje gracz */
	private static Player player;
	/** Pole zawieta referencjê do postaci, któr¹ steruje przeciwnik */
	private static Player opponent;

	/** Pole zawiera kierunek poziomy pi³ki;
	 * 1 - ruch w prawo
	 * 0 - ruch w lewo
	 */
	private static int ballXDirection = 1;
	/** Pole zawiera kierunek pionowy pi³ki
	 * 1 - ruch w górê
	 * 0 - ruch w dó³
	 */
	private static int ballYDirection = 1;
	
	/**
	 * Metoda rysuje na ekranie t³o gry
	 */
	public static void DrawBackground(){
		Window.getWindow().draw(LoadImages.getGameBg());
	}
	
	/**
	 * Metoda rysuje na ekranie siatkê
	 */
	public static void DrawNet(){
		LoadImages.getNet().setPosition(500, 250);
		Window.getWindow().draw(LoadImages.getNet());
	}
	
	/**
	 * Metoda rysuje na ekranie pi³kê
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
	 * Metoda rysuje na ekranie punktacjê
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
	 * Metoda sprawdza stan klawiszy odpowiadaj¹cych za poruszanie siê graczem <br>
	 * W przypadku wykrycia naciœniêcia przycisku realizuje odpowiedni¹ akcjê (ruch/skok)
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
	 * Metoda odpowiada za akcje zwi¹zane z ruchem pi³ki, m.in. <br>
	 * - poruszanie pi³k¹ po ekranie <br>
	 * - wykrywanie kolizji <br>
	 * - zaliczanie punktów graczom <br>
	 */
	public static void MoveBall(){
		//Pocz¹tkowa pozycja pi³ki
		float x = LoadImages.getBall().getPosition().x;
		float y = LoadImages.getBall().getPosition().y;
			
		//Ruch nastêpuje, dopóki trwa gra
		if(isGameRunning()){
			//Sprawdzanie czy pi³ka upad³a na pod³ogê i zaliczenie punktu odpowiedniemu graczowi
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
			
			//Modyfikacja po³o¿enia pi³ki
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
	 * Metoda pobiera z serwera pozycjê pi³ki i j¹ aktualizuje <br>
	 * Korzysta z niej gracz numer 2 aby mieæ aktualn¹ pozycjê
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
	 * Metoda wysy³a na serwer pozycjê pi³ki <br>
	 * Korzysta z niej gracz numer 1 aby serwer móg³ przekazaæ pozycjê graczowi numer 2
	 */
	public static void UpdateBallPos(){
		Send.SendObject("updateBallPos:"+Config.getMainAccount().getLogin()+":"+(int) LoadImages.getBall().getPosition().x+":"+(int) LoadImages.getBall().getPosition().y);
	}
	
	/**
	 * Metoda ³¹czy gracza z serwerem i tworzy now¹ grê lub do³¹cza gracza do ju¿ istniej¹cej gry
	 */
	public static void ConnectToGame(){
		Config.getMainAccount().setType("connect");
		Send.SendObject(Config.getMainAccount());
		try {
			Object o = Get.GetObject();
			Object o2 = Get.GetObject();
			
			if(o.equals("Po³¹czenie z gr¹ nieudane")){
				JOptionPane.showMessageDialog(null, "B³¹d, spróbuj jeszcze raz");
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
	 * Metoda s³u¿y do aktualizacji pozycji graczy. Wysy³a pozycjê gracza na serwer a tak¿e
	 * pobiera pozycjê przeciwnika z serwera aby móc j¹ zaktualizowaæ na ekranie
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
	 * Metoda pobiera z serwera punktacjê graczy i zapisuje j¹
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
	 * Metoda pobiera z serwera informacjê o stanie gry
	 * @return true jeœli gra trwa, false jeœli gracz czeka na przeciwnika lub przeciwnik siê roz³¹czy³
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
	 * Metoda wysy³a na serwer zapytanie czy gra siê skoñczy³a (któryœ z graczy zdoby³ 9 punktów) <br>
	 * Je¿eli gra siê zakoñczy³a wyœwietlana jest stosowna informacja i gra jest wy³¹czana
	 */
	public static void CheckEnd(){
		try {
			Send.SendObject("checkEnd:"+Config.getMainAccount().getLogin());
			Object o = Get.GetObject();
			if(o.equals(1)) {
				JOptionPane.showMessageDialog(null, "Wygra³ gracz 1");
				Window.closeWindow();
			}
			if(o.equals(2)) {
				JOptionPane.showMessageDialog(null, "Wygra³ gracz 2");
				Window.closeWindow();
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda s³u¿y do realizowania wszystkich akcji zwi¹zanych z obs³ug¹ gry poprzez wywo³anie odpowiednich metod, m.in.: <br>
	 * - ruch graczy <br>
	 * - sprawdzanie stanu gry <br>
	 * - po³¹czenie z serwerem <br>
	 * - rysowanie elementów gry <br>
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
