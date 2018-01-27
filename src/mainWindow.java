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
                // add Panel
                JPanel panelServers = new JPanel();
                panelServers.setBackground(Color.red);
                // add TextArea
                JTextArea displayServers = new JTextArea(33,15);
                displayServers.setEditable(false);
                // add ScrollPane to TextArea
                JScrollPane scrollServers = new JScrollPane(displayServers); 
                scrollServers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                //scrollServers.setBounds(10, 11, 455, 249);
                // add TextArea to panelServers
                panelServers.add(displayServers);
                
                // Users Liste
                JPanel panelUsers = new JPanel();
                panelUsers.setBackground(Color.red);
                JTextArea displayUsers = new JTextArea(33,15);
                // min hight für dynamisches layout:
                //JTextArea displayUsers = new JTextArea();
                //displayUsers.setPreferredSize(new Dimension(33, 105));
                displayUsers.setEditable(false);
                JScrollPane scrollUsers = new JScrollPane(displayUsers);
                scrollUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                panelUsers.add(displayUsers);
                
                // Text box & Eingabe / Center / content
                JPanel panelText = new JPanel();
                panelText.setBackground(Color.blue);
                JTextArea displayText = new JTextArea(28,37);
                displayText.setEditable(false);
                JScrollPane scrollText = new JScrollPane(displayText);
                scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                panelText.add(displayText);
                
                
                // Add elements to JFrame / Root Frame
                getContentPane().add(panelServers, BorderLayout.WEST);
                getContentPane().add(panelUsers, BorderLayout.EAST);
                getContentPane().add(panelText, BorderLayout.CENTER);
                //pack();
                setLocationRelativeTo(null);
		this.setVisible(true);
    }
    
}
