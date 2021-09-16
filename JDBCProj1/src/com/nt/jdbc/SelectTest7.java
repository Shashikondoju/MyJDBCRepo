package com.nt.jdbc;
//SelecctTest7 maximum salaried person in employee..
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {

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
			String query1="SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			//execute the query
			if(st!=null)
				rs=st.executeQuery(query1);
			if (rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println("Maximum salried employe details are:  "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) );
				}//while
				
				if (flag==false) {
					System.out.println("No records found");
				}//if
				
			}//if
		//}//try
		
		String query2="SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
		//execute the query
		if(st!=null)
			rs=st.executeQuery(query2);
		if (rs!=null) {
			boolean flag=false;
			while(rs.next()) {
				flag=true;
				System.out.println("Minimum salried employe details are:  "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) );
			}//while
			
			if (flag==false) {
				System.out.println("No records found");
			}//if
			
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


