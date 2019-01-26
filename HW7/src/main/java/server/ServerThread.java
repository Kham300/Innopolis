package main.java.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The type Server thread.
 */
public class ServerThread extends Thread{
	
	private Server server;
	private Socket socket;
	private final int ID;
	private DataInputStream streamInput = null;
	private DataOutputStream streamOutput = null;
	private boolean isDone = false;

	/**
	 * Instantiates a new Server thread.
	 *
	 * @param server the server
	 * @param socket the socket
	 */
	public ServerThread(Server server, Socket socket){
		this.server = server;
		this.socket = socket;
		this.ID = socket.getPort();
	}
	
	public void run(){
		while(!isDone){
			try {
				server.handle(ID, streamInput.readUTF());
			} catch (IOException ioe) {
				//Expected EOF error
			} 
		}		
	}

	/**
	 * Send.
	 *
	 * @param message the message
	 */
	public void send(String message){
		try{
			streamOutput.writeUTF(message);
			streamOutput.flush();
			System.out.println(message + " sent to client: " + ID);
		}catch(IOException e){
			e.printStackTrace();
			server.removeThread(ID);
		}
	}

	/**
	 * Open.
	 *
	 * @throws IOException the io exception
	 */
	public void open() throws IOException{
		streamInput = new DataInputStream(socket.getInputStream());
		streamOutput = new DataOutputStream(socket.getOutputStream());
	}

	/**
	 * Close.
	 *
	 * @throws IOException the io exception
	 */
	public void close() throws IOException{
		isDone = true;
		socket.close();
		streamInput.close();
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
//GETTERS
	public int getID(){
		return ID;
	}
	
}
