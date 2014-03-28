package Messager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;


/*
 *  EXPERIMENTAL!!!!!! PLEASE DO NOT ALTER ANYTHING IN THIS FILE. 
 *  
 *  THIS IS THE CODE THAT WILL USE A DATABASE TO CONNECT TO A CHAT.
 */



public class ChatFrame {
	
	
	public void buildChatFrame()
	{
		JFrame chatFrame = new JFrame();
		
		JButton go = new JButton();
		
		chatFrame.add(go);
		
		// ADD LISTENER HERE
		
		
		
		
		chatFrame.setTitle("Chat");
		chatFrame.setVisible(true);
	}
	
	public void connectToAndQueryDatabase(String username, String password) {

		Connection con;
		try {
			con = DriverManager.getConnection(
					"jdbc:myDriver:myDatabase",
					username,
					password);


			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT time, name, message FROM chat");

			while (rs.next()) {
				Timestamp time = rs.getTimestamp("time");
				String name = rs.getString("name");
				String message = rs.getString("message");
				System.out.println(time + " " + name + " " + message);
			}
		} catch (SQLException e) {
			System.out.println("ERROR::Database Connection Error");
			e.printStackTrace();
		}




	}
}
