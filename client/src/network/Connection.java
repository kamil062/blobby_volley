package network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Config;

/**
 * Klasa pozwala po��czy� si� z serwerem
 * @author Sebastian Opoka
 */
public class Connection {
	/**
	 * Inicjalizacja po��czenia z serwerem
	 */
	public static void doConnection(){
		try {
			Config.setSocket(new Socket("localhost", Config.getPort()));
			System.out.println("Connected to server");
		} catch (ConnectException e){
			System.out.println("Connection failed");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Error");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Zamkni�cie po��czenia z serwerem
	 */
	public static void closeConnection(){
		try {
			Config.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
