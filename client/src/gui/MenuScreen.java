package gui;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import main.Config;

/**
 * Klasa obs�uguj�ca ekran menu g��wnego
 * @author Sebastian Opoka
 */
public class MenuScreen {
	/** Pozycja przycisku "graj" na ekranie */
	private static Vector2f menuPlayButtonPosition = new Vector2f((Config.getWindowSize().x / 2) - (LoadImages.getMenuPlayButton().getSize().x / 2), 200);
	/** Pozycja przycisku "koniec" na ekranie */
	private static Vector2f menuExitButtonPosition = new Vector2f((Config.getWindowSize().x / 2) - (LoadImages.getMenuExitButton().getSize().x / 2), 300);
	/** Pozycja pod�wietlonego przycisku "graj" na ekranie */
	private static Vector2f menuPlayButtonHlPosition = new Vector2f((Config.getWindowSize().x / 2) - (LoadImages.getMenuPlayButtonHl().getSize().x / 2), 200);
	/** Pozycja pod�wietlonego przycisku "koniec" na ekranie */
	private static Vector2f menuExitButtonHlPosition = new Vector2f((Config.getWindowSize().x / 2) - (LoadImages.getMenuExitButtonHl().getSize().x / 2), 300);

	/** Czy mysz znajduje si� nad przyciskiem "graj" */
	private static boolean menuPlayButtonHover = false;
	/** Czy mysz znajduje si� nad przyciskiem "koniec" */
	private static boolean menuExitButtonHover = false;
	
	/** Czy naci�ni�to przycisk "graj" */
	private static boolean menuPlayButtonClicked = false;
	/** Czy naci�ni�to przycisk "koniec" */
	private static boolean menuExitButtonClicked = false;
	
	/**
	 * Metoda rysuje t�o menu g��wnego
	 */
	public static void DrawBackground(){
		Window.getWindow().draw(LoadImages.getMenuBg());
	}
	
	/**
	 * Metoda rysuje przyciski menu g��wnego, r�ne w zale�no�ci od tego czy mysz znajduje si� nad nimi czy nie
	 */
	public static void DrawButtons(){
		if(!menuPlayButtonHover){
			LoadImages.getMenuPlayButton().setPosition(menuPlayButtonPosition);
			Window.getWindow().draw(LoadImages.getMenuPlayButton());
		} else {
			LoadImages.getMenuPlayButtonHl().setPosition(menuPlayButtonHlPosition);
			Window.getWindow().draw(LoadImages.getMenuPlayButtonHl());
		}
		
		if(!menuExitButtonHover){
			LoadImages.getMenuExitButton().setPosition(menuExitButtonPosition);
			Window.getWindow().draw(LoadImages.getMenuExitButton());
		} else {
			LoadImages.getMenuExitButtonHl().setPosition(menuExitButtonHlPosition);
			Window.getWindow().draw(LoadImages.getMenuExitButtonHl());
		}
	}
	
	/**
	 * Metoda sprawdza czy mysz znajduje si� nad kt�rym� z przycisk�w menu g��wnego i czy kt�ry� z przycisk�w
	 * zosta� naci�ni�ty
	 */
	public static void CheckClickAndHover(){
		FloatRect playButtonBoundingBox = LoadImages.getMenuPlayButton().getGlobalBounds();
		FloatRect exitButtonBoundingBox = LoadImages.getMenuExitButton().getGlobalBounds();
		menuPlayButtonClicked = false;
		menuExitButtonClicked = false;

		if(playButtonBoundingBox.contains(Mouse.getPosition(Window.getWindow()).x, Mouse.getPosition(Window.getWindow()).y)){
			menuPlayButtonHover = true;
			
			if(Mouse.isButtonPressed(Mouse.Button.LEFT))
				menuPlayButtonClicked = true;
			else
				menuPlayButtonClicked = false;
		} else {
			menuPlayButtonHover = false;
		}
		
		if(exitButtonBoundingBox.contains(Mouse.getPosition(Window.getWindow()).x, Mouse.getPosition(Window.getWindow()).y)){
			menuExitButtonHover = true;

			if(Mouse.isButtonPressed(Mouse.Button.LEFT))
				menuExitButtonClicked = true;
			else
				menuExitButtonClicked = false;
		} else {
			menuExitButtonHover = false;
		}
	}
	
	/**
	 * Metoda wykonuje wszystkie funkcje potrzebne do dzia�ania menu g��wnego 
	 */
	public static void DoMenuScreen(){
		CheckClickAndHover();
		DrawBackground();
		DrawButtons();

		if(menuExitButtonClicked){
			Window.closeWindow();
		}
		if(menuPlayButtonClicked){
			Config.setActiveScreen(Screens.WAITING_MENU);
		}
	}
}
