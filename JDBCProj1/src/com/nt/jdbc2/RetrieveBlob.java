package com.nt.jdbc2;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetrieveBlob {
 private static String QUERY="SELECT * FROM ARTIST_INFO_TABLE WHERE AID=?"; 

	public static void main(String[] args) {
		try(
				Scanner sc=new Scanner(System.in);
				
				){
					int aid=0;
					if (sc!=null) {
						System.out.println("Enter artist id");
						 aid=sc.nextInt();
					}
					try(
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","sha","sha");
							PreparedStatement ps=con.prepareStatement(QUERY);		
							){
								//set values to param
								if (ps!=null)
									ps.setInt(1, aid);
								
								try(
										ResultSet rs=ps.executeQuery()
										){
											if (rs!=null) {
												if (rs.next()) {
													aid=rs.getInt(1);
													String name=rs.getString(2);
													String add=rs.getString(3);
													System.out.println(aid+" "+name+" "+add);
											 		//get inputStream pointing to blob col.value
													try(InputStream is=rs.getBinaryStream(4);
															//create output stream pointing to destination file
															OutputStream os=new FileOutputStream("my_img.jpg");
															){
																//copy the blob col value to destination file
														IOUtils.copy(is,os);
														System.out.println("BLOB values are retrieved and stored in the file");
													}//try4
												}//if
												else
													System.out.println("Records not found");
											}//if
									
								}//try3
						
					}//try2
			
		}//try1
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
