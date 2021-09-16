package com.nt.swingapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginJFrame extends JFrame implements ActionListener {
	JLabel l1,l2,l3;
	 JTextField t1,t2,t3;
	JButton b1;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	String user_name;
	String pass;
	
	public LoginJFrame()  {
		System.out.println("LoginJFrame Constructor start");
		setTitle("Login swing application");
		setSize(500,700);
		setLayout(new FlowLayout());
		l1=new JLabel("UserName:");
		add(l1);
		t1=new JTextField(10);
		add(t1);
		l2=new JLabel("Password");
		add(l2);
		t2=new JTextField(15);
		add(t2);
		l3=new JLabel("Result");
		add(l3);
		t3=new JTextField(10);
		add(t3);
		b1=new JButton("Click");
		add(b1);
		b1.addActionListener(this);
		setVisible(true);
		System.out.println("constructor ends.....");
	}
//	String s1=t1.getText();
	private void initializeJDBC() {
		System.out.println("GUI Db: @JDBC.....");
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if(con!=null)
			st=con.createStatement();
			
			user_name="'"+user_name+"'";
			pass="'"+pass+"'";
			String query="select count(*)from IRTC_TAB where uname="+user_name +" and pwd="+pass;
			
			rs=st.executeQuery(query);
			System.out.println(query);
	//send and execute query
			int count=0;
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultset object
			if (rs!=null) {
				rs.next();//moves the cursor BFR to ALR
				count=rs.getInt(1);
				if (count==0)
					System.out.println("records not found");
				else
					System.out.println("records found "+count);
				}//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid given table column");
			else
				se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally


	}//@jdbc

	public static void main(String[] args) {
		new LoginJFrame();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		user_name=t1.getText();
		 pass=t2.getText();
		
		 initializeJDBC();

		 System.out.println("Action performed");
		System.out.println(user_name+" "+pass);
	
	}

}
