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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

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


	// Text Encryption and Decryption


	static String key = "na";


	/*
	 * A KEY IS REQUIRED FOR THE PROPER ENCRYPTION AND DECRYPTION OF TEXT.
	 * THIS IS THE METHOD THAT SETS THAT KEY. IT IS CALLED EVERYTIME A USER
	 * IS ASKED TO INPUT A KEY. IT IS ALSO CALLED DURING THE BUILD BUTTON
	 * PANEL METHOD.
	 */


	public void setNewKey(String aKey)
	{
		key = aKey;

	}

	/*
	 * MAIN BUILDER FOR THIS FILE. THIS PUTS EVERYTHING TOGETHER.
	 */
	public void buildButtonPanel()
	{


		// SET LISTENERS FOR BUTTON PANEL BUTTONS
		setListeners();

		// MAKE BUTTONS
		//editButton.setText("Edit in Larger Window");
		//add(editButton);

		publishButton.setText("Publish");
		add(publishButton);

		downloadButton.setText("Download");
		add(downloadButton);

	}

	/*
	 *  THIS IS THE BUILDER FOR THE 'EDIT' WINDOW. IT MAKES AN ENTIRELY NEW
	 *  FRAME FOR THE USER TO MANIPULATE... THIS MAY OR MAY NOT BE INCLUDED
	 *  IN THE FINAL VERSION OF THIS APP.
	 */
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
						// BUILDS NEW FRAME
						buildSubFrame();
					}
				}
				);

		publishButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){


						/*
						 * SORRY FELLAS(). THIS IS A CRAZY METHOD... 
						 * 
						 * IT IS THE ACTION LISTENER FOR THE PUBLISH BUTTON(pushes text to ftp).
						 * 
						 * IT OPERATES IN THESE STEPS..
						 * 
						 * 1. CHECKS TO SEE IF KEY HAS BEEN SET, IF(not){SET KEY}.
						 * 2. ENCRYPTS THE TEXT USING THE KEY.
						 * 3. SAVES FILE TO APP ROOT <DIR> AS "upload.txt".
						 * 4. PUSHES FILE TO FTP SERVER USING USER SET CONNECTION INFO.
						 * 
						 * 
						 * I really do feel bad about this one....
						 * 
						 * If you need help understanding what is going on feel free 
						 * to contact me. 
						 * 
						 */

						// CREATE DATE FOR ARCHIVING PURPOSES
						DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd::HH:mm:ss");
						Date date = new Date();
						String time = dateFormat.format(date) + ".txt";


						try {

							BasicTextEncryptor textEncryptor = new BasicTextEncryptor();


							if(key.equals("na"))
							{
								key = JOptionPane.showInputDialog("Enter Encryption Key");
							}

							textEncryptor.setPassword(key);

							String newline = System.getProperty("line.separator");
							String content = out.getOutput() + newline + "//  Last Modified: " + dateFormat.format(date) + "   by: " + conInfo.getUsername();
							String encryptedText = textEncryptor.encrypt(content);

							// UPLOADER FILE
							File file = new File("upload.txt");

							// ARCHIVER FILE




							// if file doesnt exists, then create it
							if (!file.exists()) {
								System.out.println("Creating Upload File");
								file.createNewFile();
							}


							// UPLOAD FILE
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(encryptedText);
							bw.close();

							System.out.println("Done writing upload file...");

							// Archive Files
							File archFile = new File(time);

							System.out.println("Creating Archive File called " + time);
							archFile.createNewFile();

							// ARCHIVE FILE
							FileWriter aw = new FileWriter(archFile.getAbsoluteFile());
							BufferedWriter abw = new BufferedWriter(aw);
							abw.write(encryptedText);
							abw.close();

							System.out.println("Done writing archive file...");


						} catch (IOException e2) {
							e2.printStackTrace();
						}

						// PUBLISH TO FTP SERVER
						String iAddress = conInfo.getAddress();
						String iUsername = conInfo.getUsername();
						String iPassword = conInfo.getPassword();
						String iFolder = conInfo.getFolder();
						String host = iAddress;
						String username = iUsername;
						String password = iPassword;
						String destFolder = ("/" + iFolder);
						String archFolder = ("/" + iFolder + "/Archives");
						try {

							// Upload Download Folder
							String filePath = "upload.txt";
							int status = FTP.sendFile(filePath, destFolder, host, username, password);
							if(FileTransferStatus.SUCCESS == status){
								JOptionPane.showMessageDialog(null, "Successful Upload");
								System.out.println(filePath + " got sftp-ed successfully to  folder "+destFolder);



								// Archive Folder
								String archFilePath = time;
								int archStatus = FTP.sendFile(archFilePath, archFolder, host, username, password);
								if(FileTransferStatus.SUCCESS == status)
								{
									System.out.println(archFilePath + " Got sftp-ed successfully to folder "+archFolder);
								}
								else if(FileTransferStatus.FAILURE == status){
									System.out.println("Fail to ssftp  to  folder "+destFolder);
									JOptionPane.showMessageDialog(null, "ERROR: Could not ARCHIVE. Check your Information.");
								}


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


		/*
		 * 
		 * 
		 * ABOUT AS CRAZY AS THE PUBLISH BUTTON. THIS IS GOING TO:
		 * 
		 * 1. DOWNLOAD FILE FROM FTP SERVER
		 * 2. READ INFO FROM IT. **REMEMBER INFO IS ENCRYPTED**
		 * 3. CHECKS FOR KEY TO DECRYPT.
		 * 4. DECRYPTS THE INFO USING KEY.
		 * 5. DISPLAYS THE DECRYPTED INFORMATION.
		 * 
		 * 
		 */
		downloadButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						String iAddress = conInfo.getAddress();
						String iUsername = conInfo.getUsername();
						String iPassword = conInfo.getPassword();
						String iFolder = conInfo.getFolder();
						String host = iAddress;
						String username = iUsername;
						String password = iPassword;	
						String sftpFolder = ("/" + iFolder);

						String writeToLocalFolder = "";
						String nameOfFile = "upload.txt";
						try {
							int status = FTP.getFile(nameOfFile, sftpFolder, host, username, password, writeToLocalFolder);
							if(FileTransferStatus.SUCCESS == status){
								System.out.println(nameOfFile + " got downloaded successfully to  folder "+writeToLocalFolder);
							}
							else if(FileTransferStatus.FAILURE == status){
								System.out.println("Fail to download  to  folder "+writeToLocalFolder);
								JOptionPane.showMessageDialog(null, "ERROR: Could not download.");
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

							// INFORMATION FROM FILE **ENCRYPTED**
							String encContent = sb.toString();


							// CHECK FOR SESSION KEY
							if(key.equals("na"))
							{
								key = JOptionPane.showInputDialog("Enter Encryption Key");
							}

							BasicTextEncryptor textDecryptor = new BasicTextEncryptor();

							textDecryptor.setPassword(key);

							// DECRYPT THE INFORMATION
							try
							{
								String plainText = textDecryptor.decrypt(encContent);
								out.setOutput(plainText);
							}
							catch(EncryptionOperationNotPossibleException p)
							{
								JOptionPane.showMessageDialog(null, "Error: Could not decrypt. "
										+ "Perhaps your key is wrong?");
								System.out.println("ERROR: DECRYPTION ERROR::Most likely wrong key");
							}


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
