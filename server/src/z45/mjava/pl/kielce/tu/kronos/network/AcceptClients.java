package network;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;

import main.Client;
import main.Config;

/**
 * Klasa akceptuje po��czenia serwera z klientami
 * @author Kamil Piec
 * @author Sebastian Opoka
 */
public class AcceptClients implements Runnable {
	/**
	 * Metoda tworzy gniazdo sieciowe serwera.
	 */
	public void createServer(){
		try {
			Config.setServerSocket(new ServerSocket(Config.getPort()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server created");
	}
	
	/**
	 * Metoda wy��cza serwer
	 */
	public static void closeServer(){
		try {
			Config.getServerSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda zamyka po��czenia z klientami
	 */
	public static void closeClients(){
		for(int i = 0; i < Config.getMaxClients(); i++){
			Config.clients[i].closeClient();
		}
	}
	
	@Override
	/**
	 * Metoda uruchamia serwer, ��czy si� z klientami a nast�pnie tworzy w�tki przyjmuj�ce polecenia od klient�w
	 */
	public void run(){
		createServer();
		
		for(int i = 0; i < Config.getMaxClients(); i++){
			try {
				Config.clients[i] = new Client();
				Config.clients[i].setSocket(Config.getServerSocket().accept());
				Config.clients[i].setClientNumber(i);
				Config.clients[i].setThread(new Thread(Config.clients[i]));
				Config.clients[i].getThread().start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Client "+i+" connected");
		}
		
		try {
			Get.Init();
			Send.Init();
		} catch (EOFException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i < Config.getMaxClients(); i++){
			try {
				Config.clients[i].getThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
