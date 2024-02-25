package model;

import controller.Client;
import controller.Database;
import controller.Server;
import view.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerGUI extends JFrame implements ServerView {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    private Server server;
    JTextArea serverInfo;
    JButton btnStart, btnStop;
    public ServerGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        add(createButtons(), BorderLayout.SOUTH);
        setVisible(true);
        createPanel();
        server = new Server(this, new Database());
    }
    private void createPanel() {
        serverInfo = new JTextArea();
        add(serverInfo);
        add(createButtons(), BorderLayout.SOUTH);
    }
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.showInfo("Сервер запущен!");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
                server.showInfo("Сервер остановлен!");
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
    public Server returnServer() {
        return server;
    }
    @Override
    public void showInfo(String text) {
        serverInfo.append(text);
    }
}

