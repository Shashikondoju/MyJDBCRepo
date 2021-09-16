package com.nt.jdbc23;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPS_BYNAME_INTIAL 
(
  INITIALCHAR IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR
) AS 
BEGIN
  OPEN DETAILS FOR
   SELECT EMPNO,ENAME,SAL,JOB,DEPTNO FROM EMP WHERE ENAME LIKE INITIALCHAR; 
END P_GET_EMPS_BYNAME_INTIAL;
*/public class CsProcedureLikeRs {
	
	private static final String QUERY="{CALL P_GET_EMPS_BYNAME_INTIAL(?,?) }";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)
				){
			String intia_name=null;
			if (sc!=null) {
				System.out.println("Enter Initial chars: ");
				intia_name=sc.next()+"%";
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					CallableStatement cs=con.prepareCall(QUERY);
					){
						if(cs!=null)
							cs.registerOutParameter(2, OracleTypes.CURSOR);
						
						cs.setString(1, intia_name);
						cs.execute();
						
						if (cs!=null) {
							ResultSet rs=(ResultSet)cs.getObject(2);
							boolean flag=false;
							while(rs.next()) {
								flag=true;
								System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
							}
							if(flag!=true)
								System.out.println("records not found with given index");		
					}//if
						
			}//try2
		}//try1
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//main
}//class
