package main;
import java.net.Socket;

import org.jsfml.system.*;

import gui.Screens;
import network.Account;

/**
 * Klasa do obslugi konfiguracji gry
 * 
 * @author Kamil Piec
 */
public class Config {
	/** Szerokoœæ okna gry */
	private static int windowWidth = 1000;
	/** Wysokoœæ okna gry */
	private static int windowHeight = 600;
	/** Wektor z rozmiarem oka gry */
	private static Vector2i windowSize = new Vector2i(windowWidth, windowHeight);
	
	/** CZy gra dzia³a */
	private static boolean isGameRunning = true;
	
	/** Pole z aktualnie wyœwietlanym ekranem */
	private static Screens activeScreen = Screens.MAIN_MENU;
	
	/** Tytu³ okna gry */
	private static String windowTitle = "BlobbyVolley";
	
	/** Port, na którym klient bêdzie ³¹czy³ siê z serwerem */
	private static int port = 8001;
	
	/** Gniazdo do po³¹czenia z serwerem */
	private static Socket socket;
	
	/** Pole z kontem gracza */
	private static Account mainAccount = null;
	
	/**
	 * Metoda zwracaj¹ca wymiary okna gry
	 * @return Wektor z wymiarami okna gry
	 */
	public static Vector2i getWindowSize() {
		return windowSize;
	}

	/**
	 * Metoda sprawdzaj¹ca czy gra dzia³a
	 * @return Stan gry
	 */
	public static boolean isGameRunning() {
		return isGameRunning;
	}

	/**
	 * Metoda pozwalaj¹ca zmienic stan dzia³ania gry
	 * @param isGameRunning Nowy stan dzia³ania gry
	 */
	public static void setGameRunning(boolean isGameRunning) {
		Config.isGameRunning = isGameRunning;
	}

	/**
	 * Metoda zwracaj¹ca tytu³ okna gry
	 * @return Tytu³ okna gry
	 */
	public static String getWindowTitle() {
		return windowTitle;
	}

	/**
	 * Metoda pozwalaj¹ca zmieniæ tytu³ gry
	 * @param windowTitle Nowy tytu³ gry
	 */
	public static void setWindowTitle(String windowTitle) {
		Config.windowTitle = windowTitle;
	}

	/**
	 * Metoda zwraca gniazdo po³¹czeniowe z serwerem
	 * @return Gniazdo sieciowe
	 */
	public static Socket getSocket() {
		return socket;
	}

	/** 
	 * Metoda ustawia gniazdo sieciowe
	 * @param socket Nowe gniazdo sieciowe
	 */
	public static void setSocket(Socket socket) {
		Config.socket = socket;
	}

	/** 
	 * Metoda zwraca port, na którym nastêpuje po³¹czenie
	 * @return Port, na którym nastepuje po³¹czenie
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * Metoda ustawia port, na którym nastêpuje po³¹czenie
	 * @param port Port, na którym nastêpuje po³¹czenie
	 */
	public static void setPort(int port) {
		Config.port = port;
	}

	/**
	 * Metoda zwraca aktualnie aktywny ekran
	 * @return Aktualnie aktywny ekran
	 */
	public static Screens getActiveScreen() {
		return activeScreen;
	}

	/**
	 * Metoda pozwala zmieniæ aktualnie aktywny ekran
	 * @param activeScreen Nowy aktywny ekran
	 */
	public static void setActiveScreen(Screens activeScreen) {
		Config.activeScreen = activeScreen;
	}

	/**
	 * Metoda zwraca g³ówne konto gracza
	 * @return Konto gracza
	 */
	public static Account getMainAccount() {
		return mainAccount;
	}

	/**
	 * Metoda pozwala ustawiæ g³ówne konto gracza
	 * @param mainAccount G³ówne konto gracza
	 */
	public static void setMainAccount(Account mainAccount) {
		Config.mainAccount = mainAccount;
	}
}
