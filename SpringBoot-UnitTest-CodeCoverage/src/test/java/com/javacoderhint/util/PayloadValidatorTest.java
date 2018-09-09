package com.javacoderhint.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.javacoderhint.model.Employee;
import com.javacoderhint.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		Employee emp = new Employee(1, "Sample emp 1", 32);
		assertEquals(false, PayloadValidator.validateCreatePayload(emp));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		Employee emp = new Employee(0, "Sample Emp 1", 12);
		assertEquals(true, PayloadValidator.validateCreatePayload(emp));
	}
	
	

}
