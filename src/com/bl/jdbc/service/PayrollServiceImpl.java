package com.bl.jdbc.service;

public class PayrollServiceImpl {

	public static void main(String[] args) {
		
		int emp_id = 4;
		double basic_pay = 1500000;
		
		PayrollService service = new PayrollService();
		
		service.getEmpData();
		
		service.updateSalarySt(emp_id, basic_pay);
		
	}
	
}