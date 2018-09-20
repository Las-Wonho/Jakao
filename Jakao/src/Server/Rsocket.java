package Server;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Rsocket {
public Socket socket;
private BufferedReader br;
private OutputStream os;

public Rsocket(Socket s){
	socket = s;
	try {
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		os = socket.getOutputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void send(String str) {
	try {
		os.write(str.getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Send error");
	}
}

public String receive() {
	try {
		return br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "";
}
}
