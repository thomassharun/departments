package com.tus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.exception.DepartmentExistenceException;
import com.tus.exception.ResourceNotFoundException;
import com.tus.model.Department;
import com.tus.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> getAllDepartments() {
		List<Department> list = departmentRepository.findAll();
		return list;
	}

	public Department getDeptByName(String name) {
		Department dept;
		try {
			dept = departmentRepository.findByName(name).get().get(0);
			return dept;
		} catch (Exception ex) {
			throw new ResourceNotFoundException("Department with name: " + name + " not found");
		}
	}

	public Department getDeptById(Long id) {
		// Optional<Department> dept = departmentRepository.findById(id);
		try {
			Department dept = departmentRepository.findById(id).get();
			return dept;
		} catch (Exception ex) {
			throw new ResourceNotFoundException("Department with Id: " + id + " not found");
		}
	}

	public Department save(Department department) {

		List<Department> dept = departmentRepository.findByName(department.getName()).get();

		if (dept.size() != 0) {
			throw new DepartmentExistenceException(
					"Department with deptcode : " + department.getDeptCode() + " already exists.");
		}
		try {
			Department dept1 = departmentRepository.save(department);
			return dept1;
		} catch (Exception ex) {
			throw new ResourceNotFoundException(
					"Department with deptCode: " + department.getDeptCode() + " not created");
		}
	}

	public Department updateDept(Department department, Long id) {
		Department dept = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));

		dept.setName(department.getName());
		dept.setLocation(department.getLocation());
		dept.setManager(department.getManager());
		dept.setSalaryRange(department.getSalaryRange());

		Department updatedDepartment = departmentRepository.save(dept);
		return updatedDepartment;
	}
	
	public void deleteDept(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
		departmentRepository.delete(department);
		//return updatedDepartment;
	}

}
