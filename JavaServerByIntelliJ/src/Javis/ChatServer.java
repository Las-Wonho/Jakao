package Javis;

import java.util.ArrayList;

public class ChatServer {
    Server server;
    ArrayList<User> peoples;
    String id;

    ChatServer(String id, Server server) {
        peoples = new ArrayList<User>();
        this.id = id;
        this.server = server;
    }

    void new_people(User people) {
        if (people != null){
            peoples.add(people);
        }
    }

    void delete_people(User sender) {
        if (sender != null)
            peoples.remove(sender);
    }

    void send_all(User sender, String message) {
        if (sender != null)
            peoples.parallelStream()
                    .filter(x -> x != null)
                    .filter(x -> x.id != sender.id)
                    .forEach(x -> x.Send(message, this, sender.name));
    }
    @Override
    public String toString(){

        int count = peoples.size();
        String result = "";
        for (User i: peoples) {
            result += i.name + " : " + i.id + "\r\n";
        }
        return String.format("Server id is %s \r\n Server poeple count is %d \r\n::::Users info::::\r\n%s",id,count,result);
    }
}
