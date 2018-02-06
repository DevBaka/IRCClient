/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author devbaka
 */
public class ircFunctions {
    
    String Server;
    int Port;
    String Nickname;
    String Channel;
    
    Socket ircSocket;
    
    public void irc_conn() throws IOException{
        this.ircSocket = new Socket(this.Server, this.Port);
    }
    
    public void send_data(String command) throws IOException {
        OutputStream  outstream = this.ircSocket.getOutputStream();
        PrintWriter out = new PrintWriter(outstream);
        String sendData = command;
        out.print(sendData);
    }
    
    public void join(String channelName)throws IOException{
        this.send_data(channelName);
    }
    
    public void login(String nickname, String username, String hostname, String realname, String servername)throws IOException{
        String msg = String.format("USER %s %s %s %s", username,hostname, servername, realname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    public void login(String nickname, String servername)throws IOException{
        String msg = String.format("USER %s %s %s %s", nickname,nickname, servername, nickname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    
    public void testConnect(){
        String username = "baka";
    }
    
}
