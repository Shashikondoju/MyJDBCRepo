package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
//Age of the person based on given peson id from person_info_dates...
public class PsDateAge_Oracle {
	
	private static final String SELECT="SELECT DOB FROM PERSON_INFO_DATES WHERE PID=?";
	
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
			ps=con.prepareStatement(SELECT);
			//set values to to the query
			if (ps!=null) 
				ps.setInt(1, pid);
			//execute query
			if (ps!=null) 
			rs=ps.executeQuery();
			//process the resultset object
			boolean flag1=false;
			if (rs!=null) {
				while (rs.next()) {
					flag1=true;
					java.sql.Date sqdob=rs.getDate(1);
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
					 sdob=sdf.format(sqdob);
					 System.out.println("date of bith of given pid: "+sdob);
					 java.util.Date sudob =new java.util.Date();
					 float age=(sudob.getTime()-sqdob.getTime())/(1000*60.0f*60.0f*24.0f*365.25f);
					 DecimalFormat df=new DecimalFormat("#.##");
					 System.out.println("person sge:"+df.format(age));
				}//while
					 if (flag1==false) {
						 System.out.println("reult not found");
					 }//if
			}//if

			
			String query="SELECT ROUND((SYSDATE - TO_DATE("+"'"+sdob+"'"+"))/365.25, 5) AS AGE from DUAL";

			System.out.println("Query is:"+query);
			if (con!=null) {
				st=con.createStatement();
				if (st!=null) 
					rs2=st.executeQuery(query);
				boolean flag2 = false;
				if (rs2!=null) 
					while (rs2.next()) {
						flag2=true;
						System.out.println("the age of given pid is:  "+rs2.getString(1));
					}
				if (flag2==false) {
					System.out.println("result not found");
				}
					
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
