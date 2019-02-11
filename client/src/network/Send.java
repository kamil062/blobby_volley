package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

import main.Config;

/**
 * Metoda pozwala wys³aæ dane na serwer
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class Send {
	/** Strumieñ danych do wysy³ania na serwer */
	private static ObjectOutputStream out;
	
	/**
	 * Inicjalizacja klasy (inicjalizacja strumienia do wysy³ania strumieniem z gniazda sieciowego)
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
	 * Wysy³ka obiektu na serwer
	 * @param obj Obiekt do wys³ania
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
	 * Metoda zwraca strumieñ danych do wysy³ania
	 * @return Strumieñ danych do wysy³ania
	 */
	public static ObjectOutputStream getOut() { return out; }

	/**
	 * Metoda pozwala zmienic strumieñ danych do wysy³ania
	 * @param out Nowy strumieñ danych do wysy³ania
	 */
	public static void setOut(ObjectOutputStream out) { Send.out = out; }
}
