package com.nt.Rowsets;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowsetTest {

	public static void main(String[] args)  {
		try(OracleJDBCRowSet jrowset=new OracleJDBCRowSet())
			{
				jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
				jrowset.setUsername("sha");
				jrowset.setPassword("sha");
				jrowset.setCommand("SELECT * FROM STUDENT");
				jrowset.execute();
				
				while (jrowset.next()) {
					System.out.println(jrowset.getString(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3)+" "+jrowset.getString(4));
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
