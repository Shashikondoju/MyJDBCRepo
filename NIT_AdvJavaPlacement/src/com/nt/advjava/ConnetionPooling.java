package com.nt.advjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnetionPooling {
public static void main(String[] args) {
		String user="sha";
		String pass="shi";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		Pooling p=new Pooling(url, user, pass);
		System.out.println(p.v.size());
		//p.createConnection();
		Connection con=null;
		try {
			con=p.useConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from t1");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Size of ::"+p.v.size());
		p.ReturnConnection(con);
		System.out.println("Size of ::"+p.v.size());
	}
}
