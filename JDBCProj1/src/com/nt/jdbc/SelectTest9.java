package com.nt.jdbc;
//Delete query....
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest9 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			String city=null;
			if(sc!=null) {
				System.out.println("Enter Student address to delete");
				city=sc.next();//gives city
				city="'"+city+"'";//gives 'city' 
				
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sha", "sha");
				//create the jdbc statement
				if(con!=null) 
					st=con.createStatement();
				//prepare sql query
				String query="DELETE FROM STUDENT WHERE SADD="+city;
				//execute the query
				System.out.println(query);
				//send and execute query in Db s/w
				int count=0;
				if(st!=null)
					count=st.executeUpdate(query);

				if(count==0)
					System.out.println("no records found to delete");
				else
					System.out.println("deleted:: "+count);
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

