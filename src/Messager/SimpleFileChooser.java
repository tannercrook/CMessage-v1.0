package Messager;

//SimpleFileChooser.java
//A simple file chooser to see what it takes to make one of these work.
//
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

public class SimpleFileChooser extends JFrame {
	
	static OutScreen out = new OutScreen();

public SimpleFileChooser() {
 super("File Chooser Test Frame");
 setSize(350, 200);
 setDefaultCloseOperation(EXIT_ON_CLOSE);

 Container c = getContentPane();
 c.setLayout(new FlowLayout());
 
 JButton openButton = new JButton("Open");
 JButton saveButton = new JButton("Save");
 JButton dirButton = new JButton("Pick Dir");
 JButton doneButton = new JButton("Done");
 final JLabel statusbar = 
              new JLabel("Output of your selection will go here");

 // Create a file chooser that opens up as an Open dialog
 openButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     chooser.setMultiSelectionEnabled(true);
     int option = chooser.showOpenDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       File[] sf = chooser.getSelectedFiles();
       String filelist = "nothing";
       if (sf.length > 0) filelist = sf[0].getName();
       for (int i = 1; i < sf.length; i++) {
         filelist += ", " + sf[i].getName();
       }
       statusbar.setText("You chose " + filelist);
       
       
    // READ IN THE FILE
       // Open downloaded File

   		try {
   			BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
   			StringBuilder sb = new StringBuilder();
   			String line = br.readLine();

   			while (line != null) {
   				sb.append(line);
   				sb.append("\n");
   				line = br.readLine();
   			}

   			// INFORMATION FROM FILE
   			String content = sb.toString();
   			out.setOutput(content);


   		} catch (IOException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} finally {
   			BufferedReader br = null;
   			try {
   				br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
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
     else {
       statusbar.setText("You canceled.");
     }
   }
 });

 // Create a file chooser that opens up as a Save dialog
 saveButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     int option = chooser.showSaveDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       statusbar.setText("You saved " + ((chooser.getSelectedFile()!=null)?
                         chooser.getSelectedFile().getName():"nothing"));
       
try {
       
    // UPLOADER FILE

		// CONTENT
		String content = out.getOutput();
		
		if(!chooser.getSelectedFile().exists())
		{
			System.out.println("Creating New File Called: " + chooser.getSelectedFile().getName());
			chooser.getSelectedFile().createNewFile();
		}

		

		// UPLOAD FILE
		FileWriter fw = new FileWriter(chooser.getSelectedFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();

		System.out.println("Done writing file...");



	} catch (IOException e2) {
		e2.printStackTrace();
	}
       
    
       
       
     }
     else {
       statusbar.setText("You canceled.");
     }
   }
 });

 // Create a file chooser that allows you to pick a directory
 // rather than a file
 dirButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     int option = chooser.showOpenDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       statusbar.setText("You opened " + ((chooser.getSelectedFile()!=null)?
                         chooser.getSelectedFile().getName():"nothing"));
       
     }
       
     else {
       statusbar.setText("You canceled.");
     }
   }
 });
 
 doneButton.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent ae) {
		 setVisible(false);
	 }
 });

 c.add(openButton);
 c.add(saveButton);
 //c.add(dirButton);
 c.add(doneButton);
 c.add(statusbar, BorderLayout.SOUTH);
 
}

}