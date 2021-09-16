package com.nt.jdbc.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class dateTest {
	public static void main(String[] args) throws ParseException {
		String da="11-jul-97";
		String da1="1997-11-07";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		java.util.Date ud=sdf.parse(da);
		long ms=ud.getTime();
		
		java.sql.Date sd=new java.sql.Date(ms);
		
		System.out.println("util date: "+ud);
		System.out.println("sql date: "+sd);
		
		String da2="1771-5-23";
		java.sql.Date sd2=java.sql.Date.valueOf(da2);
		System.out.println(sd2);
		
		java.sql.Date te=java.sql.Date.valueOf(da1);
		System.out.println(te);
		
//		java.sql.Date sqdob="11-jul-77";
		String sdob=null;
		java.util.Date d=new java.util.Date("11-jul-21");
		SimpleDateFormat sdf2=new SimpleDateFormat("dd-MMM-yy");
		 sdob=sdf2.format(d);
		 System.out.println(sdob);
	}
}
