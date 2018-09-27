package Javis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Rsocket {
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    /**
     * @param _socket is socket that get server socket
     */
    Rsocket(Socket _socket) {
        socket = _socket;
        init(socket);
    }

    /**
     * @param so is client socket
     */
    private void init(Socket so) {
        try {
            in = new BufferedReader(new InputStreamReader(so.getInputStream()));
            out = new PrintWriter(so.getOutputStream());
        } catch (IOException e) {
            System.err.println("Can't setting I/O");
        }
    }

    public void send(String message) {
        out.println(message);
        out.flush();
    }

    public String recieve() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Can't using I/O");
        }
        return "";
    }
}
