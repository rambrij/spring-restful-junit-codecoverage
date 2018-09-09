package com.javacoderhint.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javacoderhint.exception.EmpException;
import com.javacoderhint.model.Response;
import com.javacoderhint.model.Employee;
import com.javacoderhint.service.EmpService;
import com.javacoderhint.util.PayloadValidator;

@RestController
public class EmpController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService empService;	
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmp(){
    	logger.info("Returning all the Emp´s");
		return new ResponseEntity<List<Employee>>(empService.getAllEmp(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") long id) throws EmpException{
    	logger.info("Emp id to return " + id);
    	Employee emp = empService.getEmpById(id);
    	if (emp == null || emp.getId() <= 0){
            throw new EmpException("Emp doesn´t exist");
    	}
		return new ResponseEntity<Employee>(empService.getEmpById(id), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeEmpById(@PathVariable("id") long id) throws EmpException{
    	logger.info("Emp id to remove " + id);
    	Employee emp = empService.getEmpById(id);
    	if (emp == null || emp.getId() <= 0){
            throw new EmpException("Emp to delete doesn´t exist");
    	}
		empService.removeEmp(emp);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Emp has been deleted"), HttpStatus.OK);
	}   
    
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
   	public ResponseEntity<Employee> saveEmp(@RequestBody Employee payload) throws EmpException{
    	logger.info("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new EmpException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<Employee>(empService.saveEmp(payload), HttpStatus.OK);
   	}    
    @RequestMapping(value = "/emp", method = RequestMethod.PATCH)
   	public ResponseEntity<Employee>  updateEmp(@RequestBody Employee payload) throws EmpException{
    	logger.info("Payload to update " + payload);
    	Employee emp = empService.getEmpById(payload.getId());
    	if (emp == null || emp.getId() <= 0){
            throw new EmpException("Emp to update doesn´t exist");
    	}
		return new ResponseEntity<Employee>(empService.saveEmp(payload), HttpStatus.OK);
   	}	
}
