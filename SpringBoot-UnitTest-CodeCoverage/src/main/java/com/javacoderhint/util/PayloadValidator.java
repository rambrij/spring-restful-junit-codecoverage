package com.javacoderhint.util;

import com.javacoderhint.model.Employee;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(Employee emp){
		if (emp.getId() > 0){
			return false;
		}
		return true;
	}

}
