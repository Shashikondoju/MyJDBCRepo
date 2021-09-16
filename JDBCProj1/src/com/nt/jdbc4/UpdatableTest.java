package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableTest {

	private static final String QUERY="SELECT SNO, SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st.executeQuery(QUERY);
			){
				if (rs!=null) {
					System.out.println("Rs records top to bottom: ");
					int count=0;
					while(rs.next()) {
						if (count==0)
//							Thread.sleep(30000);
//						rs.refreshRow();
						count++;
						System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}
					//to insert
					/*
					 * rs.moveToInsertRow(); rs.updateInt(1, 200); rs.updateString(2, "Maria");
					 * rs.updateString(2, "colombo"); rs.updateFloat(4, 77f); rs.insertRow();
					 * System.out.println("Record inserted");
					 */
					
					//to update
					
					
					/*
					 * rs.absolute(10); rs.updateFloat(4, 55f); rs.updateRow();
					 * 
					 * System.out.println("Record updated..");
					 */
					
					
					//to delete
					rs.absolute(10);
					rs.deleteRow();
					System.out.println("record deleted");
					
				}//if
			}//try 
			catch (SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}//main
}//class
