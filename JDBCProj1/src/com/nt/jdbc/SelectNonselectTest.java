package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonselectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			sc=new Scanner(System.in);
			int no=0;
			String query=null;
			//read query
			if(sc!=null) {
				System.out.println("Enter query");
			query=sc.nextLine();
			}//if
			//load the driver class
			//establish Connection.. 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			//create statement 
			if (con!=null) 
				st=con.createStatement();
			//send and execute sql query
			if (st!=null) {
				boolean flag=st.execute(query);
				System.out.println(flag);
				if(flag==true) {
					System.out.println("Select SQL Query executed ");
					//gather and process the result set obj
					rs=st.getResultSet();
					if(rs!=null) {
						while(rs.next()) {
//							System.out.println(rs.getString(1));
							System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
						}//while
					}//if
				}//if
				else {
					System.out.println("Non Select SQL query executed");
					//gather the result
					int count=st.getUpdateCount();
					System.out.println("no.of records that are effected is::"+count);
				}//else
				
			}//if
				
		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}//finally
		
		
		
		
	}//main

}//class
