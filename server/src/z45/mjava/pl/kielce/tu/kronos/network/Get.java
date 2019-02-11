package network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;

import main.Config;

/**
 * Klasa pozwala pobraæ dane od klienta
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Get {
	/** Strumienie danych do odbierania */
	private static ObjectInputStream in[] = new ObjectInputStream[Config.getMaxClients()];

	/**
	 * Metoda inicjalizuje strumienie danych do odbierania strumieniami z gniazd sieciowych klientów
	 * @throws EOFException Wyj¹tek w przypadku niepowodzenia
	 */
	public static void Init() throws EOFException{
		for(int i = 0; i < Config.getMaxClients(); i++){
			try {
				in[i] = new ObjectInputStream(Config.clients[i].getSocket().getInputStream());
			} catch (SocketTimeoutException e){
				//Socket timeout
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * Metoda pobiera obiekt od zadanego klienta
	 * @param client Numer klienta
	 * @return Odebrany obiekt
	 * @throws SocketTimeoutException Wyj¹tek w przypadku niepowodzenia
	 */
	public static Object GetObject(int client) throws SocketTimeoutException{
		Object data = null;
		try {
			data = in[client].readObject();
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
