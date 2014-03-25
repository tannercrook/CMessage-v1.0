package Messager;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class comFrame {
	
	
	static JFrame comFrame = new JFrame();
	static JTextArea chatScreen = new JTextArea();
	
	
	public void buildComFrame()
	{
		buildChatScreen();
		
		// BUILD THE FRAME
		comFrame.setSize(400,400);
		comFrame.setLayout(new BorderLayout());
		comFrame.setTitle("C-Com");
		comFrame.setVisible(true);
	}
	
	public void buildChatScreen()
	{
		// BUILD THE CHAT SCREEN
		
		
		// ADD TO FRAME
		comFrame.add(chatScreen, BorderLayout.CENTER);
		
	}
	

}
