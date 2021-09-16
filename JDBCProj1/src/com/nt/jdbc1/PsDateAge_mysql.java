package com.nt.jdbc1;

////Age of the person based on given peson id from person_info_dates...

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsDateAge_mysql {

	private  final static String QUERY="select Pname, year(curdate())-year(dob)as age from person_info_dates where pid=?";
	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			int pid=0;
			if (sc!=null) {
				System.out.println("Enter person Id");
				pid=sc.nextInt();
			}//if
			
			//establish jdbc type 4 connection for mysql... 
			con=DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","Shivaprasad");
			if (con!=null) 
				ps=con.prepareStatement(QUERY);
			//	SET values to pre-compiled query..
			ps.setInt(1, pid);
			System.out.println(QUERY);
			if (ps!=null) 
				rs=ps.executeQuery();
			//gathering result set values
			boolean flag=false;
			if (rs!=null) {
				flag=true;
				while (rs.next()) {
						System.out.println(rs.getString(1)+"  "+rs.getString(2));						
					}
				}//while
			if (flag==false) {
				System.out.println("results not found");
			}//if
		}//try
	
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//closing jdbc connections
		
		finally {
			try {
				if (rs!=null) {
					rs.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (ps!=null) {
					ps.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (con!=null) {
					con.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (sc!=null) {
					rs.close();
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}//finally
		
	}//main

}//class
