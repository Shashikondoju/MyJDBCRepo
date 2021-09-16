package com.nt.Rowsets;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class CachedRowsetTest {

	public static void main(String[] args)  {
		try(OracleJDBCRowSet crowset=new OracleJDBCRowSet();
				OracleJDBCRowSet crowset2=new OracleJDBCRowSet();
				OracleJoinRowSet joinRowset=new OracleJoinRowSet();
				)
			{
				crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
				crowset.setUsername("sha");
				crowset.setPassword("sha");
				crowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
				crowset.execute();
				
				crowset2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
				crowset2.setUsername("sha");
				crowset2.setPassword("sha");
				crowset2.setCommand("SELECT DEPTNO,DNAME,LOC  FROM DEPT");
				crowset2.execute();
				
				//add multiple cached rowset to joinrowset
				joinRowset.addRowSet(crowset2);
				joinRowset.addRowSet(crowset);
				
				while (joinRowset.next()) {
					System.out.println(joinRowset.getInt(1)+" "+joinRowset.getString(2)+" "+joinRowset.getString(3)+" "+joinRowset.getString(4)+" "+joinRowset.getString(5)+" "+joinRowset.getString(6)+" "+joinRowset.getString(7));
				}
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
