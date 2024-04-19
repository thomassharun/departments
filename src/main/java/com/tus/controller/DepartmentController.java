package com.tus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.exception.ResourceNotFoundException;
import com.tus.model.Department;
import com.tus.model.TestDetails;
import com.tus.repository.DepartmentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final TestDetails testDetails;
	
	@Autowired
    public DepartmentController(TestDetails testDetails) {
        this.testDetails = testDetails;
    }

	// get all departments
	@GetMapping("/department")
	public List<Department> getAllDepartments() {
		//System.out.println(departmentRepository.findAll());
		return departmentRepository.findAll();
	}

	// find by department
	@GetMapping("/department/deptName/{name}")
	public Department getDepartmentByCode(@PathVariable String name) {
		//System.out.println(departmentRepository.findByDeptCode(code));
		return departmentRepository.findByName(name).get(0);
	}

	// create department rest api
	@PostMapping("/department")
	public Department createDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}

	// get department by id rest api
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
		return ResponseEntity.ok(department);
	}

	// update department rest api
	@PutMapping("/department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
			@RequestBody Department departmentDetails) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));

		department.setName(departmentDetails.getName());
		department.setLocation(departmentDetails.getLocation());
		department.setManager(departmentDetails.getManager());
		department.setSalaryRange(departmentDetails.getSalaryRange());

		Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}

	// delete department rest api
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDepartment(@PathVariable Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));

		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

	// get contact-info rest api
	@GetMapping("/contact-info")
	public ResponseEntity<TestDetails> contact() {
		return ResponseEntity.ok(testDetails);
	}

}
