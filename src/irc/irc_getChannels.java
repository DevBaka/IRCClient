/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irc;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author devbaka
 */
public class irc_getChannels {
    private irc irc;
    private BufferedWriter writer;
    
    public void getChannels() throws IOException{
        this.writer = irc.writer;
        this.writer.write("LIST -min 5" + "\r\n");
        this.writer.flush();
    }
}
