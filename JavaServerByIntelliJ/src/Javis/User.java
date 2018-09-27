package Javis;

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
        String arg = message.split(" ")[1];
        if (command.equals("/help")) {
            socket.send(StaticField.help);
        } else if (command.equals("/join")) {
            now_server = Integer.parseInt(arg);
            server.Chats.get(now_server).new_people(this);
        } else if (command.equals("/new")) {
            server.new_server();
        } else if (command.equals("/info")) {
            socket.send(server.Chats.get(now_server).toString());
        } else if (command.equals("/move")) {
            move(Integer.parseInt(arg));
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
                server.Chats.get(now_server).send_all(this, "Exit " + name);
                System.out.println("Exit");
                break;
            }
        }
    }

    void Send(String message, String username) {
        socket.send(username + " : " + message);
    }

    void move(int server_id) {
        server.Chats.get(now_server).delete_people(this);
        now_server = server_id;
        server.Chats.get(now_server).new_people(this);
    }
}
