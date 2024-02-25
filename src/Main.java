import controller.Client;
import controller.Server;
import model.ClientGUI;
import model.ServerGUI;
import view.ClientView;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        Server server = serverGUI.returnServer();
        ClientGUI clientGUI1 = new ClientGUI(server);
        clientGUI1.setLocation(server.getX() - 300, server.getY());
        ClientGUI clientGUI2 = new ClientGUI(server);
        clientGUI2.setLocation(server.getX() + 300, server.getY());
    }
}