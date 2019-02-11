package main;

/**
 * Klasa obs�uguj�ca ca�� gr�. 
 * Uruchamiany jest tu w�tek, w ramach kt�rego dzia�a gra
 * @author Kamil Piec
 */
public class GameStart {
	/**
	 * Funkcja g��wna programu. Uruchamia w�tek w ramach kt�rego zostanie otwarte okno gry.
	 * @param args
	 */
	public static void main(String[] args){
		Thread windowThread = new Thread(new gui.Window());

		windowThread.start();
	}
}
