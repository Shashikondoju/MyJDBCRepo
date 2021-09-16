package com.nt.jdbc23;
import java.sql.CallableStatement;
import java.sql.Connection;
/*CREATE OR REPLACE PROCEDURE P_AUTHENTICATE 
(
  USERNAME IN VARCHAR2 
, PASSWORD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
 CNT NUMBER(5);
BEGIN
  SELECT COUNT(*)INTO CNT FROM IRTC_TAB WHERE UNAME=USERNAME AND PWD=PASSWORD;
  IF(CNT<>0)THEN
    RESULT:='VALID CREDENTIALS';
  ELSE
    RESULT:='INVALID CREDENTIALS';
    END IF;
END P_AUTHENTICATE;*/
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProceduteTest4_Login {
	private final static String QUERY="{CALL P_AUTHENTICATE(?,?,?)}";
	public static void main(String[] args) {
		//read input
		try(Scanner sc=new Scanner(System.in)	){
			String user=null, pwd=null;
			if (sc!=null) {
				System.out.println("enter user name");
				user=sc.next();
				System.out.println("enter password");
				pwd=sc.next();
			}//if
			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					CallableStatement cs=con.prepareCall(QUERY);)
					{
							if (cs!=null) 
								cs.registerOutParameter(3, Types.VARCHAR);
							
							//set the values to in param
							cs.setString(1, user);
							cs.setString(2, pwd);
							
							if (cs!=null) 
								cs.execute();
							
							String result=null;
							if (cs!=null) {
						
								result=cs.getString(3);
								System.out.println(result);
							}
							
					}//try2
		}//try1
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}//main
}//class
