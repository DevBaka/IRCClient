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
public class joinChannel implements ActionListener{
    private JTextArea txtChannelname;
    private JButton cmdTestconnect;
    private Thread t;
    
    public joinChannel(){
        JDialog joinChannel = new JDialog();
        joinChannel.setTitle("Join Channel");
        joinChannel.setSize(200,400);
        joinChannel.setLocation(250, 250);
        joinChannel.setModal(true);
        joinChannel.setLayout(new GridLayout(2, 2));
        txtChannelname = new JTextArea();
        
        cmdTestconnect = new JButton("Test");
        cmdTestconnect.setSize(50,20);
        cmdTestconnect.addActionListener(this);
        
        JLabel lblChannelname = new JLabel("Channelname: ");
        JLabel lblEnter = new JLabel("Join Channel: ");
        joinChannel.add(lblChannelname);
        joinChannel.add(txtChannelname);
        joinChannel.add(lblEnter);
        joinChannel.add(cmdTestconnect);
        joinChannel.pack();
        joinChannel.setVisible(true);
        
    }
    
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == this.cmdTestconnect){
            String text = "";
            try{
            irc.ircFunctions ircSession = null;
            ircSession.join((String)this.txtChannelname.getText());
            }
            catch(IOException e){
                System.out.println("join channel error: " + e);
            }
        }
     }
}
