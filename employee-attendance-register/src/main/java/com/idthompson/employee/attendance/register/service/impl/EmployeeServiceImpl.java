package com.idthompson.employee.attendance.register.service.impl;

import com.idthompson.employee.attendance.register.dto.request.EmployeeRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.EmployeeResponseDto;
import com.idthompson.employee.attendance.register.exception.BadRequestException;
import com.idthompson.employee.attendance.register.exception.ResourceNotFoundException;
import com.idthompson.employee.attendance.register.model.Department;
import com.idthompson.employee.attendance.register.model.Employee;
import com.idthompson.employee.attendance.register.repository.DepartmentRepository;
import com.idthompson.employee.attendance.register.repository.EmployeeRepository;
import com.idthompson.employee.attendance.register.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository deptRepo;

    @Override
    public ApiResponse<EmployeeResponseDto> save(EmployeeRequestDto dto) {
        if (dto.getFirstName() == null || dto.getDepartmentName() == null) {
            throw new BadRequestException("First name and department name must not be null");
        }

        Department dept = deptRepo.findByName(dto.getDepartmentName())
                .orElseGet(() -> deptRepo.save(Department.builder().name(dto.getDepartmentName()).build()));

        Employee e = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .type(dto.getType())
                .department(dept)
                .build();

        employeeRepo.save(e);
        return ApiResponse.of("Employee created", toResponseDto(e));
    }

    @Override
    public ApiResponse<EmployeeResponseDto> update(Long id, EmployeeRequestDto dto) {
        if (dto.getFirstName() == null || dto.getDepartmentName() == null) {
            throw new BadRequestException("First name and department name must not be null");
        }

        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        Department dept = deptRepo.findByName(dto.getDepartmentName())
                .orElseGet(() -> deptRepo.save(Department.builder().name(dto.getDepartmentName()).build()));

        existing = existing.toBuilder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .type(dto.getType())
                .department(dept)
                .build();

        employeeRepo.save(existing);
        return ApiResponse.of("Employee updated", toResponseDto(existing));
    }

    @Override
    public ApiResponse<Page<EmployeeResponseDto>> findAll(Pageable pageable) {
        Page<EmployeeResponseDto> dtoPage = employeeRepo.findAll(pageable).map(this::toResponseDto);
        return ApiResponse.of("All employees fetched", dtoPage);
    }

    @Override
    public ApiResponse<Page<EmployeeResponseDto>> filterByDepartment(String departmentName, Pageable pageable) {
        Page<EmployeeResponseDto> dtoPage = employeeRepo.findByDepartment_Name(departmentName, pageable)
                .map(this::toResponseDto);
        return ApiResponse.of("Employees in department fetched", dtoPage);
    }

    private EmployeeResponseDto toResponseDto(Employee e) {
        return EmployeeResponseDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .gender(e.getGender().name())
                .address(e.getAddress())
                .type(e.getType().name())
                .department(e.getDepartment().getName())
                .build();
    }
}