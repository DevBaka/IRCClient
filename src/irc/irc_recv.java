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
public class irc_recv implements Runnable{
    private Thread t;
    private String threadName = "t";
    private irc irc;
    private String line;
    private String Channel;
    private mainWindow mW;
    
    Socket ircSocket;
    BufferedWriter writer;
    BufferedReader reader;

    
    public irc_recv(irc IRC, mainWindow window){
        this.irc = IRC;
        this.Channel = this.irc.ChannelName;
        this.ircSocket = this.irc.ircSocket;
        this.writer = this.irc.writer;
        this.reader = this.irc.reader;
        this.mW = window;
        
        this.start();
    }
    
    public void start(){
        System.out.println("Start freenode thead");
        if(t==null){
            t = new Thread(this, threadName);
            t.start();
        }
    }

    @Override
    public void run() {
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
                    //mainWindow.addText(line);
                    //super.displayText.setText(line);
                    //super.displayText.append(line + "\n");
                    //Class<mainWindow> mW = mainWindow.class;
                    //mW.getMethod(line, parameterTypes)
                    //gui.mainWindow.class.getMethod
                    //Class<gui.mainWindow> mW;
                    //Class mW = gui.mainWindow.;
                    //mW.getMethod("addText", null);
                    mW.addText(line);
                }

                /*if(this.newChannel != null){
                    this.join(this.newChannel);
                    this.newChannel = null;
                }*/

            }
            }
            catch(IOException e ){
                System.out.println("read error: " + e);
            }    }
}