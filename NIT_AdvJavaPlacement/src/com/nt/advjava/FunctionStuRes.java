package com.nt.advjava;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class FunctionStuRes {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("student rno");
		int sid=sc.nextInt();
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			CallableStatement cs=con.prepareCall("{call ?:=RetrieveStudentResult77(?)}");
			cs.registerOutParameter(1, Types.FLOAT);
			cs.setInt(2, sid);
			cs.execute();
			System.out.println("employee total marks::"+cs.getFloat(1));
		
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
