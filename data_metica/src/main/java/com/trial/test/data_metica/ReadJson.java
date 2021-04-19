package com.trial.test.data_metica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

	public static void main(String[] args)
			throws SQLException, ClassNotFoundException, FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub

		Employee[] obj = new Employee[10];
		String username="";
		String password="";
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Please enter the username and passord for your database");
		System.out.println("Please enter the Username:-");
		username=sc.nextLine();
		System.out.println("Please enter the Password:-");
		password=sc.nextLine();
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Please enter your existing database name to connect");
		String database=sc.nextLine();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, username,password);
		Statement stmt = con.createStatement();

		// creating database
		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS employee");
		stmt.execute("use employee");

		// creating table
		String table_creation = "CREATE TABLE IF NOT EXISTS info" + "(name VARCHAR(255)," + "age int,"
				+ "city VARCHAR(255))";
		stmt.executeUpdate(table_creation);

		// for inserting rows
		PreparedStatement stmet = con.prepareStatement("insert into info (name,age,city) values(?,?,?)");
		System.out.println("Please enter the File directory");
		String directory=sc.nextLine();
		File dir = new File(directory);
		File[] directoryListing = dir.listFiles();

		int i = 0, count = 0;
		if (directoryListing != null) {
			for (File child : directoryListing) {
				String s = child.getName().trim();
				String str[] = s.split("\\.");

				if (str.length > 1 && str[1].equals("json")) {
					Object obj1 = new JSONParser().parse(new FileReader(directory + s));
					JSONObject jo = (JSONObject) obj1;
					String Name = (String) jo.get("Name");
					Long Age = (Long) jo.get("Age");
					String City = (String) jo.get("City");
					stmet.setString(1, Name);
					stmet.setLong(2, Age);
					stmet.setString(3, City);
					count += stmet.executeUpdate();

					Employee emp = new Employee(Name, Age, City);
					obj[i++] = emp;

				}

			}

		}

		System.out.println( count+ " Record  added succfully" );
		con.close();

	}

}
