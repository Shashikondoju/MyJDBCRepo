package com.nt.jdbc23;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure first_pro(x in number,y in number, z out number)as
begin
 z:=x+y;
end;*/
public class CsProcedureTest1 {

	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?)}";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("enter 1st no");
			int fst=sc.nextInt();
			System.out.println("enter 2nd no");
			int second=sc.nextInt();
			//establish the jdbc connection
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					//create callablestatement object having the query call PL/SQL procedure as a pre compiled query
						CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
					){
							if (cs!=null) 
								cs.registerOutParameter(3, Types.INTEGER);
							if (cs!=null) {
								cs.setInt(1, fst);
								cs.setInt(2, second);
							}
							//excute/call pl/sql function
							if (cs!=null) 
								cs.execute();
							int result=0;
							if (cs!=null)
								result=cs.getInt(3);
							System.out.println("sum is:: "+result);
			}//try2
			
		}//try1
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
	}//main
}//class
