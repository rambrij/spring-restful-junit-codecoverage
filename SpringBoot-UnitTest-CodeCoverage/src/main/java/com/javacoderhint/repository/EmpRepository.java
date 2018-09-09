package com.javacoderhint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoderhint.model.Employee;

@Repository("empRepository")
public interface EmpRepository extends JpaRepository<Employee, Long>{

//	Employee findOne(long id);

}
