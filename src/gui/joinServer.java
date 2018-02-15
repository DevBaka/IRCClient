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
public class joinServer implements ActionListener{
    private JButton cmdTestconnect;
    private Thread t;
    
    public joinServer(){
        JDialog joinServer = new JDialog();
        joinServer.setTitle("Join Server");
        joinServer.setSize(200,400);
        joinServer.setLocation(250, 250);
        joinServer.setModal(true);
        
        
        cmdTestconnect = new JButton("Test");
        cmdTestconnect.addActionListener(this);
        joinServer.add(cmdTestconnect);
        joinServer.pack();
        joinServer.setVisible(true);
        
    }
    
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == this.cmdTestconnect){
            String text = "";
            try{
                ircFunctions irc = new irc.ircFunctions("freenode");
                irc.start();
                //irc.irc_conn();

            }
            catch(IOException e){
                System.out.println("errorirc: " + e);
            }
        }
     }
}
