package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//java app for update student avg in given range..
import java.util.Scanner;
public class psLoginTest_Type2_OCI {

	private static final String LOGIN_QUERY="SELECT COUNT(*)FROM IRTC_TAB WHERE PWD=? AND UNAME=?";
	
	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;		
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String userName=null, pwd=null;
			
			if (sc!=null) {
				System.out.println("Enter user name");
				userName= sc.nextLine();
				System.out.println("Enter password");
				pwd= sc.nextLine();
			}//if
			
			con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","sha","sha");
			if(con!=null)
				ps=con.prepareStatement(LOGIN_QUERY);
			if (ps!=null) {
				ps.setString(1, pwd);
				ps.setString(2, userName);
			}
			if(ps!=null)
				rs=ps.executeQuery();
			if (rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				if (count==0) 
					System.out.println("Invalid credentials");
				else
					System.out.println("Valid credentials");
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
				if(ps!=null)
					ps.close();
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
	}//main
}//class
