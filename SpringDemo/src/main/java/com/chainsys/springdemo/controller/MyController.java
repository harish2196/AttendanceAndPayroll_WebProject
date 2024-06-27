package com.chainsys.springdemo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chainsys.springdemo.dao.EmployeeImpl;
import com.chainsys.springdemo.model.ObjectModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MyController {

	@Autowired
	EmployeeImpl employeeImpl;


	@RequestMapping("/register")
	public String saveEmp() {
		return "registration.jsp";

	}
	@RequestMapping("/home")
	public String viewEmp() {
		return "Home.jsp";

	}
	@RequestMapping("/edit")
	public String update() {
		return "update.jsp";

	}

	@RequestMapping("/loginn")
	public String login() {
		return "login.jsp";

	}

	@PostMapping("/register")
	public String saveEmployee(@RequestParam("name") String name,@RequestParam("role") String desgination,
			@RequestParam("email") String email,@RequestParam("contact") String phoneNumber,@RequestParam("pass") String passWord) {
		System.out.println("In Register");
		ObjectModel objectModel=new ObjectModel();
		objectModel.setName(name);
		objectModel.setDesignation(desgination);
		objectModel.setEmail(email);
		objectModel.setPhoneNumber(phoneNumber);
		objectModel.setPassWord(passWord);
		employeeImpl.insertEmployee(objectModel);

		return "registration.jsp";
	}

	@PostMapping("/search")
	public ModelAndView retrieveAllEmployees(@RequestParam("name") String name) {
		List<ObjectModel> employees = employeeImpl.getAllEmployees(name);

		ModelAndView modelAndView = new ModelAndView("ViewEmployee.jsp");
		modelAndView.addObject("employees", employees);

		return modelAndView;
	}

	@GetMapping("/all")
	public ModelAndView retrieveAllEmployees() {
		List<ObjectModel> employees = employeeImpl.getAllEmployees();

		ModelAndView modelAndView = new ModelAndView("ViewEmployee.jsp");
		modelAndView.addObject("employees", employees);

		return modelAndView;
	}

	@PostMapping("/delete")
	public ModelAndView deleteEmployee(@RequestParam("id") int id) {
		employeeImpl.deleteEmployeeById(id);

		return new ModelAndView("redirect:/all");
	}

	@PostMapping("/update")
	public ModelAndView updateEmployee(HttpServletRequest request) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String designation = request.getParameter("designation");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");

		ObjectModel objectModel = new ObjectModel();
		objectModel.setId(id);
		objectModel.setName(name);
		objectModel.setDesignation(designation);
		objectModel.setEmail(email);
		objectModel.setPhoneNumber(phoneNumber);

		employeeImpl.updateEmployee(objectModel);

		List<ObjectModel> employees = employeeImpl.getAllEmployees();
		ModelAndView modelAndView = new ModelAndView("ViewEmployee.jsp");
		modelAndView.addObject("employees", employees);

		return modelAndView;
	}

	@RequestMapping("/login")
	public String  login(@RequestParam("username") String username,@RequestParam("password") String password) {
		boolean success = false;
		try {
			success = employeeImpl.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (success) {
			return "redirect:/home";

		} else {
			return "failure.jsp";
		}
	}


}
