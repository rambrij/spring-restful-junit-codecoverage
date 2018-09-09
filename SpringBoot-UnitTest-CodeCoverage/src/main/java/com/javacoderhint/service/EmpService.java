package com.javacoderhint.service;

import java.util.List;
import java.util.Optional;

import com.javacoderhint.model.Employee;

public interface EmpService {
	public List<Employee> getAllEmp();
	public Employee getEmpById(long id);
	public Employee saveEmp(Employee emp);
	public void removeEmp(Employee emp);
}
