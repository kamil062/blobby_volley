package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

import main.Config;

/**
 * Metoda pozwala wysy³aæ dane do klienta
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Send {
	/**
	 * Strumienie danych do wysy³ania danych do klientów
	 */
	private static ObjectOutputStream out[] = new ObjectOutputStream[Config.getMaxClients()];
	
	/**
	 * Inicjalizacja strumieni danych strumieniami z gniazd sieciowych klientów
	 */
	public static void Init(){
		for(int i = 0; i < Config.getMaxClients(); i++){
			try {
				out[i] = new ObjectOutputStream(Config.clients[i].getSocket().getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				//Brak danych
			}
		}
	}
		
	/**
	 * Wys³anie obiektu do danego klienta
	 * @param client Numer klienta
	 * @param obj Obiekt do wys³ania
	 */
	public static void SendObject(int client, Object obj){
		try{
			if(obj != null) {
				out[client].writeObject(obj);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
