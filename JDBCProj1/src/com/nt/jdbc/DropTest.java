package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if (con!=null)
				st=con.createStatement();
			
			String query="Drop table temp_student";
			int count=0;
			if (st!=null)
				count=st.executeUpdate(query);
			if (count==0)
				System.out.println("table not droped");
			else
				System.out.println("table droped");
				
		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (st!=null)
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
	}//main

}//class
