package com.tus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.model.Department;
import java.util.List;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	List<Department> findByName(String name);
}
