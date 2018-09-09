package com.javacoderhint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoderhint.model.Employee;
import com.javacoderhint.repository.EmpRepository;

@Service("empService")
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpRepository empRepository;
	
	@Override
	public List<Employee> getAllEmp() {
		return empRepository.findAll();
	}

	@Override
	public Employee getEmpById(long id) {
		return empRepository.findOne(id);
	}

	@Override
	public Employee saveEmp(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public void removeEmp(Employee emp) {
		empRepository.delete(emp);
	}
	

}
