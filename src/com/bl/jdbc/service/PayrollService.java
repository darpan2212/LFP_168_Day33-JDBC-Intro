package com.bl.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bl.jdbc.DbConnection;
import com.bl.jdbc.sql.SqlQueries;

public class PayrollService {

	Connection con;
	SqlQueries sql;

	public void resetConnection() {
		con = DbConnection.init().getConnection();
		sql = new SqlQueries();
	}

	public void getEmpData() {
		resetConnection();
		System.out.println("-------------------Employee Data-------------------------");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.SELECT_EMP_DATA);

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getDate(3) + "\t" + rs.getString(4));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSalarySt(int emp_id, double basic_pay) {
		resetConnection();
		try {
			con = DbConnection.init().getConnection();
			Statement st = con.createStatement();
			double deduction = basic_pay * 0.10;
			double taxable_pay = basic_pay - deduction;
			double tax = taxable_pay * 0.20;
			double net_pay = taxable_pay - tax;
			int isUpdated = st
					.executeUpdate("update payroll_tbl set basic_pay = "
							+ basic_pay + ", deduction = " + deduction
							+ ", taxable_pay = " + taxable_pay + "" + ", tax = "
							+ tax + ", net_pay = " + net_pay
							+ " where emp_id = " + emp_id);

			if (isUpdated == 1) {
				System.out.println("Data is updated successfully");
			} else {
				System.out.println(
						"Something went wrong while updating the data.");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateSalary(int emp_id, double basic_pay) {
		resetConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql.UPDATE_SALARY);
			ps.setDouble(1, basic_pay);
			double deduction = basic_pay * 0.10;
			ps.setDouble(2, deduction);
			double taxable_pay = basic_pay - deduction;
			ps.setDouble(3, taxable_pay);
			double tax = taxable_pay * 0.20;
			ps.setDouble(4, tax);
			double net_pay = taxable_pay - tax;
			ps.setDouble(5, net_pay);
			ps.setInt(6, emp_id);

			int isUpdated = ps.executeUpdate();

			if (isUpdated == 1) {
				System.out.println("Data is updated successfully");
				showPayrollData();
			} else {
				System.out.println(
						"Something went wrong while updating the data.");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showPayrollData() {
		resetConnection();
		System.out.println("-------------------Employee payroll data-----------------------");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.SELECT_EMP_PAYROLL_DATA);

			while (rs.next()) {
				System.out.print(rs.getInt("emp_id") + "\t");
				System.out.print(rs.getString("emp_name") + "\t");
				System.out.print(rs.getInt("payroll_id") + "\t");
				System.out.print(rs.getDouble("basic_pay") + "\t");
				System.out.print(rs.getDouble("deduction") + "\t");
				System.out.print(rs.getDouble("taxable_pay") + "\t");
				System.out.print(rs.getDouble("tax") + "\t");
				System.out.print(rs.getDouble("net_pay") + "\t");
				System.out.println();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getEmpDataByJoinDate(String startDate, String endDate) {
		resetConnection();
		System.out.println("---------------Employee data based on join date------------------");
		try {
			PreparedStatement ps = con.prepareStatement(sql.SELECT_EMP_DATA_BY_JOIN_DATE);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getInt("emp_id") + "\t");
				System.out.print(rs.getString("emp_name") + "\t");
				System.out.print(rs.getString("join_date") + "\t");
				System.out.print(rs.getString("gender") + "\t");
				System.out.println();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}