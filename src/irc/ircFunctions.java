/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author devbaka
 * 
 * Socket socket = new Socket(server, 6667);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream( )));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream( )));
 * 
 */
public class ircFunctions {
    
    String Server;
    int Port;
    String Nickname;
    String Channel;
    
    Socket ircSocket = null;
    BufferedWriter writer;
    BufferedReader reader;
    String line;
    
    public ircFunctions()throws IOException{
        String username = "bakaTest";
        String servername = "devbaka.ddns.net";
        String channel = "#baka";
        int port = 6667;
        this.set_Data(servername, port, username);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
            if (this.line.indexOf("004") >= 0) {
                System.out.println("Nickname is not in use.");
                break;
            }
            else if (this.line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
            }
        }
        this.join("#baka");
        while ((line = reader.readLine( )) != null) {
            if (line.toLowerCase( ).startsWith("PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                writer.flush( );
            }
            else {
                // Print the raw line received by the bot.
                System.out.println(line);
            }
        }
    }
    
    public void irc_conn()throws IOException{
        this.ircSocket = new Socket(this.Server, this.Port);
        this.writer = new BufferedWriter(new OutputStreamWriter(this.ircSocket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(this.ircSocket.getInputStream()));
    }
    
    public void send_data(String command)throws IOException{
        //OutputStream  outstream = this.ircSocket.getOutputStream();
        //PrintWriter out = new PrintWriter(outstream);
        //String sendData = command;
        //out.print(sendData);
        if(command == null){
            command = "";
        }
        else{
        this.writer.write(command);
        this.writer.flush();
        }
       
        
    }
    
    public void join(String channelName)throws IOException{
        this.send_data(String.format("JOIN %s", channelName));
    }
    
    public void login(String nickname, String username, String hostname, String realname)throws IOException{
        String msg = String.format("USER %s %s %s %s", username,hostname, this.Server, realname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    public void login(String nickname)throws IOException{
        String msg = String.format("USER %s %s %s %s", nickname,nickname, this.Server, nickname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    public void set_Data(String Host, int Port, String nickname ){
        this.Server = Host;
        this.Port = Port;
        this.Nickname = nickname;
    }
    public void testConnect()throws IOException{
        String username = "bakaBot";
        String servername = "devbaka.ddns.net";
        String channel = "#baka";
        int port = 6667;
        this.set_Data(servername, port, username);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
            if (this.line.indexOf("004") >= 0) {
                System.out.println("Nickname is not in use.");
                break;
            }
            else if (this.line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
            }
        }
        this.join("#baka");
        while ((line = reader.readLine( )) != null) {
            if (line.toLowerCase( ).startsWith("PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                writer.flush( );
            }
            else {
                // Print the raw line received by the bot.
                System.out.println(line);
            }
        }
        
    }
    
    
    
}
