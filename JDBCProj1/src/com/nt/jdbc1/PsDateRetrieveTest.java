package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsDateRetrieveTest {
	
	private static final String SELECT="SELECT * FROM PERSON_INFO_DATES";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	//	Scanner sc=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql:///ntaj415db", "root", "Shivaprasad");
			//create PreparedStatement object
			ps=con.prepareStatement(SELECT);
			if (ps!=null) 
			rs=ps.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				}//while
			}//if
		}//try
		catch (SQLException se) {
				se.printStackTrace();
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			try {
				if (ps!=null) 
					ps.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (con!=null) 
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs!=null) 
					rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
