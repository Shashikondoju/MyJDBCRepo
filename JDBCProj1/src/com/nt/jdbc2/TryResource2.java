

package com.nt.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//prepared statement insertion 
public class TryResource2 {
	
	private static final String QUERY="INSERT INTO STUDENT VALUES(SNO_SEQ1.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) {
		
		try( 
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
		PreparedStatement ps=con.prepareStatement(QUERY);
		Scanner sc=new Scanner(System.in);
				)
		{
			if (sc!=null) {
				System.out.println("enter name");
				String sname=sc.next();
				System.out.println("enter address");
				String sadd=sc.next();
				System.out.println("enter avg");
				float avg=sc.nextFloat();
				ps.setString(1, sname);
				ps.setString(2, sadd);
				ps.setFloat(3, avg);
				int rs=ps.executeUpdate();
				if (rs==0) {
					System.out.println("Try again");
				}
				else
				System.out.println("inserted sucessfully");
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
