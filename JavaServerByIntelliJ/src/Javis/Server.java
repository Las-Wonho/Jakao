package Javis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    ServerSocket server;
    ArrayList<ChatServer> Chats;
    int server_number;
    Server() {
        server_number = 0;
        Chats = new ArrayList<ChatServer>();
        try {
            server = new ServerSocket(9090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Rsocket accept_socket() {
        if (server != null) {
            try {
                Rsocket client = new Rsocket(server.accept());
                System.out.println("connected");
                client.send("connect");
                return client;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    User new_user() {
        Rsocket rs = accept_socket();
        if (rs != null) {
            rs.send("id:");
            String id = rs.recieve();
            User user = new User(rs, id, id, this);
            return user;
        }
        return null;
    }
    void new_server(){
        server_number+=1;
        Chats.add(new ChatServer(""+(server_number), this));
    }

}
