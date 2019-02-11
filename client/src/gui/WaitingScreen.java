package gui;

import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import main.Config;
import network.Account;
import network.Get;
import network.Send;

/**
 * Klasa s³u¿y do obs³ugi menu logowania i rejestracji gracza
 * @author Kamil Piec
 */
public class WaitingScreen {
	/** Pole tekstowe na nazwê u¿ytkownika */
	static Input userInput = new Input(
			"nazwa u¿ytkownika", 
			new Vector2f(500, 50), 
			new Vector2f(250, 100),
			new Color(240, 240, 240),
			Color.WHITE, 
			Color.BLACK,
			1);
	
	/** Pole tekstowe na has³o */
	static Input passwordInput = new Input(
			"has³o", 
			new Vector2f(500, 50), 
			new Vector2f(250, 160),
			new Color(240, 240, 240),
			Color.WHITE, 
			Color.BLACK,
			1);

	/** Przycisk logowania */
	static Button loginButton = new Button(
			"zaloguj", 
			new Vector2f(500, 50), 
			new Vector2f(250, 250), 
			new Color(240, 240, 240), 
			Color.WHITE, 
			Color.BLACK, 
			1);
	
	/** Przycisk rejestracji */
	static Button registerButton = new Button(
			"zarejestruj", 
			new Vector2f(500, 50), 
			new Vector2f(250, 310), 
			new Color(240, 240, 240), 
			Color.WHITE, 
			Color.BLACK, 
			1);
	
	/**
	 * Metoda rysuje t³o ekranu rejestracji i logowania
	 */
	public static void DrawBackground(){
		Window.getWindow().draw(LoadImages.getMenuBg());
	}
	
	/**
	 * Metoda rysuje na ekranie wszystkie przyciski i pola tekstowe
	 */
	public static void DrawUserInput(){
		userInput.draw();
		passwordInput.draw();
		loginButton.draw();
		registerButton.draw();
	}
	
	/**
	 * Metoda sprawdza czy któryœ z przycisków lub pól tekstowych zosta³ naciœniêty.<br>
	 * W przypadku naciœniêcia na przyciski rejestracji lub logowania wysy³ane s¹ odpowiednie polecenia na serwer
	 * i zak³adane jest konto lub nastepuje logowanie na ju¿ istniej¹ce
	 */
	public static void ChceckClick(){
		userInput.checkClick();
		passwordInput.checkClick();
		loginButton.checkClick();
		registerButton.checkClick();

		if(loginButton.isClicked()) {
			Account account = new Account(userInput.getText(), passwordInput.getText(), "login");
			Send.SendObject(account);
			
			try {
				System.out.println("waiting for response");
				Object o =  Get.GetObject();
				String[] ret = (String[]) o;
				JOptionPane.showMessageDialog(null, ret[0]);
				if(ret[1].equals("1")){
					Config.setMainAccount(new Account(userInput.getText(), passwordInput.getText(), "logout"));
					Config.getMainAccount().setOnline(true);
					Config.setActiveScreen(Screens.GAME);
				}
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
			}
		}
		if(registerButton.isClicked()) {
			Account account = new Account(userInput.getText(), passwordInput.getText(), "register");
			Send.SendObject(account);
			
			try {
				System.out.println("waiting for response");
				Object o =  Get.GetObject();
				JOptionPane.showMessageDialog(null, o.toString());
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda dopisuje znak do aktywnego pola tekstowego lub usuwa z niego znak
	 * @param chr Znak do dopisania
	 * @param unicode Kod ASCII znaku
	 */
	public static void WriteTextToInput(char chr, int unicode){
		String userInputText = userInput.getText();
		String passwordInputText = passwordInput.getText();	
		
		if(unicode == 8){
			if(userInputText.length() != 0 && userInputText != null)
				userInputText = userInputText.substring(0, userInputText.length() - 1);
			if(passwordInputText.length() != 0 && passwordInputText != null)
				passwordInputText = passwordInputText.substring(0, passwordInputText.length() - 1);
		} else {
			userInputText += chr;
			passwordInputText += chr;
		}
		
		if(userInput.isActive())
			userInput.setText(userInputText);
		if(passwordInput.isActive())
			passwordInput.setText(passwordInputText);
	}
	
	/**
	 * Metoda wykonuje wszystkie akcje zwi¹zane z dzia³aniem menu rejestracji i logowania
	 */
	public static void DoWaitingScreen(){
		ChceckClick();
		DrawBackground();
		DrawUserInput();
	}
}
