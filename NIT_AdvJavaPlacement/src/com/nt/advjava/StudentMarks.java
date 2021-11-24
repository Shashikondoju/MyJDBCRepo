package com.nt.advjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentMarks {
	
	/*CREATE TABLE "SHA"."STUDENTMARKS" 
	   (	"SID" NUMBER NOT NULL ENABLE, 
		"SNAME" VARCHAR2(20 BYTE), 
		"BRANCH" VARCHAR2(20 BYTE), 
		"TOTAL_MARKS" FLOAT(126), 
		"PERCENTAGE" FLOAT(126), 
		"RESULT" VARCHAR2(20 BYTE)
	   )*/
	public static void main(String[] args) {
		System.out.println("enter student name");
		Scanner sc =new Scanner(System.in);
		String sname=sc.next();
		System.out.println("Student branch::");
		String branch=sc.next();
		System.out.println("Enter Sudent Marks");
		float []marks=new float[5+1];
		for (int i = 0; i <marks.length; i++) {
			marks[i]=sc.nextFloat();
		}
		float res=0;
		for (int i = 0; i < marks.length; i++) {
			res=res+marks[i];
		}
		System.out.println("result is::"+res);
		float per=(res/600)*100;
		System.out.println("Percentage"+per);
		String status;
		if (per>=75.0) {
			status="Distinction";
		}
		else if (per<=74 && per>=60 ) {
		status="1st";
		}
		else if (per<=59 && per>=45) {
			status="2nd";	
		}
		else {
			status="avg";
		}
		//System.out.println(status);
		//========================================
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			PreparedStatement ps=con.prepareStatement("INSERT INTO STUDENTMARKS VALUES(SNO_SEQ.NEXTVAL,?,?,?,?,?)");
			ps.setString(1, sname);
			ps.setString(2, branch);
			ps.setFloat(3,res );
			ps.setFloat(4, per);
			ps.setString(5, status);
			
			int check=ps.executeUpdate();
			if (check>0) {
				System.out.println("Student details are Stored Successfully::");
			}
			else
				System.out.println("Not stored plz check again");
		}//try
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}//catch1
		catch (Exception e) {
			e.printStackTrace();
		}//catch2
		finally {
			try {
				if (con!=null) {
					con.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
