/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author devbaka
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class mainWindow2 extends JFrame {
    public mainWindow2() {
        	super("IRC-Client");
		this.setSize(800,600);
		this.setLocation(50, 50);
		this.setResizable(false);
		
		this.setLayout(null);
		
		Border bo = new LineBorder(Color.yellow);
		JMenuBar navbar = new JMenuBar();
		navbar.setBorder(bo);
		
		JMenu mServer = new JMenu("Server");
		
		JMenuItem joinServer = new JMenuItem("Join Server");
		JMenuItem NetzwerkListe = new JMenuItem("Join Server");
		mServer.add(joinServer);
		
		navbar.add(mServer);
		this.setJMenuBar(navbar);
		this.setVisible(true);
    }
    
}
