package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//java app for update student avg in given range..
import java.util.Scanner;
public class SelectTestUpdateAssignStudent {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;		
		try {
			//read inputs
			sc=new Scanner(System.in);
			int Percentage=0;
			if (sc!=null) {
			System.out.println("Enter Student name1");
			String Add1 = sc.next();
			System.out.println("Enter Student name2");
			String Add2 = sc.next();
			System.out.println("Enter increment percent");
			Percentage=sc.nextInt();
			Add1="'"+Add1+"'";
			Add2="'"+Add2+"'";
			//}//if
			//establish the connection type 4 so no need of load class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
			if(con!=null)
				st=con.createStatement();
			//prepare query..
			//update student set avg=avg+(avg*10/100) where sname in ('saisaketh','Vittu');
			
			String query="update student set avg=avg+(avg*"+Percentage+"/100) where sadd in ("+Add1+","+Add2+")";
	
			System.out.println(query);
	//send and execute query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if (count==0)
				System.out.println("no records found to update");
			
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
