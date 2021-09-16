package com.nt.jdbc23;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT VARCHAR2 
) AS 
BEGIN
  SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
END;
 */
public class CsProcedureTest2 {

	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("enter EMP ID");
			int emp_id=sc.nextInt();
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
								cs.setInt(1, emp_id);
							}
							//excute/call pl/sql function
							if (cs!=null) 
								cs.execute();
							String name=null;
							String desg=null;
							float sal=0.0f;
							if (cs!=null)
								name=cs.getString(2);
							desg=cs.getString(3);
							sal=cs.getFloat(4);
							System.out.println("name:"+name+" "+"job:"+desg+"  "+"sal: "+sal);
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
