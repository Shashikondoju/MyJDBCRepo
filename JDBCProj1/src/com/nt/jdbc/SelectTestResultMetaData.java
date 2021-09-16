package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectTestResultMetaData {
//using type5 jdbc connection for oracle  database......
	private static final String QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
	
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(QUERY);
				){
			//process the ResultSet object
		ResultSetMetaData rsmd=null;
			if (rs!=null) {
				rsmd=rs.getMetaData();
			}
			if (rsmd!=null) {
				int colCount=rsmd.getColumnCount();
				for (int i = 1; i <=colCount; i++) {
					System.out.print(rsmd.getColumnName(i)+"  ");
				}
					System.out.println();
					for (int i = 1; i <=colCount; i++) {
					System.out.print(rsmd.getColumnTypeName(i)+"  ");
				}
					System.out.println();
			}
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
