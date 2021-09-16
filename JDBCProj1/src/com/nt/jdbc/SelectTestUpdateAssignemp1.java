package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//java app for update employe salary in given range..
import java.util.Scanner;

public class SelectTestUpdateAssignemp1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;		
		try {
			//read inputs
			sc=new Scanner(System.in);
			float Sal1=0.0f;
			float Sal2=0.0f;
			int Hike=0;
			if (sc!=null) {
			System.out.println("Enter Salary range 1");
			Sal1=sc.nextFloat();
			System.out.println("Enter Salary range 2");
			Sal2=sc.nextFloat();
			System.out.println("Enter hike percent");
			Hike=sc.nextInt();
			//}//if
			//establish the connection type 4 so no need of load class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if(con!=null)
				st=con.createStatement();
			//prepare query..
			//update emp1 set salary=salary+(salary*10/100) where salary between 15000 and 22000
			
			String query="UPDATE EMP1 SET SALARY=SALARY+(SALARY*"+Hike+"/100) WHERE SALARY BETWEEN "+Sal1+"and "+Sal2;
	
			System.out.println(query);
	//send and execute query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if (count==0)
				System.out.println("no records found to update");
			//else
				System.out.println("updated fields in table is:  "+count);
			}//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid given table column");
			else
				se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
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
