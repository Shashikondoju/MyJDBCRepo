package com.nt.jdbc;
//update student details
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest_mysql {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		String newName=null, newCity=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int sno=0;
			float newAvg=0.0f;
			if(sc!=null) {
				System.out.println("enter name");
				newName=sc.next();
				System.out.println("enter address");
				newCity=sc.next();
				System.out.println("enter average");
				newAvg=sc.nextFloat();
				
				System.out.println("Enter Student no");
				sno=sc.nextInt();//gives student number
				
				newName="'"+newName+"'";
				newCity="'"+newCity+"'";
				
				
				con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","Shivaprasad");
				//create the jdbc statement
				if(con!=null) 
					st=con.createStatement();
				//prepare sql query
				//update student set sno=3, sname='pandu', avg= 12000 where sno=5;
				String query="update student set  sname="+newName+", sadd="+newCity+", avg="+newAvg+" where sno="+sno;
				//execute the query
				System.out.println(query);
				//send and execute query in Db s/w
				int count=0;
				if(st!=null)
					count=st.executeUpdate(query);

				if(count==0)
					System.out.println("no records found to update");
				else
					System.out.println("Updated :: "+count);
				}//if
			}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("invalid col names or table names or sql keyword");
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
	}

}
