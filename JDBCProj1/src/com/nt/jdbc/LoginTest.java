package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//java app for update student avg in given range..
import java.util.Scanner;
public class LoginTest {
//problem in this app....
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;		
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String userName=null, pwd=null;
			if (sc!=null) {
			System.out.println("Enter user name");
			userName= sc.nextLine();
			System.out.println("Enter password");
			 pwd= sc.nextLine();
			userName="'"+userName+"'";
			pwd="'"+pwd+"'";
			//}//if
			//establish the connection type 4 so no need of load class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if(con!=null)
				st=con.createStatement();
			//prepare query..
			//select count(*)from IRTC_TAB where uname='shashi' and pwd='shiva';
			String query="select count(*)from IRTC_TAB where uname="+userName +" and pwd="+pwd;
			
			System.out.println(query);
	//send and execute query
			int count=0;
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultset object
			if (rs!=null) {
				rs.next();//moves the cursor BFR to ALR
				count=rs.getInt(1);
				if (count==0)
					System.out.println("records not found");
				else
					System.out.println("records found "+count);
				}//if
			}//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid given table column");
			else
				se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
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
