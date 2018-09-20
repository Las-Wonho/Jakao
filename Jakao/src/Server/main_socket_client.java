package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class main_socket_client {

	private Socket _socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public main_socket_client(String IP, int port) {
		try {
			_socket = new Socket(IP, port); 
			System.out.println(IP + "Connected");
			
			in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			out = new PrintWriter(_socket.getOutputStream());
			
			out.println("Listen");
			out.flush();
			
			System.out.println(in.readLine());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				_socket.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new main_socket_client("127.0.0.1", 8080);
	}
}
