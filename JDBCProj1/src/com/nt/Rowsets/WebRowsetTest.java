package com.nt.Rowsets;

import java.io.FileOutputStream;
import java.sql.SQLException;

import ids.net.OutputStream;
import oracle.jdbc.rowset.OracleJDBCRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowsetTest {

	public static void main(String[] args)  {
		try(OracleWebRowSet wrowset=new OracleWebRowSet())
			{
				wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
				wrowset.setUsername("sha");
				wrowset.setPassword("sha");
				wrowset.setCommand("SELECT * FROM STUDENT");
				wrowset.execute();
				
				while (wrowset.next()) {
					System.out.println(wrowset.getString(1)+" "+wrowset.getString(2)+" "+wrowset.getString(3)+" "+wrowset.getString(4));
				}
				
				System.out.println("========================");
				//write Db table records as xml content to file
				FileOutputStream os=new FileOutputStream("Student.xml");
				wrowset.writeXml(os);
				System.out.println("======================");
				wrowset.writeXml(System.out);
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
