package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
				//load jdbc driver class
				//establish the connection
			//gather the results from Db s/w
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
				//create the jdbc statement
				if(con!=null) 
					st=con.createStatement();
				String query="create table temp_student(sno number(5) primary key, sname varchar(15))";
				System.out.println(query);
				//send and execute sql query in Db s/w
				int count=0;
				if(st!=null)
					count=st.executeUpdate(query);
				System.out.println("Count is: "+count);
				//process the result
				if(count==0)
					System.out.println("Db table is created");
				else
					System.out.println("Db table is not created");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			//we can work with error codes
			if (se.getErrorCode()==955) {
				System.out.println("Db table is already created");
			}//if
		}//catch
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();	
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finallly
	}//main

}//classs
