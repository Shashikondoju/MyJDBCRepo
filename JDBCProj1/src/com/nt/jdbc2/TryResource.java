

package com.nt.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//prepared statement insertion 
public class TryResource {
	
	private static final String QUERY="SELECT * FROM STUDENT";
	
	public static void main(String[] args) {
		
		try( 
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
		Statement ps=con.createStatement();
		ResultSet rs=ps.executeQuery(QUERY);
				)
		{
			if (rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getString(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));
				}
				if (flag==false) {
					System.out.println("not found");
				}
			}
		}//block
		//Automstically final closing the jdbc connections..
		catch (SQLException e) {
				e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
