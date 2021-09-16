package com.nt.properties;

	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

	//prepared statement insertion 
	public class Jdbc {		
		private static final String QUERY="SELECT * FROM STUDENT";
		
		public static void main(String[] args) {
			Properties pro=null;
			try(InputStream is=new FileInputStream("src/com/nt/commons/jdbcinfo.properties")					
					){
				
				pro=new Properties();
				pro.load(is);
				
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try( 
			Connection con=DriverManager.getConnection(pro.getProperty("jdbc.url"), pro.getProperty("jdbc.username"), pro.getProperty("jdbc.pwd"));
			Statement ps=con.createStatement();
			ResultSet rs=ps.executeQuery(QUERY);
					)
			{
				if (rs!=null) {
					boolean flag=false;
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getString(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));
					}
					if (flag==false) {
						System.out.println("not found");
					}
				}
			}//block
			//Automstically final closing the jdbc connections..
			catch (SQLException e) {
					e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//main
	}//class
