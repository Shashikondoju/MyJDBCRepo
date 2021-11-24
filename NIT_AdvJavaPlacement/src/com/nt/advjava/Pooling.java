package com.nt.advjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class Pooling {
	String url,user,pass;
	
	public Pooling(String url,String user,String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	Connection con=null;
	public void createConnection() {
		try {
			con=DriverManager.getConnection(url,user,pass);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
//		return con; 
	}
	Vector v=new Vector<>();
	int i=0;
	
	public Connection useConnection() {
		while (i<5) {
			try {
				con=DriverManager.getConnection(url,user,pass);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (i==5) {
				System.out.println("pool is full");
			}
			else {
			System.out.println("Pool is not full");
			v.add(con);
			System.out.println(v.elementAt(i));
			i++;
			}
		}
		System.out.println(v);
		return  (Connection) v.remove(0);
	}
	public void ReturnConnection(Connection c) {
		v.add(c);
	}
	
}
