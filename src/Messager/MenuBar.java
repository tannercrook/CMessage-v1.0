/*
 * ========================= PROJECT INFORMATION  ============================
 * ===========================================================================
 * 	ABOUT
 * 
 * THIS APPLICATION IS FOR BYU-IDAHO CIT262 SYSTEM ANALYSIS AND DESIGN COURSE
 * TAUGHT BY PROFESSOR MCLAUGHLIN. IT IS FOR THE FINAL TEAM PROJECT. THE 
 * PROGRAM IS DESIGNED TO BE A PROBABLE AND USEFUL SOLUTION TO SHARING 
 * CODE SECURELY BETWEEN REMOTE HOSTS.
 * 
 * 
 * 	OWNERSHIP
 * 
 * IDEAS AND PURPOSE ARE A CREATION OF PROFESSOR MCLAUGHLIN. ALL CODE WAS
 * WRITTEN BY TANNER CROOK, 2014. DOCUMENTATION AND ILLUSTRATION IS BEING
 * DONE BY THE TEAM.
 * 
 * ============================================================================
 * ============================================================================
 * 
 * 
 * ======================   MENUBAR.JAVA   ===================================
 * 
 * AUTHOR(S): TANNER CROOK, 2014
 * 
 * THIS FILE CONTAINS THE CLASS FOR THE MENUBAR AND ALL OF ITS COMPONENTS. IT
 * ALSO CONTAINS THE FUNCTIONS NECESSARY FOR THE FUNCTION OF THE BAR.
 * 
 */


package Messager;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


public class MenuBar extends JMenuBar{

	private JMenu file = new JMenu("File");
	private JMenu connection = new JMenu("Connection");
	private JMenu help = new JMenu("Help");
	
	private JMenuItem menuItemSaveAs  = new JMenuItem("Save As");
	private JMenuItem menuItemSetUp = new JMenuItem("Connect");
	
	// New Window for Setup
    static JFrame setUpFrame = new JFrame();
    static JTextField database = new JTextField(40);
    static JTextField username = new JTextField(40);
    static JTextField password = new JTextField(40);
    static JButton doneButton = new JButton();
    
    static String dbAddress;
    static String dbUsername;
    static String dbPassword;
	
	
	void buildBar()
	{
		// FILE MENU 
		// --------------------------------------------------------
		add(file);
		file.setMnemonic('F');

		// Save As Selection
		file.add(menuItemSaveAs);
		menuItemSaveAs.setMnemonic('A');


		// FILE MENU 
		// --------------------------------------------------------
		add(connection);
		connection.setMnemonic('C');

		// Save As Selection
		connection.add(menuItemSetUp);
		menuItemSetUp.setMnemonic('S');
		
		// HELP MENU
		//---------------------------------------------------------
		add(help);
		help.setMnemonic('H');
		
		
		
		// MenuBar set up
		setSize(600, 100);
		menuListeners();
	}
	
	void menuListeners()
	{
		 menuItemSaveAs.addActionListener(
			      new ActionListener()
			      {
			        public void actionPerformed(ActionEvent e)
			        {
			          // New file.
			          System.out.println("Not Functional");

			        } // End of actionPerformed method.
			      }); // 
		 
		 menuItemSetUp.addActionListener(
			      new ActionListener()
			      {
			        public void actionPerformed(ActionEvent e)
			        {
			        	
			          
			        	
			        	database.setText("Database Address...");
			        	JLabel dbLabel = new JLabel();
			        	dbLabel.setText("Database:");
			        	
			        	
			        	username.setText("Username...");
			        	JLabel usrLabel = new JLabel();
			        	usrLabel.setText("Username:");
			        	
			        	
			        	password.setText("Password...");
			        	JLabel passLabel = new JLabel();
			        	passLabel.setText("Password:");
			        	
			        	
			        	doneButton.setText("Connect");
			        	
			        	doneButton.addActionListener(
			   	             new ActionListener(){
			   	                 public void actionPerformed(
			   	                         ActionEvent e){
			   	                	 					
			   	                	 					// CONNECT
			   	                	 					dbAddress = database.getText();
			   	                	 					dbUsername = username.getText();
			   	                	 					dbPassword = password.getText();
			   	                	 					// Kill the frame
			   	                	 					setUpFrame.setVisible(false);
			   	                	 					setUpFrame.dispose();
			   	                	 					
			   	                                       }
			   	                                 }
			   	                         );
			        	
			        	setUpFrame.add(dbLabel);
			        	setUpFrame.add(database);
			        	setUpFrame.add(usrLabel);
			        	setUpFrame.add(username);
			        	setUpFrame.add(passLabel);
			        	setUpFrame.add(password);
			        	setUpFrame.add(doneButton);
			        	
			        	
			          setUpFrame.setTitle("Set Up Connection");
			          setUpFrame.setLayout(new FlowLayout());
			          setUpFrame.setSize(600, 200);
			          setUpFrame.setVisible(true);

			        } // End of actionPerformed method.
			      }); // End of menuItemNew action listener
	}
	
	public String getAddress()
	{
		return dbAddress;
	}
	
	public String getUsername()
	{
		return dbUsername;
	}
	
	public String getPassword()
	{
		return dbPassword;
	}
	
}


