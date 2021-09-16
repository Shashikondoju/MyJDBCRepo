package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollablePsTest2 {

	private static final String QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					PreparedStatement st=con.prepareStatement(QUERY,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st.executeQuery();
			){
				if (rs!=null) {
					System.out.println("Rs records top to bottom: ");
					while(rs.next()) {
						System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}
					System.out.println("------------------------------next()------------------------------");
					
					System.out.println("bottom to top....");
//					rs.afterLast();
//					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
//					System.out.println("------------------------------afterLast()------------------------------");
//				
					while(rs.previous()) {
						System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}
					
					System.out.println("------------------------------previous()------------------------------");

					rs.first();
					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------first()------------------------------");
				
					
					rs.last();
					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------last()------------------------------");
					
					rs.absolute(3);
					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------absolute(3)------------------------------");
				
   					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------absolute(-7)------------------------------");
					
					rs.relative(3);
					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------relaticve(3)------------------------------");
					
					rs.relative(-3);
					System.out.println(rs.getRow()+"===>"+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					System.out.println("-----------------------------relaticve(-3)------------------------------");
					
				}
			}//try 
			catch (SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}//main
}//class
