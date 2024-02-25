package controller;

import controller.Client;
import view.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends JFrame {
    private List<Client> clientList;
    private ServerView serverView;
    private Database database;
    private boolean flag = true;

    public Server(ServerView serverView, Database database){
        clientList = new ArrayList<>();
        this.serverView = serverView;
        this.database = database;
    }
    public boolean connectUser(Client client){
        if (!flag){
            return false;
        }
        clientList.add(client);
        showInfo(client.getName() + " подключился к беседе");
        return true;
    }
    public void stopServer(){
        flag = false;
    }
    private void showChat(String text){
        for (Client client: clientList){
            client.showInfo(text);
        }
    }
    public void updateChat(String text){
        database.read();
        showChat(text);
        database.save(text);
    }
    private void systemInfo(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }
    public String getData() {
        return database.read();
    }
    private void saveInDatabase(String text){
        database.save(text);
    }
    public void showInfo(String text){
        serverView.showInfo(text + "\n");
    }
}
