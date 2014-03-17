package Messager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.zehon.FileTransferStatus;
import com.zehon.exception.FileTransferException;
import com.zehon.ftp.FTP;

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
	static MenuBar conInfo = new MenuBar();
	
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
	                	 
	                	 
	                	 // SAVE TEXT TO FILE
	                	 try {
	                		 
	             			String content = out.getOutput();
	              
	             			File file = new File("upload.txt");
	              
	             			// if file doesnt exists, then create it
	             			if (!file.exists()) {
	             				file.createNewFile();
	             			}
	              
	             			FileWriter fw = new FileWriter(file.getAbsoluteFile());
	             			BufferedWriter bw = new BufferedWriter(fw);
	             			bw.write(content);
	             			bw.close();
	              
	             			System.out.println("Done writing...");
	              
	             		} catch (IOException e2) {
	             			e2.printStackTrace();
	             		}
	                	 
	                	 // PUBLISH TO FTP SERVER
	                	 String iAddress = conInfo.getAddress();
	                	 String iUsername = conInfo.getUsername();
	                	 String iPassword = conInfo.getPassword();
	                	 String host = iAddress;
	             		String username = iUsername;
	             		String password = iPassword;
	             		String destFolder = "/test";
	             		try {
	             			
	             			// Upload Download Folder
	             			String filePath = "upload.txt";
	             			int status = FTP.sendFile(filePath, destFolder, host, username, password);
	             			if(FileTransferStatus.SUCCESS == status){
	             				JOptionPane.showMessageDialog(null, "Successful Upload");
	             				System.out.println(filePath + " got sftp-ed successfully to  folder "+destFolder);
	             				
	             				// Archive Folder
	             				// to be implemented
	             				
	             			}
	             			else if(FileTransferStatus.FAILURE == status){
	             				System.out.println("Fail to ssftp  to  folder "+destFolder);
	             				JOptionPane.showMessageDialog(null, "ERROR: Could not Upload. Check your Information.");
	             			}
	             		} catch (FileTransferException e1) {
	             			e1.printStackTrace();
	             		}
	                	 					
	                                       }
	                                 }
	                         );
		
		downloadButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						String iAddress = conInfo.getAddress();
						String iUsername = conInfo.getUsername();
						String iPassword = conInfo.getPassword();
						String host = iAddress;
						String username = iUsername;
						String password = iPassword;	
						String sftpFolder = "/test";

						String writeToLocalFolder = "";
						String nameOfFile = "upload.txt";
						try {
							int status = FTP.getFile(nameOfFile, sftpFolder, host, username, password, writeToLocalFolder);
							if(FileTransferStatus.SUCCESS == status){
								System.out.println(nameOfFile + " got downloaded successfully to  folder "+writeToLocalFolder);
							}
							else if(FileTransferStatus.FAILURE == status){
								System.out.println("Fail to download  to  folder "+writeToLocalFolder);
							}
						} catch (FileTransferException e5) {
							e5.printStackTrace();
						}

						// Open downloaded File
						
						try {
							BufferedReader br = new BufferedReader(new FileReader("upload.txt"));
							StringBuilder sb = new StringBuilder();
							String line = br.readLine();

							while (line != null) {
								sb.append(line);
								sb.append("\n");
								line = br.readLine();
							}
							out.setOutput(sb.toString());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							BufferedReader br = null;
							try {
								br = new BufferedReader(new FileReader("upload.txt"));
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

				}
	}
				);
}

}
