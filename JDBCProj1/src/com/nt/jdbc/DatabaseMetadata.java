package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetadata {

	public static void main(String[] args) {
		try(
//				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha")
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","Shivaprasad")
				
				){
			DatabaseMetaData dbmd=con.getMetaData();
			if (dbmd!=null) {
				System.out.println("db s/w nmae: "+dbmd.getDatabaseProductName());
				System.out.println("db s/w version: "+dbmd.getDatabaseProductVersion());
				System.out.println("jdbc Drivername:"+dbmd.getDriverName());
				System.out.println("jdbc Driver version: "+dbmd.getDriverVersion());
				System.out.println("All SQLKeywords : "+dbmd.getSQLKeywords());
				System.out.println("All numeric function : "+dbmd.getNumericFunctions());
				System.out.println("All System function : "+dbmd.getSystemFunctions());
				System.out.println("All String function : "+dbmd.getStringFunctions());
				System.out.println("Max chars in table name : "+dbmd.getMaxTableNameLength());
				System.out.println("Max tables in select query : "+dbmd.getMaxTablesInSelect());
				System.out.println("Max row size : "+dbmd.getMaxRowSize());
				System.out.println("supports PL/SQL procedures  : "+dbmd.supportsStoredProcedures());
			}
		}//try
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
