package com.nt.jdbc2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class blobInsertMysql_Test {
	
	private static final String INSERT_QUERY="INSERT INTO ARTIST_INFO_TABLE(ANAME,AADD,PIC) VALUES(?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc =new Scanner(System.in)
				)
		//read the input values
		{
			String sname=null;
				String add=null; 
				String pholoc=null;
				
				if (sc!=null) {
					System.out.println("enter name");
					sname=sc.nextLine();
					System.out.println("enter add");
					add=sc.nextLine();
					System.out.println("enter photo_location");
					pholoc=sc.nextLine().replace("?", "");
				}//if
				//create inputStream pointing to photo location
				try(InputStream is=new FileInputStream(pholoc)){
					//establish the connection and prepareStatement object 
					try(
							Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","Shivaprasad");
							PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
							){
								if (ps!=null) {
									ps.setString(1, sname);
									ps.setString(2, add);
					
									ps.setBinaryStream(3, is);
								}
								int count=0;
								if (ps!=null) 
									count=ps.executeUpdate();
								
								//process the result
								
								if (count==0) {
									System.out.println("records not inserted");
								}
								else
									System.out.println("records are inserted");
						
					}//try3
				}//try2
		}//tr1
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//,main
}//class
