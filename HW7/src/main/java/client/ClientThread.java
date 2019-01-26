package main.java.client;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The type Client thread.
 */
public class ClientThread extends Thread{
	
	private Client client;
	private Socket socket;
	private DataInputStream inputStream = null;
	private volatile boolean isDone = false;

	/**
	 * Instantiates a new Client thread.
	 *
	 * @param client the client
	 * @param socket the socket
	 */
	public ClientThread(Client client, Socket socket){
		this.client = client;
		this.socket = socket;
		open();
		start();
	}

	/**
	 * Open.
	 */
	public void open(){
		try{
			inputStream = new DataInputStream(socket.getInputStream());
		}catch(IOException ioe){
			System.out.println("Error getting input stream: " + ioe);
			client.stop();
		}
	}

	/**
	 * Close.
	 */
	public void close(){
		try{
			if(inputStream != null)
				inputStream.close();
		}catch(IOException ioe){
			System.out.println("Error closing input stream: " + ioe);
		}
		
		isDone = true;
	}
	
	public void run(){
		while(!isDone){
			try{
				client.handle(inputStream.readUTF());
			}catch(IOException ioe){
				System.out.println("Listening error" + ioe.getMessage());
				client.stop();
			
			}
		}
	}
}
