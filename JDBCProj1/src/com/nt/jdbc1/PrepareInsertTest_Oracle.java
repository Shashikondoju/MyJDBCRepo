package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;
//prepared statement insertion 
public class PrepareInsertTest_Oracle {
	
	private static final String CUS_INSERT_QUERY="INSERT INTO CUSTOMER VALUES(?,?,?,?,?)";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			int count=0;
			if (sc!=null) {
				System.out.println("Enter customer count");
				count=sc.nextInt();
			}
		
			//register jdbc driver
			//establish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			
			//create PreparedStatement object
			ps=con.prepareStatement(CUS_INSERT_QUERY);
			if (ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter customer Id.no");
					int id=sc.nextInt();
					System.out.println("Enter customer name");
					String cname=sc.next();
					System.out.println("Enter customer age");
					int cage=sc.nextInt();
					
					System.out.println("Enter customer salary");
					int sal=sc.nextInt();
					
					System.out.println("Enter customer Dept");
					String dept=sc.next();
				//set each student values to the pre compiled 
					ps.setInt(1, id); ps.setString(2, cname);
					ps.setInt(3, cage); ps.setInt(4, sal);
					ps.setString(5, dept);
					int result=ps.executeUpdate();
					if (result==0)
						System.out.println(i+"Customer details not inseted");
					else
						System.out.println(i+"Customer details are inserted");
				}//for
			}//if	
		}//try
		catch (SQLException e) {
				e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//closing the jdbc connections..
		finally {
		try {
			if (ps!=null) 
				ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con!=null) 
				con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (sc!=null) 
				sc.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		}//finally
	}//main
}//class
