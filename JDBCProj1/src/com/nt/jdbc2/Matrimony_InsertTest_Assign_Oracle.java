package com.nt.jdbc2;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE TABLE "SHA"."VSB_MARTIMONY" 
(	"CID" NUMBER NOT NULL ENABLE, 
	"CNAME" VARCHAR2(20 BYTE), 
	"GENDER" VARCHAR2(5 BYTE), 
	"DOB" DATE NOT NULL ENABLE, 
	"PHOTO" BLOB, 
	"RESUME" BLOB, 
	"DOJ_FIRSTJOB" DATE, 
	"BIODATA" CLOB, 
	"AUDIO_INFO" BLOB, 
	"VIDEO_INFO" BLOB
);
*/
public class Matrimony_InsertTest_Assign_Oracle {
	
	private static final String INSERT_QUERY="INSERT INTO VSB_MARTIMONY  VALUES(CID_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc =new Scanner(System.in)
				)
		//read the input values
		{
			String cname=null;
				String gender=null;
				String dateof_birth=null;
				String pholoc=null;
				String resume_loc=null;
				String dateof_job=null;
				String bio_loc=null;
				String audio_loc=null;
				String video_loc=null;
				
				if (sc!=null) {
					System.out.println("enter name");
					cname=sc.nextLine();
					System.out.println("enter gender");
					gender=sc.nextLine();
					System.out.println("enter date of birth");
					dateof_birth=sc.nextLine();
					System.out.println("enter photo_location");
					pholoc=sc.nextLine().replace("?","");
					System.out.println("enter resume_location");
					resume_loc=sc.nextLine().replace("?","");
					System.out.println("enter date of joining");
					dateof_job=sc.nextLine();
					System.out.println("enter Biodata_location");
					bio_loc=sc.nextLine().replace("?","");
					System.out.println("enter Audio_location");
					audio_loc=sc.nextLine().replace("?","");
					System.out.println("enter video_location");
					video_loc=sc.nextLine().replace("?","");
				}//if
				//convert the String date value to sql date format..
				java.sql.Date sqdob=java.sql.Date.valueOf(dateof_birth);
				java.sql.Date sqdoj=java.sql.Date.valueOf(dateof_job);
				
				//create inputStream pointing to file location
				try(
						InputStream is1=new FileInputStream(pholoc);
						InputStream is2=new FileInputStream(resume_loc);
						Reader reader=new FileReader(bio_loc);
						InputStream is4=new FileInputStream(audio_loc);
						InputStream is5=new FileInputStream(video_loc);
						
						){
					//establish the connection and prepareStatement object 
					try(
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
							PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
							){
							//set values to the prepared statement
								if (ps!=null) {
									ps.setString(1, cname);
									ps.setString(2, gender);
									ps.setDate(3, sqdob);
									ps.setBinaryStream(4, is1);
									ps.setBinaryStream(5, is2);
									ps.setDate(6, sqdoj);
									ps.setCharacterStream(7, reader);;
									ps.setBinaryStream(8, is4);
									ps.setBinaryStream(9, is5);
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
