package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

import main.Config;

/**
 * Metoda pozwala wysy�a� dane do klienta
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Send {
	/**
	 * Strumienie danych do wysy�ania danych do klient�w
	 */
	private static ObjectOutputStream out[] = new ObjectOutputStream[Config.getMaxClients()];
	
	/**
	 * Inicjalizacja strumieni danych strumieniami z gniazd sieciowych klient�w
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
	 * Wys�anie obiektu do danego klienta
	 * @param client Numer klienta
	 * @param obj Obiekt do wys�ania
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
