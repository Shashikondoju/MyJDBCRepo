package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest3_emp {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int empno=0;
			String ename=null, job=null;
			float sal=0.0f;
			if(sc!=null) {
				System.out.println("enter eno");
				empno=sc.nextInt();
				System.out.println("enter ename");
				ename=sc.next();
				System.out.println("enter job");
				job=sc.next();
				System.out.println("enter salary");
				sal=sc.nextFloat();
			}//if
			ename="'"+ename+"'";
			job="'"+job+"'";
			//load the class
			//get connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","sha","sha");
			//create statement
			if(con!=null)
				st=con.createStatement();
			String query="INSERT INTO EMP(EMPNO,ENAME,SAL,JOB)VALUES("+empno+","+ename+","+sal+","+job+")";
			
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
		//close jdbc connection
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}//main

}//class
