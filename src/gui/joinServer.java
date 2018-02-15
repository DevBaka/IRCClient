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

import irc.ircFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import irc.ircFunctions;
import java.awt.Color;
import java.awt.GridLayout;
public class joinServer implements ActionListener{
    private JButton cmdTestconnect;
    private JTextArea txtServername;
    private SpinnerModel portRange;
    private JSpinner portNumber;
    private JTextArea txtUsername;
    private JTextArea txtChannelname;
    private Thread t;
    
    public joinServer(){
        JDialog joinServer = new JDialog();
        joinServer.setTitle("Join Server");
        joinServer.setSize(200,400);
        joinServer.setLocation(250, 250);
        joinServer.setModal(true);
        joinServer.setLayout(new GridLayout(5, 2));
        txtServername = new JTextArea();
        txtServername.setBackground(Color.BLUE);
        
        portRange = new SpinnerNumberModel();
        portNumber = new JSpinner(portRange);
        portNumber.setSize(100,25);
        
        txtUsername = new JTextArea();
        
        txtChannelname = new JTextArea();
        
        
        cmdTestconnect = new JButton("Test");
        cmdTestconnect.setSize(50,20);
        cmdTestconnect.addActionListener(this);
        
        JLabel lblServername = new JLabel("Servername: ");
        JLabel lblServerPort = new JLabel("Port: ");
        JLabel lblUsername = new JLabel("Username: ");
        JLabel lblChannelname = new JLabel("Channelname: ");
        JLabel lblenter = new JLabel("Verbinden: ");
        
        joinServer.add(lblServername);
        joinServer.add(txtServername);
        joinServer.add(lblServerPort);
        joinServer.add(portNumber);
        joinServer.add(lblUsername);
        joinServer.add(txtUsername);
        joinServer.add(lblChannelname);
        joinServer.add(txtChannelname);
        joinServer.add(lblenter);
        joinServer.add(cmdTestconnect);
        joinServer.pack();
        joinServer.setVisible(true);
        
    }
    
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == this.cmdTestconnect){
            String text = "";
            try{
                System.out.println("Server info: " + txtServername.getText() + ":" + portNumber.getValue());
                ircFunctions irc = new irc.ircFunctions(txtServername.getText(),(int)portNumber.getValue(), txtChannelname.getText(), txtUsername.getText());
                irc.start();
                //irc.irc_conn();

            }
            catch(IOException e){
                System.out.println("errorirc: " + e);
            }
        }
     }
}
