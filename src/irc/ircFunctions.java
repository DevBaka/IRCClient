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
public class ircFunctions implements Runnable{
    
    String Server;
    int Port;
    String Nickname;
    String Channel;
    
    Socket ircSocket;
    BufferedWriter writer;
    BufferedReader reader;
    String line;
   
    private Thread t;
    private String threadName;
    
   /* public ircFunctions()throws IOException{
        System.out.println("irc open");
        String username = "devbaka223";
        String servername = "irc.freenode.net";
        String channel = "#baka";
        int port = 6667;
        this.set_Data(servername, port, username, channel);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
           // System.out.println("lineUsername: 004: \n" + this.line.indexOf("004") + "line 433: \n" + this.line.indexOf("433"));
           System.out.println("line: " + this.line);
           /*if(this.line.contains("End of /MOTD")){
               this.join("#baka");
           }*//*
            if (this.line.indexOf("004") >= 0) {
                System.out.println("Nickname is not in use.");
                break;
            }
            else if (this.line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
                //return;
            }
        }
        this.join("#baka");
        while ((line = reader.readLine( )) != null) {
                       
          System.out.println("line2: " + this.line);

            //if (line.startsWith("PING ")) {
            if(line.contains("PING ") && !line.contains("PRIVMSG")){
                // We must respond to PINGs to avoid being disconnected.
                System.out.println("got pinged sub: " + line.substring(5));
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.write("PRIVMSG " + this.Channel + " :I got pinged!\r\n");
                writer.flush( );
            }
            else {
                // Print the raw line received by the bot.
                System.out.println("line2any: " + line);
            }
        }        
    }*/
   
    public ircFunctions(String servers) throws IOException{
        this.threadName = servers;
        System.out.println("irc open");
        String username = "devbaka223";
        String servername = "irc.freenode.net";
        String channel = "#baka";
        int port = 6667;
        this.set_Data(servername, port, username, channel);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
           // System.out.println("lineUsername: 004: \n" + this.line.indexOf("004") + "line 433: \n" + this.line.indexOf("433"));
           System.out.println("line: " + this.line);
           /*if(this.line.contains("End of /MOTD")){
               this.join("#baka");
           }*/
            if (this.line.indexOf("004") >= 0) {
                System.out.println("Nickname is not in use.");
                break;
            }
            else if (this.line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
                //return;
            }
        }
        this.join("#baka");
    }
    
    public void run(){
        try{
        while ((line = reader.readLine( )) != null) {
                       
          System.out.println("line2: " + this.line);

            //if (line.startsWith("PING ")) {
            if(line.contains("PING ") && !line.contains("PRIVMSG")){
                // We must respond to PINGs to avoid being disconnected.
                System.out.println("got pinged sub: " + line.substring(5));
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.write("PRIVMSG " + this.Channel + " :I got pinged!\r\n");
                writer.flush( );
            }
            else {
                // Print the raw line received by the bot.
                System.out.println("line2any: " + line);
            }
        }
        }
        catch(IOException e ){
            System.out.println("read error: " + e);
        }
    }
    public void start(){
        System.out.println("Start freenode thead");
        if(t==null){
            t = new Thread(this, threadName);
            t.start();
        }
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
    public void testConnect()throws IOException{
        /*String username = "bakaBot3";
        String servername = "devbaka.ddns.net";
        String channel = "#baka";
        int port = 6667;
        this.set_Data(servername, port, username);
        this.irc_conn();
        this.login(username);
        
        while((this.line = this.reader.readLine()) != null){
            System.out.println("lineUsername: 004: \n" + this.line.indexOf("004") + "line 433: \n" + this.line.indexOf("433"));
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
            if (line.toLowerCase( ).startsWith(" PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                System.out.println("GOT Ping");
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                writer.flush( );
            }
            else if(line.contains("/MOTD")){
                
            }
            else {
                // Print the raw line received by the bot.
                System.out.println(line);
            }
        }*/
        
    }
    
    
    
}
