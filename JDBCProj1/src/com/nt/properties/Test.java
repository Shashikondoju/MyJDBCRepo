package com.nt.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Test {
	public static void main(String[] args) throws IOException {
			FileReader reader=new FileReader("info.properties");
			Properties pro=new Properties();
			pro.load(reader);
			
			System.out.println(pro);
			System.out.println(pro.getProperty("person.name"));
			System.out.println(pro.getProperty("person.age"));

			
			
			
			
	}
}
