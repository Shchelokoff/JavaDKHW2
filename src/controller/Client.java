package controller;

import view.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame {
    private Server server;
    private ClientView clientView;
    private String nickname;
    private boolean flag;
    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }
    public boolean connectToServer(String nickname) {
        this.nickname = nickname;
        if (server.connectUser(this)){
            showInfo("Вы успешно подключились!\n");
            flag = true;
            String log = server.getData();
            if (log != null){
                showInfo(log);
            }
            return true;
        } else {
            showInfo("Подключение не удалось");
            return false;
        }
    }
    public void answerFromServer(String text) {
        showInfo(text);
    }
    public void updateChat(String text) {
        if (flag) {
            if (!text.isEmpty()) {
                server.updateChat(nickname + ": " + text);
            }
        } else {
            showInfo("Нет подключения к серверу");
        }
    }
    public String getName() {
        return nickname;
    }
    public void showInfo(String text) {
        clientView.showInfo(text + "\n");
    }
}