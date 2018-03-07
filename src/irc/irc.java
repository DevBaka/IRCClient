/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irc;

import gui.channelList;
import gui.mainWindow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SickzZ
 */
public final class irc {
    
    private irc_conn irc;
    Socket ircSocket;
    BufferedWriter writer;
    BufferedReader reader;
    String ChannelName;
    String UserName;
    mainWindow window;
    int status;
    public irc(String ServerName, int ServerPort, String ChannelName, String Username, mainWindow win) throws IOException{
        this.irc = new irc_conn(ServerName, ServerPort, ChannelName, Username);
        this.ircSocket = this.irc.ircSocket;
        this.writer = this.irc.writer;
        this.reader = this.irc.reader;
        this.ChannelName = ChannelName;
        this.UserName = Username;
        this.window = win;
        irc_recv recv = new irc_recv(this, window);
        add_Server(ServerName, ChannelName);
    }

    public void add_Server(String server, String Channel){
        window.addServer(server, Channel);

    }
    
    public void irc_joinChannel(String channel)
    {
        try {
            writer.write("JOIN " + channel);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(irc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void irc_sendMessage(String Message){
        try {
            writer.write("PRIVMSG " + this.ChannelName + " :" + Message + "\r\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(irc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.window.addText(this.UserName + " " + Message);
    }
    
    public void irc_getAllChannel() throws IOException{
        System.out.println("hello test");
        writer.write("LIST -min 5" + "\r\n");
        writer.flush();
    }
    
    
}