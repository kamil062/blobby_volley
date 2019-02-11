package main;

/**
 * Klasa obs³uguj¹ca ca³¹ grê. 
 * Uruchamiany jest tu w¹tek, w ramach którego dzia³a gra
 * @author Kamil Piec
 */
public class GameStart {
	/**
	 * Funkcja g³ówna programu. Uruchamia w¹tek w ramach którego zostanie otwarte okno gry.
	 * @param args
	 */
	public static void main(String[] args){
		Thread windowThread = new Thread(new gui.Window());

		windowThread.start();
	}
}
