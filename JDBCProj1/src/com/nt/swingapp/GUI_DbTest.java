package com.nt.swingapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GUI_DbTest extends JFrame implements ActionListener, WindowListener {
	JLabel lsno,lname,ladd,lavg;
	JTextField tsno,tname,tadd,tavg;
	JButton bFirst, bLast, bPrevious, bNext;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private static final String GET_STUDENT="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	
	public GUI_DbTest() {
		System.out.println("GUI_Db 0-constructor");
		setSize(500,500);
		setTitle("GUI DB Test..");
		setLayout(new FlowLayout());
		
		lsno=new JLabel("SNO");
		add(lsno);
		tsno=new JTextField(10);
		add(tsno);
		
		lname=new JLabel("SName");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		ladd=new JLabel("SAdd");
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		
		lavg=new JLabel("Avg");
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
		
		bFirst=new JButton("First");
		bLast=new JButton("Last");
		bPrevious=new JButton("Previous");
		bNext=new JButton("Next");
		
		add(bFirst);
		System.out.println();
		add(bLast);
		add(bPrevious);
		add(bNext);
		
		bFirst.addActionListener(this);
		bLast.addActionListener(this);
		bPrevious.addActionListener(this);
		bNext.addActionListener(this);
		
		this.addWindowListener(this);
		
		tsno.setEditable(false);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeJDBC();
		System.out.println("GUI_DbTest..Constructor end...");
	}
	
	public static void main(String[] args) {
		System.out.println("Main method start ");
		new GUI_DbTest();
		System.out.println("main method end");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUI-Db Action performed");
		boolean flag=false;
		if (ae.getSource()==bFirst) {
			try {
				rs.first();
				flag=true;
				System.out.println("First button clicked");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (ae.getSource()==bLast) {
			try {
				if (!rs.isLast()) {
					rs.last();
					flag=true;
					System.out.println("Last button clicked");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (ae.getSource()==bPrevious) {
			try {
				rs.previous();
				flag=true;
				System.out.println("Previous button clicked");
			} 
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
		else {
			try {
				rs.next();
				flag=true;
				System.out.println("Next button clicked");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (flag==true) {
			try {
				tsno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tadd.setText(rs.getString(3));
				tavg.setText(rs.getString(4));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}//@Action performer
	
	private void initializeJDBC() {
		System.out.println("GUI Db: @JDBC.....");
		
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			ps=con.prepareStatement(GET_STUDENT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs=ps.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
		}
	}//@jdbc

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUIDBFrame Closing()..");
		try {
			if (rs!=null) {
				rs.close();
			}
		}
		catch ( SQLException se) {
			se.printStackTrace();
		}
		try {
			if (ps!=null) {
				ps.close();
			}
		}
		catch ( SQLException se) {
			se.printStackTrace();
		}
		try {
			if (con!=null) {
				con.close();
				System.out.println("JDBC connections closing--");
			}
		}
		catch ( SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
