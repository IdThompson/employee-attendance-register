package com.idthompson.employee.attendance.register.repository;

import com.idthompson.employee.attendance.register.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);

}
