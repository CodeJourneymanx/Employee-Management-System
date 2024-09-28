package com.Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Management.entity.Employee;
import com.Management.repository.EmpRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository repo;

	@Override
	public Employee saveEmp(Employee emp) {

		return repo.save(emp);
	}

	@Override
	public List<Employee> getAllEmp() {

		return repo.findAll();
	}

	@Override
	public Employee getEmpById(int id) {

		return repo.findById(id).get();
	}

	@Override
	public boolean deleteEmp(int id) {
		Employee emp = repo.findById(id).get();
		if (emp != null) {
			repo.delete(emp);
			return true;
		}
		return false;
	}

	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();
		session.removeAttribute("msg");
	}

}
