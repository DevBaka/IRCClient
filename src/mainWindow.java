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

public class mainWindow extends JFrame {
    public mainWindow() {
                // JFrame / Root Frame
        	super("IRC-Client");
		this.setSize(800,600);
		this.setLocation(50, 50);
		this.setResizable(true);		
		this.setLayout(new BorderLayout());
                
                // Um alle Prozesse nach dem schließen der GUI zu beenden.
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
                // Menübar
		Border bo = new LineBorder(Color.yellow);
		JMenuBar navbar = new JMenuBar();
		navbar.setBorder(bo);
		
		JMenu mServer = new JMenu("Server");
		
		JMenuItem joinServer = new JMenuItem("Join Server");
		JMenuItem NetzwerkListe = new JMenuItem("Join Server");
		mServer.add(joinServer);
		
		navbar.add(mServer);
		this.setJMenuBar(navbar);
                
                
                // Server 6 Channel Liste
                JPanel panelServers = new JPanel();
                panelServers.setBackground(Color.red);
                panelServers.setSize(20,20);
                
                // Add elements to JFrame / Root Frame
                getContentPane().add(panelServers, BorderLayout.WEST);
                
		this.setVisible(true);
    }
    
}
