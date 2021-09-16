package com.nt.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SPK_PostgreInsert {

	private static final String PRODUCT_INSERT_QUERY="INSERT INTO PRODUCTS2 VALUES(NEXTVAL('PID_SEQ'),?,?,?)";
	
	public static void main(String[] args) {
		try(
		Scanner sc=new Scanner(System.in))
		{
			int count=0;
			if (sc!=null) {
				System.out.println("Enter student count");
				count=sc.nextInt();
			}
			//register jdbc driver
			//establish the connection
			try (
					Connection con=DriverManager.getConnection("jdbc:postgresql:ntaj415db","postgres","Shivaprasad");
					PreparedStatement ps=con.prepareStatement(PRODUCT_INSERT_QUERY);
			){			
			if (ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
//					System.out.println("Enter student no");
//					int sno=sc.nextInt();
					System.out.println("Enter Product name");
					String pname=sc.next();
					System.out.println("Enter Product Price");
					float price=sc.nextFloat();
					System.out.println("Enter Products Quntity");
					float qunty=sc.nextFloat();
				//set each student values to the pre compiled 
//					ps.setInt(1, sno);
					ps.setString(1, pname);
					ps.setFloat(2, price); ps.setFloat(3, qunty);
					int result=ps.executeUpdate();
					if (result==0)
						System.out.println(i+"details not inseted");
					else
						System.out.println(i+"details are inserted");
				}//for
			}//if
		}//try2
	}//try1
		catch (SQLException e) {
				e.printStackTrace();
			}
		catch (Exception e2) {
			e2.printStackTrace();
		}

	}
}
