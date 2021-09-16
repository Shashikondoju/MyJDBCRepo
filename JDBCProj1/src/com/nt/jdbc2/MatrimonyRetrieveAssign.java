package com.nt.jdbc2;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.output.WriterOutputStream;

public class MatrimonyRetrieveAssign {
	private final static String SELECT_QUERY="SELECT * FROM VSB_MARTIMONY WHERE CID=?";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)
				){
			int cid=0;
			if (sc!=null) {
				System.out.println("Enter CID");
				cid=sc.nextInt();
			}
			try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
					PreparedStatement ps=con.prepareStatement(SELECT_QUERY);
					){
						if (ps!=null) {
							ps.setInt(1, cid);
						}
						try(ResultSet rs=ps.executeQuery()){
							if (rs!=null) {
								if (rs.next()) {
									cid=rs.getInt(1);
									String name=rs.getString(2);
									String gender=rs.getString(3);
									java.sql.Date sdob=rs.getDate(4);
									java.sql.Date sdoj=rs.getDate(7);
									SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
									String dob=sdf.format(sdob);
									String doj=sdf.format(sdoj);
									System.out.println(name+" "+gender+" "+dob+" "+doj);
									try(
											InputStream is1=rs.getBinaryStream(5);
											InputStream is2=rs.getBinaryStream(6);
											InputStream is3=rs.getBinaryStream(10);
											InputStream is4=rs.getBinaryStream(9);
											Reader is5=rs.getCharacterStream(8);
										
											//create output stream pointing to destination file
											OutputStream os1=new FileOutputStream("retrieImg.jpg");
											OutputStream os2=new FileOutputStream("retrieResume.pdf");
											Writer os3=new FileWriter("retrievetxt");
											OutputStream os4=new FileOutputStream("retrieAudio.mp3");
											OutputStream os5=new FileOutputStream("retrieVideo.mp4");
										){
											//copy the blob col value to destination file
											IOUtils.copy(is1,os1);
											IOUtils.copy(is2,os2);
											IOUtils.copy(is3,os3);
											IOUtils.copy(is4,os4);
//											IOUtils.copy(is5,os5);
											System.out.println("BLOB values are retrieved and stored in the file");
								}//try4
							}//if
							else
								System.out.println("Records not found");
							}//if
						}//try3
			}//try2
		}//try1
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
		}	
	}//main
}//class
