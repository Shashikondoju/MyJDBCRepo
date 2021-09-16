package com.nt.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactioMgmt {
	/*
	 * CREATE TABLE "SHA"."JDBC_ACCOUNTS" ( "ACNO" NUMBER NOT NULL ENABLE,
	 * "HOLDERNAME" VARCHAR2(20 BYTE), "BAL" FLOAT(126) )
	 */

	public static void main(String[] args) {
		long srcAcno=0, destAcno=0;
		double bal=0.0;
		try(
				Scanner sc=new Scanner(System.in)
				){
				if (sc!=null) {
					System.out.println("Enter SrcAc/no");
					srcAcno=sc.nextLong();
					System.out.println("Enter DestAc/no");
					destAcno=sc.nextLong();
					System.out.println("Enter withdrawal amount");
					bal=sc.nextDouble();
				}
		}//try
		catch (Exception e) {
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sha","sha");
				Statement st=con.createStatement();
				){
			if (con!=null) {
				con.setAutoCommit(false);
			}
			if (st!=null) {
				st.addBatch("UPDATE JDBC_ACCOUNTS SET BAL=BAL-"+bal+"WHERE ACNO="+srcAcno);				
				st.addBatch("UPDATE JDBC_ACCOUNTS SET BAL=BAL+"+bal+"WHERE ACNO="+destAcno);	
				int result[]=st.executeBatch();
				boolean flag=true;
				for (int i = 0; i < result.length; i++) {
					if (result[i]==0) {
						flag=false;
						break;
					}
				}
				if (flag==true) {
					con.commit();
					System.out.println("money transferred TxMgmt commited successfully");
				}
				else {
					con.rollback();
					System.out.println("TxMgmt rollbacked...money not transferred");
				}
					
			}//if
			
		}//try2
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
