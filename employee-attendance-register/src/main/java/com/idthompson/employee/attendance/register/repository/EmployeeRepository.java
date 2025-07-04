package com.idthompson.employee.attendance.register.repository;

import com.idthompson.employee.attendance.register.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByDepartment_Name(String departmentName, Pageable pageable);
}
