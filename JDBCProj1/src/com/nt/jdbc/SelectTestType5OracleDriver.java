package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestType5OracleDriver {
//using type5 jdbc connection for oracle  database......
	private static final String QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try {
			//Load jdbc driver classs 
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		}//try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=xe","sha","sha");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(QUERY);
				){
			//process the ResultSet object
			if (rs!=null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getString(4));
				}//while
			}//if
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
