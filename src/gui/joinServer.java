/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author devbaka
 */
import javax.swing.*;
public class joinServer {
    /*
    funktioniert nicht so ganz richtig bzw es wird ein neues Programm initialisiert wodurch ein komplett neuer Frame gestartet wird.
    */
    public joinServer(){
        JDialog joinServer = new JDialog();
        joinServer.setTitle("Join Server");
        joinServer.setSize(200,400);
        
        joinServer.setVisible(true);
        
    }
}
