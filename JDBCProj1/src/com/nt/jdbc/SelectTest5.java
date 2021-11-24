package com.nt.jdbc;

//java app to get student details based on initial names...

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest5 {

	public static void main(String[] args) {
		System.out.println("SelectTest5.main()");
		Scanner sc=null;
		String initChar=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter intial chacteras of employee name");
				initChar=sc.next();
			}
			initChar="'"+initChar+"%'";
			
			//register driver by loading jdbc driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			 //create statement	
			 
			 if(con!=null)
				 st=con.createStatement();
			 
			 //prepare sql query
			 //select sno,sadd,sname from student where sname like 's%';
			 
			 String query="SELECT SNO,SADD,SNAME FROM STUDENT WHERE SNAME LIKE"+initChar;
			 
			 //send and execute sql query database..
			 if(st!=null)
				 rs=st.executeQuery(query);
			 if(rs!=null){
				 boolean flag=false;
				 System.out.println("Query is: "+query);
				 while(rs.next()!=false) {
					 flag=true;
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					 
				 }//while
				 if(flag==false) {
					 System.out.println("no records found");
				 }
			 }//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or table names");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try
			{
				if (rs!=null)
					rs.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}

			try{
				if (st!=null)
					st.close();
				}
			catch (SQLException se){
				se.printStackTrace();
			}

			try{
				if (con!=null)
					con.close();
			}
			catch (SQLException se){
				se.printStackTrace();
			}

			try{
				if (sc!=null)
					sc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
