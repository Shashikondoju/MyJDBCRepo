package com.nt.jdbc.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsert_mysql {

	private static final String INSERT_DATE_QUERY="INSERT INTO  PERSON_INFO_DATES(PNAME, DOB, DOJ, DOM) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read input values..
			sc=new Scanner(System.in);
			String name=null, sdob=null, sdoj=null, sdom=null;
			if (sc!=null) {
				System.out.println("person name");
				name=sc.next();				
				System.out.println("person DOB dd-MM-yyyy::");
				sdob=sc.next();
				System.out.println("person DOJ yyyy-MM-dd::");
				sdoj=sc.next();
				System.out.println("person DOM dd-yyyy-MM");
				sdom=sc.next();
			}//if
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-YYYY");
			java.util.Date udob=sdf1.parse(sdob);
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			
			java.sql.Date sqdoj=java.sql.Date.valueOf(sdoj);
			
			SimpleDateFormat sdf2=new SimpleDateFormat("dd-yyyy-MM");
			java.util.Date udom=sdf2.parse(sdom);
			ms=udom.getTime();
			java.sql.Date sqdom=new java.sql.Date(ms);
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:mysql:///ntaj415db", "root", "Shivaprasad");
			
			if (con!=null) 
				ps=con.prepareStatement(INSERT_DATE_QUERY);
				
				if (ps!=null) {
					ps.setString(1, name);
					ps.setDate(2, sqdob);
					ps.setDate(3, sqdoj);
					ps.setDate(4, sqdom);
				}
				
				int count=0;
				if (ps!=null) 
					count=ps.executeUpdate();
				if (count==0) 
					System.out.println("records not inserted");
				else
					System.out.println("records inserted");
		
		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (con!=null) 
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (ps!=null) 
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
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