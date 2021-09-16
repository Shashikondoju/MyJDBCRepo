package com.nt.jdbc;
//alter rename the table name
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
			//load jdbc class and establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if (con!=null)
				st=con.createStatement();
			//prepare query send and excute the sql query
			String query="alter table temp_student rename to per_student";
			int count=0;
			if (st!=null)
				count=st.executeUpdate(query);
			if (count==0)
				System.out.println("table renamed");
			else
				System.out.println("table not renamed");
				
		}//try
		//catch the exception
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close the jdbc connections..
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
