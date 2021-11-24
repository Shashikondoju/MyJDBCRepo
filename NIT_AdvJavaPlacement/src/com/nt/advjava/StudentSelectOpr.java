package com.nt.advjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentSelectOpr {

	public static void main(String[] args) {
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			Statement st=con.createStatement();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter student Sno...");
			int sno=sc.nextInt();
			ResultSet rs=st.executeQuery("SELECT * FROM STUDENTMARKS WHERE SID="+sno);
			while (rs.next()) {
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
			}
		} catch (SQLException e) {
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
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
