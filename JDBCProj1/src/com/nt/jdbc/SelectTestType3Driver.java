package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestType3Driver {
//using type3 jdbc connection with msaccess database......
	private static final String QUERY="SELECT SNO,SNAME,ADD,AVG FROM COLLEAGE";
	public static void main(String[] args) {
		try {
			//Load jdbc driver classs 
			Class.forName("ids.sql.IDSDriver");
		}//try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:ids://localhost:12/conn?dsn=accdsn");
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
