package com.bl.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DbConnection {

	public static Connection getConnection() {
		String jdbcStr = "jdbc:mysql://localhost:3306/payroll_service";
		String userName = "root";
		String password = "root";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			listDrivers();

			con = DriverManager.getConnection(jdbcStr, userName, password);

			System.out.println("Connection established successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println(
					"Driver class could not find, please add the mysql-connector.jar file.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(
					"Connection could not establish with mysql server, kindly check the configuration");
			e.printStackTrace();
		}
		return con;
	}

	private static void listDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver d = drivers.nextElement();
			System.out.println(d.getClass().getName());
		}
	}

}