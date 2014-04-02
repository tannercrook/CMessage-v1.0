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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MenuBar extends JMenuBar{

	private JMenu file = new JMenu("File");
	private JMenu connection = new JMenu("Connection");
	private JMenu help = new JMenu("Help");
	
	private JMenuItem menuItemSaveAs  = new JMenuItem("Save As");
	private JMenuItem menuItemSetUp = new JMenuItem("Connect");
	private JMenuItem menuItemSetKey = new JMenuItem("Set Key");
	private JMenuItem menuItemGuide = new JMenuItem("User Guide");
	
	// New Window for Setup
    static JFrame setUpFrame = new JFrame();
    static JTextField database = new JTextField(40);
    static JTextField username = new JTextField(40);
    static JPasswordField password = new JPasswordField(40);
    static JTextField folder = new JTextField(40);
    static JButton doneButton = new JButton();
    
    static String dbAddress = "Address..";
    static String dbUsername = "Username..";
    static String dbPassword = "Password";
    static String dbFolder = "default";
    
    // Set Key
    ButtonPanel keySetter = new ButtonPanel();
	
	
	void buildBar()
	{
		// FILE MENU 
		// --------------------------------------------------------
		add(file);
		file.setMnemonic('F');

		// Save As Selection
		file.add(menuItemSaveAs);
		menuItemSaveAs.setMnemonic('A');


		// Connect Menu 
		// --------------------------------------------------------
		add(connection);
		connection.setMnemonic('C');

		// Save As Selection
		connection.add(menuItemSetUp);
		menuItemSetUp.setMnemonic('S');
		
		connection.add(menuItemSetKey);
		menuItemSetUp.setMnemonic('K');
		
		// HELP MENU
		//---------------------------------------------------------
		add(help);
		help.setMnemonic('H');
		
		help.add(menuItemGuide);
		
		
		
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
			          
			          SimpleFileChooser sfc = new SimpleFileChooser();
			          sfc.setVisible(true);
			          
			          

			        } // End of actionPerformed method.
			      }); // 
		 
		 menuItemSetUp.addActionListener(
			      new ActionListener()
			      {
			        public void actionPerformed(ActionEvent e)
			        {
			        	
			          
			        	
			        	database.setText(dbAddress);
			        	JLabel dbLabel = new JLabel();
			        	dbLabel.setText("Database:");
			        	
			        	
			        	username.setText(dbUsername);
			        	JLabel usrLabel = new JLabel();
			        	usrLabel.setText("Username:");
			        	
			        	
			        	password.setText(dbPassword);
			        	JLabel passLabel = new JLabel();
			        	passLabel.setText("Password:");
			        	
			        	folder.setText(dbFolder);
			        	JLabel projLabel = new JLabel();
			        	projLabel.setText("Project");
			        	
			        	
			        	doneButton.setText("Connect");
			        	
			        	doneButton.addActionListener(
			   	             new ActionListener(){
			   	                 public void actionPerformed(
			   	                         ActionEvent e){
			   	                	 					
			   	                	 					// CONNECT
			   	                	 					dbAddress = database.getText();
			   	                	 					dbUsername = username.getText();
			   	                	 					dbPassword = password.getText();
			   	                	 					dbFolder = folder.getText();
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
			        	setUpFrame.add(projLabel);
			        	setUpFrame.add(folder);
			        	setUpFrame.add(doneButton);
			        	
			        	
			          setUpFrame.setTitle("Set Up Connection");
			          setUpFrame.setLayout(new FlowLayout());
			          setUpFrame.setSize(600, 250);
			          setUpFrame.setVisible(true);

			        } // End of actionPerformed method.
			      }); // End of menuItemNew action listener
		 
		 menuItemSetKey.addActionListener(
			      new ActionListener()
			      {
			        public void actionPerformed(ActionEvent e)
			        {
			          // New file.
			          String newKey = JOptionPane.showInputDialog("Enter Encryption Key");
			          keySetter.setNewKey(newKey);

			        } // End of actionPerformed method.
			      }); // 
		 
		 
		 // HELP WINDOW
		 menuItemGuide.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent e)
					 {
						 // Make Components
						 JFrame helpFrame = new JFrame();
						 JScrollPane helpPane = new JScrollPane();
						 JTextArea helpArea = new JTextArea();
						 
						 // Customize Text Area
						 helpArea.setEditable(false);
						 helpArea.setWrapStyleWord(true);
						 helpArea.setLineWrap(true);
						 
						 // Set the Text - calls to function below
						 helpArea.setText(getHelpText());
						 
						 // Add TextArea to Pane
						 helpPane.getViewport().add(helpArea);
						 
						 // Add Pane to Frame
						 helpFrame.add(helpPane);
						 
						 // Initialize Frame
						 helpFrame.setTitle("Help");
						 helpFrame.setSize(800, 600);
						 helpFrame.setLocationRelativeTo(null);
						 helpFrame.setVisible(true);
						 
					 }
				 });
		 
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
	
	public String getFolder()
	{
		return dbFolder;
	}
	
	private String getHelpText()
	{
		
		String helpTxt = "Something went Wrong";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("help.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			
			helpTxt = sb.toString();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("help.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		}
		
		return helpTxt;
	}
}


