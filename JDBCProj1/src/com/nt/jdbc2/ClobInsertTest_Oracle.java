package com.nt.jdbc2;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*CREATE TABLE "SHA"."JOBSEEKER_INFO" 
(	"JSID" NUMBER(10,0) NOT NULL ENABLE, 
	"JSNAME" VARCHAR2(20 BYTE), 
	"JSADDRS" VARCHAR2(20 BYTE), 
	"RESUME" CLOB
	   CREATE SEQUENCE  "SHA"."JSID_SEQ"  MINVALUE 1 MAXVALUE 100000 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
)*/

public class ClobInsertTest_Oracle {
private final static String INSERT_QUERY="INSERT INTO JOBSEEKER_INFO VALUES(JSID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			String name=null, add=null, resuloc=null;
			if (sc!=null) {
				System.out.println("Enter job seeker name");
				name=sc.next();
				System.out.println("Enter job seeker address");
				 add=sc.next();
				System.out.println("enter job seeker resume localtion");
				resuloc=sc.next();
			}//if
			//create InputStream pointing to resume file
			try(Reader reader=new FileReader(resuloc)){
				//establish the connection
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
						PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
						){
							if (ps!=null) {
								ps.setString(1, name);
								ps.setString(2, add);
								ps.setCharacterStream(3, reader);
							}
							//execute the query
							int count=0;
							if (ps!=null) 
								count=ps.executeUpdate();
							//process the results
							if (count==0) 
									System.out.println("records not inserted");
							else
								System.out.println("records are inserted");
				}//try3
			}//try2
			
		}//try1
		catch (Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
