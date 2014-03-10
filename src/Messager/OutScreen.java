package Messager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
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
 * ======================   OUTSCREEN.JAVA   ===================================
 * 
 * AUTHOR(S): TANNER CROOK, 2014
 * 
 * THIS FILE CONTAINS THE MAIN METHOD AND THE CLASS FOR THE AREA WITH SCREEN.
 * 
 */

public class OutScreen{
	static JTextArea outString = new JTextArea();
	static JScrollPane scrollPane = new JScrollPane(outString);

	
	
	public void buildScreen()
	{
		outString.setLineWrap( true );
	    outString.setWrapStyleWord( true );
	    //outString.setEditable(false);
	    
	    //scrollPane.setPreferredSize(new Dimension(450, 50));
	    scrollPane.setPreferredSize(new Dimension(200, 100));

	}
	
	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}
	
	public void setOutput(String toOutput)
	{
		outString.setText(toOutput);
	}
	
	public String getOutput()
	{
		return outString.getText();
	}

}
