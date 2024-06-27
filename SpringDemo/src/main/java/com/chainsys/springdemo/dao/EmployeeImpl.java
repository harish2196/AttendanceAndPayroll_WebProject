package com.chainsys.springdemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.springdemo.model.ObjectModel;

@Repository
public class EmployeeImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertEmployee(ObjectModel objectModel) {
		String insertQuery = "INSERT INTO employee (username, designation, useremail, usermobile, userpassword) VALUES (?, ?, ?, ?, ?)";
		Object[] params = {objectModel.getName(), objectModel.getDesignation(), objectModel.getEmail(), objectModel.getPhoneNumber(), objectModel.getPassWord()};
		int rowsAffected = jdbcTemplate.update(insertQuery, params);
		System.out.println("Rows Affected: " + rowsAffected);
	}

	public List<ObjectModel> getAllEmployees(String name) {
	    String selectQuery = "SELECT id, username as name, designation, useremail as email, usermobile as phoneNumber FROM employee WHERE username LIKE ?";
	    String namePattern = name + "%";
	    List<ObjectModel> employees = jdbcTemplate.query(selectQuery, new Object[]{namePattern}, new BeanPropertyRowMapper<>(ObjectModel.class));
	    return employees;
	}

	
	
	public List<ObjectModel> getAllEmployees() {
	    String selectQuery = "SELECT id, username as name, designation, useremail as email, usermobile as phoneNumber FROM employee ORDER BY username ASC";
	    List<ObjectModel> employees = jdbcTemplate.query(selectQuery,new BeanPropertyRowMapper<>(ObjectModel.class));
		return employees;
	}

	public void deleteEmployeeById(int id) { 
		String deleteQuery = "DELETE FROM employee WHERE id=?";
		int rowsAffected = jdbcTemplate.update(deleteQuery, id);
		System.out.println("Rows Affected by delete operation: " + rowsAffected);
	}

	public void updateEmployee(ObjectModel objectModel) {
		String updateQuery = "UPDATE employee SET username=?, designation=?, useremail=?, usermobile=? WHERE id=?";
		Object[] params = {objectModel.getName(), objectModel.getDesignation(),
				objectModel.getEmail(), objectModel.getPhoneNumber(),
				objectModel.getId()};
		int rowsAffected = jdbcTemplate.update(updateQuery, params);
		System.out.println("Rows Affected by update operation: " + rowsAffected);
	}

	public  boolean login(String username, String password) {
		String query = "SELECT username,userpassword FROM employee WHERE username = ? AND userpassword = ?";
		try {
			ObjectModel employee = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ObjectModel.class), username, password);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}

