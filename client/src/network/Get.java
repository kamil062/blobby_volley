package network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;

import main.Config;

/**
 * Klasa pozwala pobraæ dane z serwera
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Get {
	/** Strumieñ danych do odbierania */
	private static ObjectInputStream in;

	/**
	 * Inicjalizacja klasy (inicjalizacja strumienia danych strumieniem z gniazda sieciowego)
	 */
	public static void Init(){
			try {
				in = new ObjectInputStream(Config.getSocket().getInputStream());
			} catch (SocketTimeoutException e){
				//Socket timeout
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	/**
	 * Metoda pozwala odebraæ dowolny obiekt z serwera
	 * @return Obiekt z odebranymi danymi
	 * @throws SocketTimeoutException Wyj¹tek w przypadku niepowodzenia
	 */
	public static Object GetObject() throws SocketTimeoutException{
		Object data = null;
		try {
			data = in.readObject();
			return data;
		} catch (EOFException e){
			//Nie ma co czytaæ
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			//Brak danych
		} 
		return data;
	}
}
