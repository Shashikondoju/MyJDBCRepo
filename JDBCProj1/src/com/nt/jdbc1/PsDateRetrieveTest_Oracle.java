package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetrieveTest_Oracle {
	
	private static final String SELECT="SELECT * FROM PERSON_INFO_DATES";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	//	Scanner sc=null;
		try {
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			
			//create PreparedStatement object
			ps=con.prepareStatement(SELECT);
			if (ps!=null) 
			rs=ps.executeQuery();
			
			if (rs!=null) {
				while (rs.next()) {
//					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"   "+rs.getString(4)+"    "+rs.getString(5));
					
					int pid=rs.getInt(1);
					String name=rs.getString(2);
					java.sql.Date sqdob=rs.getDate(3);
					java.sql.Date sqdoj=rs.getDate(4);
					java.sql.Date sqdom=rs.getDate(5);
					
					//convert java.sql.Date class objs to String date values					
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					String sdob=sdf.format(sqdob);
					String sdoj=sdf.format(sqdoj);
					String sdom=sdf.format(sqdom);
					
					System.out.println(pid+"  "+name+"  "+sdob+"  "+sdoj+"  "+sdom);
					
				}//while
			}//if
		}//try
		catch (SQLException se) {
				se.printStackTrace();
		}
		catch (Exception e) {
				e.printStackTrace();
		}
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
				if (rs!=null) 
					rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
