package com.nt.advjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOpr {
	/* CREATE TABLE "SHA"."BOOKVENDOR" 
	   (	"BCODE" NUMBER NOT NULL ENABLE, 
		"BNAME" VARCHAR2(20 BYTE), 
		"BAUTHOR" VARCHAR2(20 BYTE), 
		"BPRICE" FLOAT(126), 
		"BQNTY" NUMBER
	   )*/
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Book name");
		String name=sc.next();
		System.out.println("Enter Book Author name");
		String author=sc.next();
		System.out.println("Enter Book Price::");
		float price=sc.nextFloat();
		System.out.println("Quantity of books::");
		int num=sc.nextInt();
		
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			PreparedStatement ps=con.prepareStatement("INSERT INTO BOOKVENDOR VALUES(SNO_SEQ.NEXTVAL,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setFloat(3, price);
			ps.setInt(4, num);
			int result=ps.executeUpdate();
			if (result>0) {
				System.out.println("Values inserted");
			}
			else
				System.out.println("plz check again");
		}//try
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
				try {
					if (con!=null) 
					con.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
