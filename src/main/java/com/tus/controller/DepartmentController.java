package com.tus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.constants.DeptConstants;
import com.tus.model.Department;
import com.tus.model.ResponseDto;
import com.tus.model.TestDetails;
import com.tus.service.DepartmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;

	private final TestDetails testDetails;

	private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	public DepartmentController(TestDetails testDetails) {
		this.testDetails = testDetails;
	}

	// get all departments
	@GetMapping("/department")
	public ResponseEntity<List<Department>> getAllDepartments(
			@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside getAllDepartments api with CorrelationId: {}", correlationId);
		List<Department> list = deptService.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	// find by department
	@GetMapping("/department/deptName/{name}")
	public ResponseEntity<Department> getDepartmentByCode(@PathVariable String name,
			@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside getDepartmentByCode api with CorrelationId: {} and dept code: {}", correlationId, name);
		Department dept = deptService.getDeptByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}

	// create department rest api
	@PostMapping("/department")
	public ResponseEntity<ResponseDto> createDepartment(@RequestBody Department department,
			@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside createDepartment api with CorrelationId: {}", correlationId);
		deptService.save(department);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(DeptConstants.STATUS_201, DeptConstants.MESSAGE_201));
	}

	// get department by id rest api
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id,
			@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside getDepartmentById api with CorrelationId: {} and Dept. Id: {}", correlationId, id);
		Department dept = deptService.getDeptById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}

	// update department rest api
	@PutMapping("/department/{id}")
	public ResponseEntity<ResponseDto> updateDepartment(@PathVariable Long id,
			@RequestBody Department departmentDetails, @RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside updateDepartment api with CorrelationId: {} and Dept. Id: {}", correlationId, id);
		Department updatedDepartment = deptService.updateDept(departmentDetails, id);

		if (updatedDepartment != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(DeptConstants.STATUS_200, DeptConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(DeptConstants.STATUS_500, DeptConstants.MESSAGE_500));
		}
	}

	// delete department rest api
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<ResponseDto> deleteDepartment(@PathVariable Long id,
			@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside deleteDepartment api with CorrelationId: {} and Dept. Id: {}", correlationId, id);
		try {
			deptService.deleteDept(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(DeptConstants.STATUS_204, DeptConstants.MESSAGE_204));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(DeptConstants.STATUS_500, DeptConstants.MESSAGE_500));
		}
	}

	// get contact-info rest api
	@GetMapping("/contact-info")
	public ResponseEntity<TestDetails> contact(@RequestHeader("tus-correlation-id") String correlationId) {
		logger.info("Inside contact-info api with CorrelationId: {}", correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(testDetails);
	}

}
