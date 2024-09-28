package com.Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Management.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{
	
	
	

}
