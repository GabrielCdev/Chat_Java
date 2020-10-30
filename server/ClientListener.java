import java.net.Socket;

public class ClientListener implements Runnable {

    private String connection_info;
    private Socket connection;
    private Server server;
    private boolean running;

    public ClientListener(String connection_info, Socket connection, Server server) {
        this.connection_info = connection_info;
        this.connection = connection;
        this.server = server;
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public void run() {
        running = true;
        String message;
        
        while(running) {
            message = Utils.receiveMessage(socket);
            
            if(message == null || message.equals("CHAT_CLOSE")) {
                if(chatOpen) {
                    home.getOpened_chats().remove(connection_info);
                    home.getConnected_listeners().remove(connection_info);
                    chatOpen = false;

                    try {
                        socket.close();
                    } catch(IOException ex) {
                        System.err.println("[ClientListener:run] -> " + ex.getMessage());
                    }
                    chat.dispose();
                }
                running = false;
            } else {
                String[] fields = message.split(";");

                if(fields.length > 1) {
                    String[] splited = fields[1].split(":");
                    connection_info = fields[1];
                    
                    if(!chatOpen) {
                        home.getOpened_chats().add(connection_info);
                        home.getConnected_listeners().put(connection_info, this);
                        chatOpen = true;
                        chat = new Chat(home, socket, connection_info, home.getConnection_info().split(":")[0]);
                    }                    
                } else if(fields[0].equals("MESSAGE")) {
                    chat.append_message(fields[1]);
                }
            }
        }
    }
}