import java.net.Socket;
import java.util.Map;

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
            message = Utils.receiveMessage(connection);
            
            if(message.equals("QUIT")) {
                running = false;
            } else if(message.equals("GET_CONNECTED_USERS")) {
                System.out.println("Atualize a lista de contatos...");
                String response = "";
                
                for(Map<K, V>.Entry<String, ClientListener> pair: server.getClients().entrySet()) {
                    response += (pair.getKey() + ";");
                }

                Utils.send(connection, response);
            } else {
                System.out.println("Recebido: " + message);
            }
        }
    }
}