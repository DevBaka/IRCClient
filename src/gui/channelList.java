/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import irc.irc;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author devbaka
 */

public class channelList implements ActionListener{
    private irc irc;
    private JButton cmdAllChannels;
    private JTextArea txtChannels;
    private JScrollPane txtScrollChannels;
    
    public channelList() throws IOException{
        JDialog channelList = new JDialog();
        channelList.setTitle("Channel List");
        channelList.setSize(600,400);
        channelList.setLocation(250, 250);
        channelList.setModal(false);
        channelList.setLayout(new BorderLayout());
        
        JPanel pChannels = new JPanel();
        
        txtChannels = new JTextArea();
        //sirc.irc_getAllChannel();
        JPanel pButtons = new JPanel();
        cmdAllChannels = new JButton("Get Channels");
        cmdAllChannels.addActionListener(this);
        pButtons.add(cmdAllChannels);
        
        channelList.add(pChannels, BorderLayout.NORTH);
        channelList.add(pButtons, BorderLayout.SOUTH);
        channelList.setVisible(true);
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.cmdAllChannels ){
            try {
                System.out.println("get channels:");
                this.irc.irc_getAllChannel();
            } catch (IOException ex) {
                System.out.println("channelList error: " + ex);
                Logger.getLogger(channelList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
