package com.idthompson.employee.attendance.register.service;

import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.DepartmentResponseDto;
import com.idthompson.employee.attendance.register.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DepartmentService {
    ApiResponse<Page<DepartmentResponseDto>> getAllDepartments(Pageable pageable);

}
