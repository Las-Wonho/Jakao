package Javis;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Server server = new Server();
        server.new_server();

        System.out.println("Program Start");
        while(true){

            User user = server.new_user();
            server.Chats.get(0).new_people(user);
            Thread thread = new Thread(user);
            thread.start();
        }
    }
}
