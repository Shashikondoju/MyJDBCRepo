package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int sno=0;
			String sname=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("enter sno");
				sno=sc.nextInt();
				System.out.println("enter sname");
				sname=sc.next();
				System.out.println("enter avg");
				avg=sc.nextFloat();
			}//if
			sname="'"+sname+"'";
			//load the class
			//get connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","sha","sha");
			//create statement
			if(con!=null)
				st=con.createStatement();
			String query="insert into student(sno,sname,avg)values("+sno+","+sname+","+avg+")";
			
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("not found given table name details");
			else
				System.out.println("details updates");
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
