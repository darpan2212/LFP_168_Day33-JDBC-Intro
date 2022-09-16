package com.bl.jdbc.service;

public class PayrollServiceImpl {

	public static void main(String[] args) {

		int emp_id = 2;
		double basic_pay = 3500000;

		PayrollService service = new PayrollService();

		service.getEmpData();

		service.updateSalary(emp_id, basic_pay);

		service.getEmpDataByJoinDate("2018-01-01", "2018-12-31");
	}

}