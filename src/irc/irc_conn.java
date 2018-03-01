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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author SickzZ
 */
public class irc_conn {
    String Server;
    int Port;
    String Nickname;
    String Channel;
    
    Socket ircSocket;
    BufferedWriter writer;
    BufferedReader reader;
    String line;
           
    private int channelNumbers;
    private String newChannel = null;
    
    
    public irc_conn(String ServerName, int ServerPort, String ChannelName, String Username) throws IOException{
        System.out.println("irc open");
        String username = Username;
        String servername = ServerName;
        String channel = ChannelName;
        int port = ServerPort;
        this.set_Data(servername, port, username, channel);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
           // System.out.println("lineUsername: 004: \n" + this.line.indexOf("004") + "line 433: \n" + this.line.indexOf("433"));
           System.out.println("line: " + this.line);
           /*if(this.line.contains("End of /MOTD")){
               this.join("#baka");
           }*/
            if (this.line.contains("004")) {
                System.out.println("Nickname is not in use.");
                break;
            }
            else if (this.line.contains("433")) {
                System.out.println("Nickname is already in use.");
                return;
                //return;
            }
        }
        //todo: if abfrage, channel angegeben?
        this.join(channel);
    }
    
    
    
  
    public String read_data()throws IOException{
            while ((line = reader.readLine( )) != null) {
                if (line.toLowerCase( ).startsWith("PING ")) {
                    // We must respond to PINGs to avoid being disconnected.
                    writer.write("PONG " + line.substring(5) + "\r\n");
                    writer.write("PRIVMSG " + this.Channel + " :I got pinged!\r\n");
                    writer.flush( );
                }
                else {
                    System.out.println("line: " + line);
                    return("line: " + line);
                }
            }
            return("");

    }
    public void irc_conn() throws IOException{
        System.out.println("Server: " + this.Server);
        this.ircSocket = new Socket(this.Server, this.Port);
        this.writer = new BufferedWriter(new OutputStreamWriter(this.ircSocket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(this.ircSocket.getInputStream()));
    }
    
    public void send_data(String command) throws IOException {
        //OutputStream  outstream = this.ircSocket.getOutputStream();
        //PrintWriter out = new PrintWriter(outstream);
        //String sendData = command;
        //out.print(sendData);
        System.out.println("send: " + command);
        this.writer.write(command +"\n");
        this.writer.flush();
        
    }
    
    //Send Message
    /*
    Todo:
    max lengh or splitting in mehrere Strings/messages mit irc max lengh
    */
    /*public void send_message(String message)throws IOException{
        writer.write("PRIVMSG " + this.Channel + " :"+ message +"\r\n");
        writer.flush( );
    }*/
    
    public void join(String channelName)throws IOException{
        System.out.println("Join: " + channelName);
        this.send_data(String.format("JOIN %s", channelName));
    }
    
    public void login(String nickname, String username, String hostname, String realname)throws IOException{
        System.out.println("login");
        String msg = String.format("USER %s %s %s %s", username,hostname, this.Server, realname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    public void login(String nickname)throws IOException{
        System.out.println("login");

        String msg = String.format("USER %s %s %s %s", nickname,nickname, this.Server, nickname);
        this.send_data(msg);
        this.send_data("NICK " + nickname);
    }
    public void set_Data(String Host, int Port, String nickname, String channel ){
        System.out.println("setdata");
        this.Server = Host;
        this.Channel = channel;
        this.Port = Port;
        this.Nickname = nickname;
    }
}