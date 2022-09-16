package com.bl.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DbConnection {

	private static DbConnection dbConnection;

	private Connection con;

	private DbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			listDrivers();
			System.out.println("Connection established successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println(
					"Driver class could not find, please add the mysql-connector.jar file.");
			e.printStackTrace();
		}
	}

	public static DbConnection init() {
		if (dbConnection == null)
			dbConnection = new DbConnection();
		return dbConnection;
	}

	public Connection getConnection() {
		String jdbcStr = "jdbc:mysql://localhost:3306/payroll_service";
		String userName = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(jdbcStr, userName, password);
		} catch (SQLException e) {
			System.out.println(
					"Database connection failed, check your configurations.");
			e.printStackTrace();
		}
		return con;
	}

	private void listDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver d = drivers.nextElement();
			System.out.println(d.getClass().getName());
		}
	}

}