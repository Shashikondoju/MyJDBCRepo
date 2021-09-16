package com.nt.jdbc2;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*CREATE TABLE `ntaj415db`.`jobseeker_info` (
		  `JSID` INT NOT NULL AUTO_INCREMENT,
		  `JSNAME` VARCHAR(45) NULL,
		  `JSADD` VARCHAR(45) NULL,
		  `JSRESUME` LONGTEXT NULL,
		  PRIMARY KEY (`JSID`),
		  UNIQUE INDEX `JSID_UNIQUE` (`JSID` ASC) VISIBLE);
*/
public class ClobInsertTest_Mysql {
private final static String INSERT_QUERY="INSERT INTO JOBSEEKER_INFO(JSNAME, JSADD, JSRESUME) VALUES(?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			String name=null, add=null, resuloc=null;
			if (sc!=null) {
				System.out.println("Enter job seeker name");
				name=sc.next();
				System.out.println("Enter job seeker address");
				 add=sc.next();
				System.out.println("enter job seeker resume localtion");
				resuloc=sc.next().replace("?", "");
			}//if
			//create InputStream pointing to resume file
			try(Reader reader=new FileReader(resuloc)){
				//establish the connection
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","Shivaprasad");
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
