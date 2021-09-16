package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest_mysqlStudent {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			String sname=null, sadd=null;
			float avg=0.0f;
			int sno=0;
			if (sc!=null) {
				System.out.println("enter student sno");
				sno=sc.nextInt();
				System.out.println("enter student sname");
				sname=sc.next();
				System.out.println("enter student sadd");
				sadd=sc.next();
				System.out.println("enter student avg");
				avg=sc.nextFloat();
			}//if
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","Shivaprasad");
			 //create statement	
			 
			 if(con!=null)
				 st=con.createStatement();
			 
			 //prepare sql query
			 //select sno,sadd,sname from student where sname like 's%';
			 
			 String query="insert into student values("+sno+","+sname+","+sadd+","+avg+")";
			 System.out.println(query);
			 //send and execute
			 
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 //process the Result
			 if(count==0)
				 System.out.println("Records not inserted");
			 else
				 System.out.println("inserted sucessfully ");

		}//try
		
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("Duplicated can not inserted to PK column");
			if(se.getErrorCode()==1400)
				System.out.println("Null can not inserted to PK column");
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or table names");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try{
				if (st!=null)
					st.close();
				}
			catch (SQLException se){
				se.printStackTrace();
			}

			try{
				if (con!=null)
					con.close();
			}
			catch (SQLException se){
				se.printStackTrace();
			}

			try{
				if (sc!=null)
					sc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
	}

}
