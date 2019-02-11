package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

import main.Config;

/**
 * Metoda pozwala wys�a� dane na serwer
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Send {
	/** Strumie� danych do wysy�ania na serwer */
	private static ObjectOutputStream out;
	
	/**
	 * Inicjalizacja klasy (inicjalizacja strumienia do wysy�ania strumieniem z gniazda sieciowego)
	 */
	public static void Init(){
		try {
			setOut(new ObjectOutputStream(Config.getSocket().getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			//Brak danych
		}
	}
		
	/**
	 * Wysy�ka obiektu na serwer
	 * @param obj Obiekt do wys�ania
	 */
	public static void SendObject(Object obj){
		try{
			if(obj != null) {
				getOut().writeObject(obj);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Metoda zwraca strumie� danych do wysy�ania
	 * @return Strumie� danych do wysy�ania
	 */
	public static ObjectOutputStream getOut() { return out; }

	/**
	 * Metoda pozwala zmienic strumie� danych do wysy�ania
	 * @param out Nowy strumie� danych do wysy�ania
	 */
	public static void setOut(ObjectOutputStream out) { Send.out = out; }
}
