package com.chainsys.springdemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chainsys.springdemo.model.ObjectModel;

@Repository
public interface EmployeeDAO {
	
	public void insertEmployee(ObjectModel objectModel);
	
	public List<ObjectModel> getAllEmployees(String name);
	
	public List<ObjectModel> getAllEmployees();
	
	public void deleteEmployeeById(int id);
	
	public void updateEmployee(ObjectModel objectModel);
	
	public  boolean login(String username, String password);
	
	

}
