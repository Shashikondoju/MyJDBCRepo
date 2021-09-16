package com.nt.jdbc;
//SelecctTest6
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {

	public static void main(String[] args) {
		//load the jdbc driver class
		//Class.forName("oracle.jdbc.driver.OracleDriver")
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//create the jdbc connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sha", "sha");
			//create the jdbc statement
			if(con!=null) 
				st=con.createStatement();
			//prepare sql query
			String query="SELECT COUNT(*)FROM STUDENT";
			//execute the query
			if(st!=null)
				rs=st.executeQuery(query);
			if (rs!=null) {
				rs.next();
				int count=rs.getInt("COUNT(*)");
				System.out.println("No of fields in student table is:: "+count);
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
		}//finally
			
		}//main
	}//class


