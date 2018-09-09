package com.javacoderhint.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javacoderhint.model.Employee;
import com.javacoderhint.repository.EmpRepository;
import com.javacoderhint.service.EmpServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmpServiceTest {
	
	@Mock
	private EmpRepository empRepository;
	
	@InjectMocks
	private EmpServiceImpl empService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllEmp(){
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1,"Emp Sample 1",23));
		empList.add(new Employee(2,"Emp Sample 2",43));
		empList.add(new Employee(3,"Emp Sample 3",45));
		when(empRepository.findAll()).thenReturn(empList);
		
		List<Employee> result = empService.getAllEmp();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetEmpById(){
		Employee emp = new Employee(1,"Emp Sample 1",65);
		when(empRepository.findOne(1L)).thenReturn(emp);
		Employee result = empService.getEmpById(1);
		assertEquals(1, result.getId());
		assertEquals("Emp Sample 1", result.getName());
		assertEquals(65, result.getAge());
	}
	
	@Test
	public void saveEmp(){
		Employee emp = new Employee(8,"Emp Sample 8",34);
		when(empRepository.save(emp)).thenReturn(emp);
		Employee result = empService.saveEmp(emp);
		assertEquals(8, result.getId());
		assertEquals("Emp Sample 8", result.getName());
		assertEquals(34, result.getAge());
	}
	
	@Test
	public void removeEmp(){
		Employee emp = new Employee(8,"Emp Sample 8",45);
		empService.removeEmp(emp);
        verify(empRepository, times(1)).delete(emp);
	}
}

