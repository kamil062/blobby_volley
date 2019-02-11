package main;

import database.DatabaseConnect;
import network.AcceptClients;

/**
 * Klasa uruchamia serwer i w¹tki potrzebne do jego dzia³ania
 * @author Kamil Piec
 */
public class ServerStart {
	/**
	 * G³ówna metoda programu. Uruchamia w¹tki po³¹czenia z klientami i z baz¹ danych, czeka na ich zakoñczenie a nastêpnie wy³¹cza serwer.
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
