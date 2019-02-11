package main;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import database.AccountManager;
import game.Game;
import network.Account;
import network.Get;
import network.Send;

/**
 * Klasa s�u�y do obs�ugi klienta i jego zapyta� dla serwera
 * @author Kamil Piec
 */
public class Client implements Runnable{
	/** Gniazdo sieciowe serwera z klientem */
	private Socket socket = null;
	/** Czy nast�pi�o po��czenie serwera z klientem */
	private boolean isConnected = false;
	/** Numer klienta */
	private int clientNumber;
	/** Uchwyt w�tku do nas�uchu na po��czeniu */
	private Thread thread;
	
	@Override
	/**
	 * Metoda wykonywana w ramach w�tku. Dop�ki serwer jest po��czony z danym klientem sprawdzane jest czy 
	 * nadesz�y jakie� wiadomo�ci od klienta - je�li tak s� one obs�ugiwane przez serwer i wysy�ane s� do klienta 
	 * wiadomo�ci zwrotne.
	 */
	public void run() {
		while(isConnected()){
			Object o;
			try {
				o = Get.GetObject(getClientNumber());
				if(o != null) {
					if(o instanceof String){
						String[] split = ((String) o).split(":");
						if(o.equals("close")){
							setConnected(false);
							System.out.println("Client " + getClientNumber() + " disconnected");
						}
						if(split[0].equals("updatePos")){
							Game.updatePos(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
						}
						if(split[0].equals("getOpponentPos")){
							int[] ret = Game.getOpponentPos(split[1]);
							Send.SendObject(getClientNumber(), ret);
						}
						if(split[0].equals("getPoints")){
							int[] ret = Game.getPoints(split[1]);
							Send.SendObject(getClientNumber(), ret);
						}
						if(split[0].equals("scoreOpponent")){
							Game.scoreOpponent(split[1]);
						}
						if(split[0].equals("scorePlayer")){
							Game.scorePlayer(split[1]);
						}
						if(split[0].equals("getBallPos")){
							int[] ret = Game.getBallPos(split[1]);
							Send.SendObject(getClientNumber(), ret);
						}
						if(split[0].equals("updateBallPos")){
							Game.updateBallPos(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
						}
						if(split[0].equals("isGameRunning")){
							int ret = Game.isGameRunning(split[1]);
							Send.SendObject(getClientNumber(), ret);
						}
						if(split[0].equals("checkEnd")){
							int ret = Game.checkEnd(split[1]);
							Send.SendObject(getClientNumber(), ret);
						}
					}
					if (o instanceof Account){
						Account acc = (Account) o;
						
						if(acc.getType().equals("login")){
							Send.SendObject(getClientNumber(), AccountManager.loginAccount(acc.getLogin(), acc.getPassword()));
						}
						if(acc.getType().equals("register")){
							Send.SendObject(getClientNumber(), AccountManager.registerAccount(acc.getLogin(), acc.getPassword()));
						}
						if(acc.getType().equals("logout")){
							Game.exitFromGame(acc.getLogin());
							Send.SendObject(getClientNumber(), AccountManager.logoutAccount(acc.getLogin()));
						}
						if(acc.getType().equals("connect")){
							if(Game.isInGame(acc.getLogin())){
								Send.SendObject(getClientNumber(), "Gracz znajduje si� w grze, do��czono do niej");
								Send.SendObject(getClientNumber(), "0");
							} else {
								int freeGame = Game.isFreeGame();
								if(freeGame != 0){
									int joined = Game.joinToGame(acc.getLogin(), freeGame);
									if(joined == 0){
										Send.SendObject(getClientNumber(), "Po��czenie z gr� nieudane");
										Send.SendObject(getClientNumber(), "0");
									} else {
										Send.SendObject(getClientNumber(), "Do��czono do istniej�cej gry");
										Send.SendObject(getClientNumber(), "2");
									}
								} else {
									Game.createGame(acc.getLogin());
									Send.SendObject(getClientNumber(), "Stworzono now� gr�");
									Send.SendObject(getClientNumber(), "1");
								}
							}
						}
					}
				}
			} catch (SocketTimeoutException e) {
				//Timeout
			}
			
			Game.deleteEmptyGames();
		}
	}

	/**
	 * Metoda zamyka po��czenie serwera z klientem
	 */
	public void closeClient(){
		try {
			getSocket().close();
			setConnected(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda zwraca gniazdo sieciowe po��czenia serwera z klientem
	 * @return Gniazdo sieciowe
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Metoda pozwala zmieni� gniazdo sieciowe po��czenia serwera z klientem
	 * @param socket Nowe gniazdo sieciowe
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
		setConnected(true);
	}

	/**
	 * Metoda zwraca informacje czy nast�pi�o po��czenie z klientem
	 * @return true je�li nast�pi�o po��czenie
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * Metoda pozwala zmieni� stan po��czenia z klientem
	 * @param isConnected Nowy stan po��czenia z klientem
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	/**
	 * Metoda zwraca numer klienta
	 * @return Numer klienta
	 */
	public int getClientNumber() {
		return clientNumber;
	}

	/**
	 * Metoda ustawia numer klienta
	 * @param clientNumber Nowy numer klienta
	 */
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	/**
	 * Metoda zwraca uchwyt w�tku
	 * @return Uchwyt w�tku
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Metoda zmienia uchwyt w�tku
	 * @param thread Nowy uchwyt w�tku
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
