package model;

import controller.Server;
import controller.Client;
import view.ClientView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    JTextArea flood;
    JTextField IPAddress, port, login, message;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;
    private Client client;

    public ClientGUI(Server server) {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX(), server.getY());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        client = new Client(this, server);
        createPanel();
        setVisible(true);
    }
    public void showMessage(String text) {
        flood.append(text);
    }
    public void hideHeaderPanel(boolean visible){
        headerPanel.setVisible(visible);
    }

    public void login(){
        if (client.connectToServer(login.getText())){
            headerPanel.setVisible(false);
        }
    }

    private void message(){
        client.updateChat(message.getText());
        message.setText("");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(floodPanel());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        IPAddress = new JTextField();
        port = new JTextField();
        login = new JTextField("Ivan");
        password = new JPasswordField();
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        headerPanel.add(IPAddress);
        headerPanel.add(port);
        headerPanel.add(new JPanel());
        headerPanel.add(login);
        headerPanel.add(password);
        headerPanel.add(btnLogin);
        return headerPanel;
    }

    private Component floodPanel() {
        flood = new JTextArea();
        flood.setEditable(false);
        return new JScrollPane(flood);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        message = new JTextField();
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(message);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }
    @Override
    public void showInfo(String text) {
        flood.append(text);
    }
}
