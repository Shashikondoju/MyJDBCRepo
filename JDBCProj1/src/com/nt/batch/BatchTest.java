package com.nt.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

	public static void main(String[] args) {
		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
				Statement st=con.createStatement();
//				ResultSet rs=st.executeQuery("SELECT SNO FROM STUDENT")
				){
					st.addBatch("INSERT INTO STUDENT VALUES(1001,'ARAK','DALAS',23.1)");
					st.addBatch("UPDATE STUDENT SET AVG=AVG+10 WHERE AVG<40");
//					st.addBatch("DELETE FROM STUDENT WHERE SNO=200");
					int resut[]=st.executeBatch();
					int sum=0;
					for (int i = 0; i < resut.length; i++) {
						sum=sum+resut[i];
					}
					if (sum>0) {
						System.out.println("total effected rows are"+sum);				
					}
					else
						System.out.println("no rows are effected");
				}//try
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
	}//main
}//class
