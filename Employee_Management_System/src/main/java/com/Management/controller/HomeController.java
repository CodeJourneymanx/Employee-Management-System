package com.Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Management.entity.Employee;
import com.Management.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmpService empService;

	@GetMapping("/")
	public String index(Model m) {

		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);

		return "index";
	}

	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {

		return "emp_save";
	}

	@GetMapping("/loadEmpEdit/{id}")
	public String loadEmpEdit(@PathVariable int id, Model m) {
		System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);

		return "emp_edit";
	}

	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee saved = empService.saveEmp(emp);

		if (saved != null) {
			// System.out.println("Data Saved");
			session.setAttribute("msg", "Register Successfully!");

		} else {
			// System.out.println("Data not saved");
			session.setAttribute("msg", "Something Wrong on Server!");
		}

		return "redirect:/loadEmpSave";
	}
 
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee updated = empService.saveEmp(emp);

		if (updated != null) {
			// System.out.println("Data Saved");
			session.setAttribute("msg", "Updated Successfully!");

		} else {
			// System.out.println("Data not saved");
			session.setAttribute("msg", "Something Wrong on Server!");
		}

		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {

		boolean deleted = empService.deleteEmp(id);

		if (deleted) {
			session.setAttribute("msg", "Deleted Successfully!");

		} else {
			session.setAttribute("msg", "Something Wrong on Server!");
		}

		return "redirect:/";
	}

}
