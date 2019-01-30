package main.java.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Server.
 */
public class Server implements Runnable{
	
	private ServerSocket server = null;
	private Thread thread = null;
	private ArrayList<ServerThread> threads = null;

	/**
	 * Instantiates a new Server.
	 *
	 * @param portNumber the port number
	 */
	public Server(int portNumber){
	
		try{
			System.out.println("----------------- SERVER -----------------");
			
			System.out.println("Creating server on port " + portNumber + ", please wait...");
			server = new ServerSocket(portNumber);
			server.setReuseAddress(true);
			System.out.println("Server created: " + server);
			
			System.out.println("------------------------------------------\n");
			
			threads = new ArrayList<>();
			
			start();
			
		}catch(IOException e){
			System.out.println(e);
		}
	}

	/**
	 * Start.
	 */
	public void start(){
		if(thread == null)
			thread = new Thread(this); thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop(){
		if(thread != null)
			thread.interrupt(); thread = null;
	}

	@Override
	public void run() {
		while(thread != null){
			try{
				System.out.println("----------------- CLIENT -----------------");
				System.out.println("Accepting new client...");
				addThread(server.accept());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Add thread.
	 *
	 * @param socket the socket
	 */
	public void addThread(Socket socket){
		System.out.println("Connected to client " + socket + "!");
		System.out.println("------------------------------------------\n");
		
		threads.add(new ServerThread(this, socket));
		startThread();
	}

	/**
	 * Start thread.
	 */
	public void startThread(){
		try{
			int newThread = threads.size() - 1;
			threads.get(newThread).open();
			threads.get(newThread).start();
		}catch(IOException ioe){
			System.out.println("Could not start thread: " + ioe);
		}
	}

	/**
	 * Find thread int.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int findThread(int id){
		
		int pos = -1;
		
		for(ServerThread st : threads){
			if(st.getID() == id){
				pos = threads.indexOf(st);
			}
		}
		return pos;
	}

	/**
	 * Remove thread.
	 *
	 * @param id the id
	 */
	public void removeThread(int id){
		
		int removePos = findThread(id);
		
		if(removePos != -1){
			ServerThread removeThread = threads.get(removePos);
			threads.remove(removePos);
			
			try{
				System.out.println("Removing client: " + id);
				removeThread.close();
			}catch(IOException ioe){
				System.out.println("Could not close thread: " + ioe);
			}
		}		
	}


	/**
	 * Handle.
	 *
	 * @param id    the id
	 * @param input the input
	 */
	public synchronized void handle(int id, String input){
		for(ServerThread c : threads)
			if(c.getID() != id)
				c.send(id + ": " + input);
		
		if(input.equalsIgnoreCase("bye")){
			threads.get((findThread(id))).send(".bye");
			removeThread(id);
		}			 
	}

	/**
	 * Main.
	 *
	 * @param args the args
	 */
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the port on which the server will run:");
		int port = input.nextInt();
		
		try {
			//PRINTS OUT HOST IP FOR OTHER CHATTERS
			System.out.println("Your IP Address is: " + InetAddress.getLocalHost() + "\n");
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
		
		Server server = new Server(port);
		input.close();
	}

	
}

