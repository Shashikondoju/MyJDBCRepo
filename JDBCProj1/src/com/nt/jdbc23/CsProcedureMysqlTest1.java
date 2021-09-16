package com.nt.jdbc23;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE DEFINER=`root`@`localhost` PROCEDURE `P_STUDENTVALUES`(IN SNO INT, OUT SNAME varchar(10) )
BEGIN
	SELECT SNAME FROM STUDENT WHERE SNO=SNO;
END
*/
public class CsProcedureMysqlTest1 {

	private static final String CALL_PROCEDURE="{CALL P_STUDENTVALUES(?,?)}";
	public static void main(String[] args) {
		//read inputs
			//establish the jdbc connection
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("enter 1st no");
			int fst=sc.nextInt();
			
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","Shivaprasad");
					//create callablestatement object having the query call PL/SQL procedure as a pre compiled query
						CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
					){
							//excute/call pl/sql function
								if (cs!=null) 
									cs.registerOutParameter(2, java.sql.Types.VARCHAR);
								if (cs!=null) 
									cs.setInt(1, fst);
								
								if (cs!=null) 									
									cs.execute();
								
							String result=null;
							boolean flag=false;
							if (cs!=null) {
								flag=true;
								result=cs.getString(2);
								System.out.println("name is:: "+result);
							}
							if (flag!=true) {
								System.out.println("reults not found..");
							}
								
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
