package main;

import database.DatabaseConnect;
import network.AcceptClients;

/**
 * Klasa uruchamia serwer i w�tki potrzebne do jego dzia�ania
 * @author Kamil Piec
 */
public class ServerStart {
	/**
	 * G��wna metoda programu. Uruchamia w�tki po��czenia z klientami i z baz� danych, czeka na ich zako�czenie a nast�pnie wy��cza serwer.
	 * @param args
	 */
	public static void main(String[] args){
		Thread acceptClientsThread = new Thread(new network.AcceptClients());
		Thread databaseThread = new Thread(new database.DatabaseConnect());

		acceptClientsThread.start();
		databaseThread.start();
		
		try {
			acceptClientsThread.join();
			databaseThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AcceptClients.closeClients();
		AcceptClients.closeServer();
		DatabaseConnect.endConnection();
	}
}
