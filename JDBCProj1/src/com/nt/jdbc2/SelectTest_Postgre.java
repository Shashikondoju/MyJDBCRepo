package com.nt.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_Postgre {

	public static void main(String[] args) {
		/*try(	//load jdbc class
				Class.forName("org.postgresql.Driver")
				){
			
		}//try1
*/
		try(
				Connection con=DriverManager.getConnection("jdbc:postgresql:ntaj415db","postgres", "Shivaprasad");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT PID, PNAME,PRICE, QUNTY FROM  Products2");
				){
					if (rs!=null) {
						boolean flag=false;
						while(rs.next()) {
							flag=true;
//							System.out.println(""+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getShort(5));
							System.out.println(""+rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
						}
						if (flag==false) 
							System.out.println("no records found");
					}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
