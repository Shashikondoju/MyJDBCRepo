package com.nt.advjava;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentCallableAssignment {
	/*create or replace procedure CreateStudent77
	(rno number,name varchar2, branch varchar2, hno varchar2, sname varchar2, city varchar2, pincode number,mail varchar2,mbl number,totMarks float,per float,result varchar2)as
	begin
	insert into studetails77 values(rno,name,branch);
	insert into stuaddress77 values(rno,hno,sname,city,pincode);
	insert into stucontact77 values(rno,mail,mbl);
	insert into sturesults77 values(rno,totMarks,per,result);
	end;
	*/
	
	public static void main(String[] args) {
	
		System.out.println("enter student name");
		Scanner sc =new Scanner(System.in);
		String sname=sc.next();
		System.out.println("enter student rno");
		int sno=sc.nextInt();
		System.out.println("Student branch::");
		String branch=sc.next();
		
		System.out.println("enter address hno");
		String hno=sc.next();
		System.out.println("Street name");
		String stname=sc.next();
		System.out.println("City name");
		String city=sc.next();
		System.out.println("Pincode");
		int pincode=sc.nextInt();
		
		System.out.println("Enter mail id");
		String mail=sc.next();
		System.out.println("Enter mbl no");
		long mbl=sc.nextLong();
		
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
			CallableStatement ps=con.prepareCall("{call CreateStudent77(?,?,?,?,?,?,?,?,?,?,?,?)}");
			ps.setInt(1, sno);
			ps.setString(2, sname);
			ps.setString(3, branch);
			ps.setString(4, hno);
			ps.setString(5, stname);
			ps.setString(6, city);
			ps.setInt(7, pincode);
			ps.setString(8, mail);
			ps.setLong(9, mbl);
			ps.setFloat(10,res );
			ps.setFloat(11, per);
			ps.setString(12, status);
			
			ps.execute();
			
				System.out.println("Student details are Stored Successfully::");
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
