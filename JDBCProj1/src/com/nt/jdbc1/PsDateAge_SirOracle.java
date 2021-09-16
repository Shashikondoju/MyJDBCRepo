package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
//Age of the person based on given peson id from person_info_dates...
public class PsDateAge_SirOracle {
	
	private static final String AGE_CAL="SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_INFO_DATES WHERE PID=?";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		Scanner sc=null;
		Statement st=null;
		try {
			//read input
			sc=new Scanner(System.in);
			int pid=0;
			String sdob=null;
			if (sc!=null) {
				System.out.println("enter pid");
				 pid=sc.nextInt();
			}
			//load the jdbc class establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			
			//create PreparedStatement object
			ps=con.prepareStatement(AGE_CAL);
			//set values to to the query
			if (ps!=null) 
				ps.setInt(1, pid);
			//execute query
			if (ps!=null) 
			rs=ps.executeQuery();
			//process the resultset object
			boolean flag1=false;
			if (rs!=null) {
				if (rs.next()) {
					flag1=true;					
					float age=rs.getFloat(1);
					System.out.println("date of bith of given pid: "+age);
				}//while
					 if (flag1==false) {
						 System.out.println("reult not found");
					 }//if
			}//if
			
		}//try
		
		catch (SQLException se) {
				se.printStackTrace();
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		//closing jdbc connections
		finally {
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
			
		}//finally
	}//main
}//class
