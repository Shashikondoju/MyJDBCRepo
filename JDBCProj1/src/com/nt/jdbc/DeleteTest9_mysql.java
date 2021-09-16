package com.nt.jdbc;
//Delete query....
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest9_mysql {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			int Sno=0;
			if(sc!=null) {
				System.out.println("Enter Student address to delete");
				Sno=sc.nextInt();//gives sno
				
				con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","Shivaprasad");
				//create the jdbc statement
				if(con!=null) 
					st=con.createStatement();
				//prepare sql query
				String query="DELETE FROM STUDENT WHERE SNO="+Sno;
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
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("problem in sql table coulmn or");
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

