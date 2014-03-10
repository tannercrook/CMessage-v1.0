package Messager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
 * ======================   BUTTONPANEL.JAVA   ===================================
 * 
 * AUTHOR(S): TANNER CROOK, 2014
 * 
 * THIS FILE CONTAINS THE MAIN METHOD AND THE CLASS FOR THE BUTTON PANEL.
 * 
 */

public class ButtonPanel extends JPanel {
	static JButton editButton = new JButton();
	static JButton publishButton = new JButton();
	static JButton downloadButton = new JButton();
	static JPanel bPanel = new JPanel();
	static OutScreen out = new OutScreen();
	
	// SUB EDIT FRAME
	static JFrame editFrame = new JFrame();
	static JTextArea editSpace = new JTextArea();
	static JScrollPane scrollPane = new JScrollPane(editSpace);
	static JButton doneButton = new JButton();
	
	public void buildButtonPanel()
	{
		setListeners();
		
		editButton.setText("Edit in Larger Window");
		add(editButton);
		
		publishButton.setText("Publish");
		add(publishButton);
		
		downloadButton.setText("Download");
		add(downloadButton);
		
	}
	
	void buildSubFrame()
	{
		
		doneButton.setText("Done");
		bPanel.add(doneButton);
		
		bPanel.setVisible(true);
		
		
		editSpace.setLineWrap( true );
	    editSpace.setWrapStyleWord( true );
	    editSpace.setText(out.getOutput());

	    scrollPane.setPreferredSize(new Dimension(700, 700));
		editFrame.add(scrollPane);
		editFrame.add(bPanel);
		
		
		editFrame.setTitle("Edit");
		editFrame.setLayout(new FlowLayout());
		editFrame.setSize(800, 850);
		editFrame.setVisible(true);
		
		doneButton.addActionListener(
	             new ActionListener(){
	                 public void actionPerformed(
	                         ActionEvent e){
	                	 					
	                	 					out.setOutput(editSpace.getText());
	                	 
	                	 					// Kill the frame
	                	 					editFrame.setVisible(false);
	                	 					editFrame.dispose();
	                	 					
	                                       }
	                                 }
	                         );
	}
	
	
	// LISTENERS FOR BUTTON PANEL
	void setListeners()
	{
		editButton.addActionListener(
	             new ActionListener(){
	                 public void actionPerformed(
	                         ActionEvent e){
	                	 					buildSubFrame();
	                                       }
	                                 }
	                         );
		
		publishButton.addActionListener(
	             new ActionListener(){
	                 public void actionPerformed(
	                         ActionEvent e){
	                	 					
	                	 					System.out.println("NOT SET UP YET, CHILL");
	                	 					
	                                       }
	                                 }
	                         );
		
		downloadButton.addActionListener(
	             new ActionListener(){
	                 public void actionPerformed(
	                         ActionEvent e){
	                	 					
	                	 					System.out.println("NOT SET UP YET, CHILL");
	                	 					
	                                       }
	                                 }
	                         );
	}

}
