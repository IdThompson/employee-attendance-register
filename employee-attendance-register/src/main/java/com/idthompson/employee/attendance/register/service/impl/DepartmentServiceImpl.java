package com.idthompson.employee.attendance.register.service.impl;

import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.DepartmentResponseDto;
import com.idthompson.employee.attendance.register.model.Department;
import com.idthompson.employee.attendance.register.repository.DepartmentRepository;
import com.idthompson.employee.attendance.register.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepo;

    @Override
    public ApiResponse<Page<DepartmentResponseDto>> getAllDepartments(Pageable pageable) {
        Page<DepartmentResponseDto> page = departmentRepo.findAll(pageable)
                .map(this::toDto);
        return ApiResponse.of("Departments retrieved successfully", page);
    }
    private DepartmentResponseDto toDto(Department dept) {
        return DepartmentResponseDto.builder()
                .id(dept.getId())
                .name(dept.getName())
                .build();
    }

}