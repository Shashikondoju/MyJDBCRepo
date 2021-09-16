package com.nt.swingapp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Guibuilder_ScrollFrame {

	private static final String STUDENT_QUERY = "SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JFrame frame;
	private JTextField textField_1;
	private final JButton btnNewButton = new JButton("First");
	private JLabel sno;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guibuilder_ScrollFrame window = new Guibuilder_ScrollFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Guibuilder_ScrollFrame() {
		initialize();
		intializeJDBC();
	}

	private void intializeJDBC() {
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			ps=con.prepareStatement(STUDENT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=ps.executeQuery();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//jdbc connection

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("GuiBuilder_ScrollTest...window closing");
				try {
					if (rs!=null) {
						rs.close();
					}
				}
					catch (SQLException e2) {
						e2.printStackTrace();
					}
				try {
					if (ps!=null) {
						ps.close();
					}
				}
				catch (Exception e3) {
					e3.printStackTrace();
				}
				try {
					if (con!=null) {
						con.close();
					}
				}
				catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(201, 43, 127, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		btnNewButton.setBounds(20, 205, 90, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!rs.isLast()) {
						rs.first();
						textField_1.setText(rs.getString(1));
						textField_2.setText(rs.getString(2));
						textField_3.setText(rs.getString(3));
						textField_4.setText(rs.getString(4));
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		sno = new JLabel("sno");
		sno.setBounds(137, 45, 54, 19);
		frame.getContentPane().add(sno);
		
		textField_2 = new JTextField();
		textField_2.setBounds(201, 77, 127, 23);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(201, 111, 127, 23);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(201, 145, 127, 23);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNewLabel = new JLabel("sname");
		lblNewLabel.setBounds(137, 81, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Add");
		lblNewLabel_1.setBounds(137, 115, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Avg");
		lblNewLabel_2.setBounds(137, 149, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!rs.isLast()) {
						rs.next();
						textField_1.setText(rs.getString(1));
						textField_2.setText(rs.getString(2));
						textField_3.setText(rs.getString(3));
						textField_4.setText(rs.getString(4));
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(120, 205, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						rs.previous();
						textField_1.setText(rs.getString(1));
						textField_2.setText(rs.getString(2));
						textField_3.setText(rs.getString(3));
						textField_4.setText(rs.getString(4));
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(227, 205, 90, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						rs.last();
						textField_1.setText(rs.getString(1));
						textField_2.setText(rs.getString(2));
						textField_3.setText(rs.getString(3));
						textField_4.setText(rs.getString(4));
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_3.setBounds(327, 205, 90, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}
