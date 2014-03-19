package Messager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

import Messager.MenuBar;
import Messager.OutScreen;



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
 * ======================   CFRAME.JAVA   ===================================
 * 
 * AUTHOR(S): TANNER CROOK, 2014
 * 
 * THIS FILE CONTAINS THE MAIN METHOD AND THE CLASS FOR THE FRAME.
 * 
 */


// THIS IS THE CLASS THAT CREATES THE GUI


public class CFrame extends JFrame {
	
	// Main GUI Components
	static CFrame frame = new CFrame();
	static MenuBar menuBar = new MenuBar();
	static OutScreen outScreen = new OutScreen();
	static ButtonPanel buttonPanel = new ButtonPanel();
	

	
	public static void main(String[] args) {
		
		// Build Frame
		frame.buildFrame();
		
		// Build Menu
		menuBar.buildBar();
		frame.setJMenuBar(menuBar);
		
		// Build Output Panel
		outScreen.buildScreen();
		frame.add(outScreen.getScrollPane(), BorderLayout.CENTER);
		
		// Build Button Panel
		buttonPanel.buildButtonPanel();
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		

		
		
	}
	
	void buildFrame()
	{
		setTitle("CMessager");
		setLayout(new BorderLayout());
		frame.setJMenuBar(menuBar);
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
