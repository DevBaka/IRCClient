/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irc;

import gui.mainWindow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author SickzZ
 */
public class irc {
    
    
    private irc_conn irc;
    Socket ircSocket;
    BufferedWriter writer;
    BufferedReader reader;
    String ChannelName;
    mainWindow window;
    
    public irc(String ServerName, int ServerPort, String ChannelName, String Username, mainWindow win) throws IOException{
        this.irc = new irc_conn(ServerName, ServerPort, ChannelName, Username);
        this.ircSocket = this.irc.ircSocket;
        this.writer = this.irc.writer;
        this.reader = this.irc.reader;
        this.ChannelName = ChannelName;
        this.window = win;
        irc_recv recv = new irc_recv(this, window);
    }
    
    public void irc_sendMessage(String Message) throws IOException{
        writer.write("PRIVMSG " + this.ChannelName + " :" + Message + "\r\n");
        writer.flush();
    }
    
}
