package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateRetrieveByDateRange {

	private static final String SELECTRANGE="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=?";
		public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			String sdob=null, edob=null;
			if (sc!=null) {
				System.out.println("enter start range");
				sdob=sc.next();
				System.out.println("enter end range");
				edob=sc.next();
			} //if
			//convert String date format to sql date format
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			java.sql.Date sqdob=new java.sql.Date(sdf.parse(sdob).getTime());
			
			java.sql.Date eqdob=new java.sql.Date(sdf.parse(edob).getTime());
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if (con!=null) 
				ps=con.prepareStatement(SELECTRANGE);
			
			if (ps!=null) {
				ps.setDate(1, sqdob);
				ps.setDate(2, eqdob);
			}
			
			if (ps!=null) 
				rs=ps.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getDate(3)+"  "+rs.getString(4)+"  "+rs.getDate(5));
				}
			}
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (con!=null) {
						con.close();
				}
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			try {
				if (sc!=null) {
					sc.close();
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
			}
			
	}//main

}//class
