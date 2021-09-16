package com.nt.jdbc23;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
CREATE OR REPLACE PROCEDURE P_STUDENT_SNO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDRS OUT VARCHAR2 
, AVG OUT NUMBER 
) AS 
BEGIN
  SELECT SNAME,SADD,AVG INTO NAME,ADDRS,AVG FROM STUDENT WHERE SNO=NO;
  
END;
 */
public class CsProcedureTest3 {

	private static final String CALL_PROCEDURE="{CALL P_STUDENT_SNO(?,?,?,?)}";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("enter STUDENT NO");
			int sno=sc.nextInt();
			//establish the jdbc connection
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					
					//create callablestatement object having the query call PL/SQL procedure as a pre compiled query
					
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
					){
							if (cs!=null) 
								cs.registerOutParameter(2, Types.VARCHAR);
							cs.registerOutParameter(3, Types.VARCHAR);
							cs.registerOutParameter(4, Types.FLOAT);
							if (cs!=null) {
								cs.setInt(1, sno);
							}
							//excute/call pl/sql function
							if (cs!=null) 
								cs.execute();
							String sname=null;
							String add=null;
							float avg=0.0f;
							if (cs!=null)
								sname=cs.getString(2);
							add=cs.getString(3);
							avg=cs.getFloat(4);
							System.out.println("name:"+sname+" "+"address:"+add+"  "+"avg: "+avg);
			}//try2
			
		}//try1
		catch (SQLException e) {
			System.out.println("enter again");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
	}//main
}//class
