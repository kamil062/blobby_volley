package gui;
import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import main.Config;
import network.Account;
import network.Connection;
import network.Get;
import network.Send;

import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;

import font.LoadFont;
import game.GameScreen;

/**
 * Klasa do obs³ugi okna gry
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Window implements Runnable{
	/** Uchwyt okna gry */
	private static RenderWindow window;
	/** Czy naciœniêto strza³kê w lewo */
	public static boolean keyLeftPressed = false;
	/** Czy naciœniêto strza³kê w prawo */
	public static boolean keyRightPressed = false;
	/** Czy naciœniêto straza³kê w górê */
	public static boolean keyUpPressed = false;
	/** Czy zosta³ naciœniêty przycisk myszy */
	public static boolean mouseButtonReleased = false;
	
	/**
	 * Metoda tworz¹ca okno gry
	 */
	public static void CreateWindow(){
		window = new RenderWindow();
		window.create(new VideoMode(Config.getWindowSize().x, Config.getWindowSize().y), Config.getWindowTitle());

		window.setFramerateLimit(60);
	}
	
	/**
	 * Metoda zwracaj¹ca uchwyt okna gry
	 * @return Uchwyt okna gry
	 */
	public static RenderWindow getWindow(){
		return window;
	}
	
	/**
	 * Metoda wylogowuje u¿ytkownika, zamyka po³¹czenie z serwerem i zamyka okno gry
	 */
	public static void closeWindow(){
		if(Config.getMainAccount() != null){
			if(Config.getMainAccount().isOnline()){
				Account acc = new Account(Config.getMainAccount().getLogin(), Config.getMainAccount().getPassword(), "logout");
				Send.SendObject(acc);
				try {
					Object o =  Get.GetObject();

					System.out.println(o.toString());
					
					if(o.toString().equals("1"))
						JOptionPane.showMessageDialog(null, "Wylogowano");
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
				}
			}
		}
		Send.SendObject("close");
		Connection.closeConnection();
		window.close();
	}
	
	@Override
	/**
	 * Metoda wykonywana w w¹tku z g³ównym oknem gry<br>
	 * W metodzie nastêpuje inicjalizacja gry, po³¹czenia sieciowego z serwerem, tworzenie okna, za³adowanie danych 
	 * a tak¿e obs³uga ekranów gry i przycisków klawiatury i myszy
	 */
	public void run(){
		Connection.doConnection();
		Send.Init();
		Get.Init();
		
		CreateWindow();
		
		try {
			LoadImages.LoadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoadFont.Load();
		
		while(window.isOpen()) {
		    window.clear(Color.BLACK);

		    switch(Config.getActiveScreen()){
		    	case MAIN_MENU: 
		    		MenuScreen.DoMenuScreen(); 
		    		break;
		    	case GAME: 
		    		GameScreen.DoGameScreen(); 
		    		break;
		    	case WAITING_MENU:
		    		WaitingScreen.DoWaitingScreen(); 
		    		break;
		    	default:
		    		break;
		    }
		    
		    
		    window.display();

		    for(Event event : window.pollEvents()) {
				keyLeftPressed = false;
				keyRightPressed = false;
				keyUpPressed = false;
				mouseButtonReleased = false;
		    	switch(event.type){
		    		case CLOSED:
		    			closeWindow();
		    			break;
		    		case KEY_PRESSED:
		    			KeyEvent keyEvent = event.asKeyEvent();
		    			if(keyEvent.key == Key.ESCAPE){
		    				closeWindow();
		    			}
		    			if(keyEvent.key == Key.LEFT){
		    				keyLeftPressed = true;
		    			}
		    			if(keyEvent.key == Key.RIGHT){
		    				keyRightPressed = true;
		    			}
		    			if(keyEvent.key == Key.UP){
		    				keyUpPressed = true;
		    			}
		    			break;
		    		case TEXT_ENTERED:
		    			TextEvent textEvent = event.asTextEvent();
		    			WaitingScreen.WriteTextToInput(textEvent.character, textEvent.unicode);
		    			break;
		    		case MOUSE_BUTTON_RELEASED:
		    			mouseButtonReleased = true;
		    			break;
					default:
						break;
		    	}
		    }
		}
	}
}
