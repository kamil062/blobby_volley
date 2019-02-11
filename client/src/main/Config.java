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
	/** Szeroko�� okna gry */
	private static int windowWidth = 1000;
	/** Wysoko�� okna gry */
	private static int windowHeight = 600;
	/** Wektor z rozmiarem oka gry */
	private static Vector2i windowSize = new Vector2i(windowWidth, windowHeight);
	
	/** CZy gra dzia�a */
	private static boolean isGameRunning = true;
	
	/** Pole z aktualnie wy�wietlanym ekranem */
	private static Screens activeScreen = Screens.MAIN_MENU;
	
	/** Tytu� okna gry */
	private static String windowTitle = "BlobbyVolley";
	
	/** Port, na kt�rym klient b�dzie ��czy� si� z serwerem */
	private static int port = 8001;
	
	/** Gniazdo do po��czenia z serwerem */
	private static Socket socket;
	
	/** Pole z kontem gracza */
	private static Account mainAccount = null;
	
	/**
	 * Metoda zwracaj�ca wymiary okna gry
	 * @return Wektor z wymiarami okna gry
	 */
	public static Vector2i getWindowSize() {
		return windowSize;
	}

	/**
	 * Metoda sprawdzaj�ca czy gra dzia�a
	 * @return Stan gry
	 */
	public static boolean isGameRunning() {
		return isGameRunning;
	}

	/**
	 * Metoda pozwalaj�ca zmienic stan dzia�ania gry
	 * @param isGameRunning Nowy stan dzia�ania gry
	 */
	public static void setGameRunning(boolean isGameRunning) {
		Config.isGameRunning = isGameRunning;
	}

	/**
	 * Metoda zwracaj�ca tytu� okna gry
	 * @return Tytu� okna gry
	 */
	public static String getWindowTitle() {
		return windowTitle;
	}

	/**
	 * Metoda pozwalaj�ca zmieni� tytu� gry
	 * @param windowTitle Nowy tytu� gry
	 */
	public static void setWindowTitle(String windowTitle) {
		Config.windowTitle = windowTitle;
	}

	/**
	 * Metoda zwraca gniazdo po��czeniowe z serwerem
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
	 * Metoda zwraca port, na kt�rym nast�puje po��czenie
	 * @return Port, na kt�rym nastepuje po��czenie
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * Metoda ustawia port, na kt�rym nast�puje po��czenie
	 * @param port Port, na kt�rym nast�puje po��czenie
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
	 * Metoda pozwala zmieni� aktualnie aktywny ekran
	 * @param activeScreen Nowy aktywny ekran
	 */
	public static void setActiveScreen(Screens activeScreen) {
		Config.activeScreen = activeScreen;
	}

	/**
	 * Metoda zwraca g��wne konto gracza
	 * @return Konto gracza
	 */
	public static Account getMainAccount() {
		return mainAccount;
	}

	/**
	 * Metoda pozwala ustawi� g��wne konto gracza
	 * @param mainAccount G��wne konto gracza
	 */
	public static void setMainAccount(Account mainAccount) {
		Config.mainAccount = mainAccount;
	}
}
