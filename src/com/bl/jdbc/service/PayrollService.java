package com.bl.jdbc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bl.jdbc.DbConnection;

public class PayrollService {

	public static void main(String[] args) {

		Connection connection = DbConnection.getConnection();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from employee_tbl");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getDate(3) + "\t" + rs.getString(4));
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}