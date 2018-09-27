package Javis;

import java.net.Socket;
import java.util.ArrayList;

public class User implements Runnable {
    Rsocket socket;
    String name;
    String id;
    Server server;
    int now_server;

    User(Rsocket _socket, String _name, String _id, Server _server) {
        server = _server;
        socket = _socket;
        now_server = 0;
        name = _name;
        id = _id;
    }

    void analyze_message(String message) {
        String command = message.split(" ")[0];
        if (command.equals("/help")) {
            socket.send("       ★G☆O☆D★\r\n"
                    + "/new : is create new chat room\r\n"
                    + "/help : is help about rules\r\n");
        } else if (command.equals("/join")) {
            now_server = Integer.parseInt(message.split(" ")[1]);
            server.Chats.get(now_server).new_people(this);
        } else if (command.equals("/new")) {
            server.new_server();
        } else if (command.equals("/info")) {
            socket.send(server.Chats.get(now_server).toString());
        } else if (command.equals("/move")) {
            server.Chats.get(now_server).delete_people(this);
            now_server = Integer.parseInt(message.split(" ")[1]);
            server.Chats.get(now_server).new_people(this);
        } else if (command.equals("/now")) {
            socket.send("now is " + now_server);
        } else if (command.equals("/server")) {
            socket.send("server count is " + server.Chats.size());
        } else {
            server.Chats.get(now_server).send_all(this, message);
        }
    }

    @Override
    public void run() {
        while (socket.socket != null || socket != null) {
            try {
                String s = socket.recieve();
                analyze_message(s);
            } catch (Exception e) {
                server.Chats.get(now_server).send_all(this, "Exit "+name);
                System.out.println("Exit");
                break;
            }
        }
    }

    void Send(String message, ChatServer server, String username) {
        socket.send(username + " : " + message);
    }

}
