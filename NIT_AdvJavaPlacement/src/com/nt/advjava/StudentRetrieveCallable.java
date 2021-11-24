package com.nt.advjava;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class StudentRetrieveCallable {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter employee id");
		int id=sc.nextInt();
		
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","shi");
			CallableStatement cs=con.prepareCall("{call retrieveemp77(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, id);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.FLOAT);
			cs.registerOutParameter(5, Types.FLOAT);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.registerOutParameter(8, Types.BLOB);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.registerOutParameter(10, Types.VARCHAR);
			cs.registerOutParameter(11, Types.VARCHAR);
			cs.registerOutParameter(12, Types.INTEGER);
			cs.execute();
			System.out.println("emp id"+id);
			System.out.println(cs.getString(2));
			System.out.println(cs.getString(3));
			System.out.println(cs.getFloat(4));
			System.out.println(cs.getFloat(5));
			System.out.println(cs.getString(6));
			System.out.println(cs.getInt(7));
			System.out.println(cs.getString(9));
			System.out.println(cs.getString(10));
			System.out.println(cs.getString(11));
			System.out.println(cs.getInt(12));
			
		}//try
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
	}//main
}//classs
