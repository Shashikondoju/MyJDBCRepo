package com.nt.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqlTest1 {
	private static final String MYSQL_INSERT_STUDENT="INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String ORACLE_SELECT_STUDENT="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	
	public static void main(String[] args) {

		Connection oraCon=null, mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
	//		Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("com.nt.cj.jdbc.Driver");
			
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","Shivaprasad");
			
			if (oraCon!=null) 
				st=oraCon.createStatement();
			
			if (mysqlCon!=null) 
				ps=mysqlCon.prepareStatement(MYSQL_INSERT_STUDENT);
				
			if (st!=null)
				rs=st.executeQuery(ORACLE_SELECT_STUDENT);
				
			if (rs!=null && ps!=null) {
				while (rs.next()) {

					int sno=rs.getInt(1);
					String sname=rs.getString(2);
					String sadd=rs.getString(3);
					float avg=rs.getFloat(4);
					ps.setInt(1, sno);
					ps.setString(2, sname);
					ps.setString(3, sadd);
					ps.setFloat(4, avg); 	
					ps.executeUpdate();
				}//while
				System.out.println("Records are copied from oracle to mysql DB");
			}//if	
		}//try
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Records are not copied from oracle to mysql DB");
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs!=null) 
					rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
			try {
				if (st!=null) 
					st.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
			
			try {
				if (ps!=null) 
					ps.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
			
			try {
				if (oraCon!=null) 
					oraCon.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
			
			try {
				if (mysqlCon!=null) 
					mysqlCon.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e2) {
				e2.printStackTrace();
				}
		
		}//finally
	}//main
}//class
