package com.nt.jdbc;
//SelecctTest8  nth highest salary..

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest8 {

	public static void main(String[] args) {
		//load the jdbc driver class
		//Class.forName("oracle.jdbc.driver.OracleDriver")
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int n=0;
		
		try {
			sc=new Scanner(System.in);
			System.out.println("Enter salary order");
			n=sc.nextInt();
			//create the jdbc connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sha", "sha");
			//create the jdbc statement
			if(con!=null) 
				st=con.createStatement();
			//prepare sql query select * from(select empno, ename ,sal, dense_rank()over(order by sal desc)r from emp)where r=3;
			String query1="SELECT * FROM(SELECT EMPNO, ENAME ,SAL, DENSE_RANK()OVER(ORDER BY SAL DESC)R FROM EMP)WHERE R="+n;
			//execute the query
			if(st!=null)
				rs=st.executeQuery(query1);
			if (rs!=null) {
				System.out.println(n+":order of employee salary details are:: ");
				boolean flag=false;
				while(rs.next()!=false) {
					flag=true;
					System.out.println("  "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getInt(4) );
				}//while
				
				if (flag==false) {
					System.out.println("No records found");
				}//if
				
			}//if
		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
			
		}//main
	}//class


