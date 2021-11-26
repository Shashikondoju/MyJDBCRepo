package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {

	public static void main(String[] args) {
		//creating connection
		Connection con=null;
		try {
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sys as sysdba","shi");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from student where sno>=1 and sno<=10");
		//gathering the result values..
		while (rs.next()) {
			System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
			}
		}//try
		catch (SQLException e) {
			e.printStackTrace();
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
				catch (SQLException e) {
					e.printStackTrace();
				}
			}//finally		
		}//main
}//class
