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
 * Klasa s³u¿y do obs³ugi klienta i jego zapytañ dla serwera
 * @author Kamil Piec
 */
public class Client implements Runnable{
	/** Gniazdo sieciowe serwera z klientem */
	private Socket socket = null;
	/** Czy nast¹pi³o po³¹czenie serwera z klientem */
	private boolean isConnected = false;
	/** Numer klienta */
	private int clientNumber;
	/** Uchwyt w¹tku do nas³uchu na po³¹czeniu */
	private Thread thread;
	
	@Override
	/**
	 * Metoda wykonywana w ramach w¹tku. Dopóki serwer jest po³¹czony z danym klientem sprawdzane jest czy 
	 * nadesz³y jakieœ wiadomoœci od klienta - jeœli tak s¹ one obs³ugiwane przez serwer i wysy³ane s¹ do klienta 
	 * wiadomoœci zwrotne.
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
								Send.SendObject(getClientNumber(), "Gracz znajduje siê w grze, do³¹czono do niej");
								Send.SendObject(getClientNumber(), "0");
							} else {
								int freeGame = Game.isFreeGame();
								if(freeGame != 0){
									int joined = Game.joinToGame(acc.getLogin(), freeGame);
									if(joined == 0){
										Send.SendObject(getClientNumber(), "Po³¹czenie z gr¹ nieudane");
										Send.SendObject(getClientNumber(), "0");
									} else {
										Send.SendObject(getClientNumber(), "Do³¹czono do istniej¹cej gry");
										Send.SendObject(getClientNumber(), "2");
									}
								} else {
									Game.createGame(acc.getLogin());
									Send.SendObject(getClientNumber(), "Stworzono now¹ grê");
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
	 * Metoda zamyka po³¹czenie serwera z klientem
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
	 * Metoda zwraca gniazdo sieciowe po³¹czenia serwera z klientem
	 * @return Gniazdo sieciowe
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Metoda pozwala zmieniæ gniazdo sieciowe po³¹czenia serwera z klientem
	 * @param socket Nowe gniazdo sieciowe
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
		setConnected(true);
	}

	/**
	 * Metoda zwraca informacje czy nast¹pi³o po³¹czenie z klientem
	 * @return true jeœli nast¹pi³o po³¹czenie
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * Metoda pozwala zmieniæ stan po³¹czenia z klientem
	 * @param isConnected Nowy stan po³¹czenia z klientem
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
	 * Metoda zwraca uchwyt w¹tku
	 * @return Uchwyt w¹tku
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Metoda zmienia uchwyt w¹tku
	 * @param thread Nowy uchwyt w¹tku
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
