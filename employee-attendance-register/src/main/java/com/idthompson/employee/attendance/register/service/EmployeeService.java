package com.idthompson.employee.attendance.register.service;

import com.idthompson.employee.attendance.register.dto.request.EmployeeRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.EmployeeResponseDto;
import com.idthompson.employee.attendance.register.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EmployeeService {
    ApiResponse<EmployeeResponseDto> save(EmployeeRequestDto dto);
    ApiResponse<EmployeeResponseDto> update(Long id, EmployeeRequestDto dto);
    ApiResponse<Page<EmployeeResponseDto>> findAll(Pageable pageable);
    ApiResponse<Page<EmployeeResponseDto>> filterByDepartment(String departmentName, Pageable pageable);
}
