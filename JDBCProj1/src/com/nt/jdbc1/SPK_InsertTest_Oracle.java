package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Scanner;

public class SPK_InsertTest_Oracle {
	
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(SNO_SEQ1.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			int count=0;
			if (sc!=null) {
				System.out.println("Enter student count");
				count=sc.nextInt();
			}
		
			//register jdbc driver
			//establish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			
			//create PreparedStatement object
			ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			if (ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
//					System.out.println("Enter student no");
//					int sno=sc.nextInt();
					System.out.println("Enter student name");
					String sname=sc.next();
					System.out.println("Enter student address");
					String sadd=sc.next();
					System.out.println("Enter student avg");
					float avg=sc.nextFloat();
				//set each student values to the pre compiled 
//					ps.setInt(1, sno);
					ps.setString(1, sname);
					ps.setString(2, sadd); ps.setFloat(3, avg);
					int result=ps.executeUpdate();
					if (result==0)
						System.out.println(i+"Student details not inseted");
					else
						System.out.println(i+"student details are inserted");
				}//for
			}//if
			
			
		}//try
		catch (Exception e) {
				e.printStackTrace();
		}
	}

}
