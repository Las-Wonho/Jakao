package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class main_socket_server {

	private ServerSocket s_socket;
	private Socket _socket;
	
	private BufferedReader in;
	private PrintWriter out;
	
	public main_socket_server() {
		try {
			s_socket = new ServerSocket(8080);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch(Exception e) {
				
			}
			try {
				
			} catch(Exception e) {
				
			}
		}
	}
	public static void main(String[] args) {
		
	}

}
