package com.nt.advjava;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class AssigmentonCallableStatement {
	
	/*create or replace procedure CreateEmp77
	(eid number,ename varchar2,edesig varchar2,ebsal float,etsal float,email varchar2,embl number,pic blob,hno varchar2,stname varchar2,city varchar2,pincode number) as
	begin
	insert into empdetails77 values(eid,ename,edesig,ebsal,etsal);
	insert into emp_contact77 values(eid,email,embl,pic);
	insert into empadd77 values(eid,hno,stname,city,pincode);
	end;
*/
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("employee id::");
		int eid=sc.nextInt();
		System.out.println("employee name:");
		String ename=sc.next();
		System.out.println("employee desig::");
		String edesig=sc.next();
		System.out.println("enter emp basic salary::");
		float bsal=sc.nextFloat();
		float totsal = bsal+(0.93F*bsal)+(0.63f*bsal);
		System.out.println("enter emp email id::");
		String email=sc.next();
		System.out.println("enter emp mbl no");
		long mbl=sc.nextLong();
		System.out.println("enter photo_location");
		String pholoc=sc.next().replace("?","");
		InputStream is=null;
		System.out.println("enter emp h.no");
		String hno=sc.next();
		System.out.println("enter street name::");
		String stname=sc.next();
		System.out.println("emp city name");
		String city=sc.next();
		System.out.println("enter pincode::");
		int pincode=sc.nextInt();
		Connection con=null;
		try {
			is=new FileInputStream(pholoc);
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			CallableStatement cs=con.prepareCall("{call createEmp77(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, eid);
			cs.setString(2, ename);
			cs.setString(3, edesig);
			cs.setFloat(4, bsal);
			cs.setFloat(5, totsal);
			cs.setString(6, email);
			cs.setLong(7, mbl);
			cs.setBinaryStream(8, is);
			cs.setString(9, hno);
			cs.setString(10, stname);
			cs.setString(11, city);
			cs.setInt(12, pincode);
			cs.execute();
			System.out.println("Procedure executed success fully");
			
		} //try
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (con!=null && is!=null) {
					con.close();
					is.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
	}//main
}//class
