package com.bl.jdbc.service;

public class PayrollServiceImpl {

	public static void main(String[] args) {

		PayrollService service = new PayrollService();

		service.getEmpData();

		System.out.println("------------------------------------------");
		/*
		 * service.updateSalary(emp_id, basic_pay);
		 * 
		 * service.getEmpDataByJoinDate("2018-01-01", "2018-12-31");
		 */
		
		service.addEmpData();
		System.out.println("------------------------------------------");
		service.showEmpDeptPayrollData();
	}

}