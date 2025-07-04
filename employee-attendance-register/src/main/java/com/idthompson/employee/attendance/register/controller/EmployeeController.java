package com.idthompson.employee.attendance.register.controller;

import com.idthompson.employee.attendance.register.dto.request.EmployeeRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.EmployeeResponseDto;
import com.idthompson.employee.attendance.register.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> create(@Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> update(@PathVariable Long id, @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeResponseDto>>> list(
            @RequestParam(required = false) String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                department == null ?
                        employeeService.findAll(pageable) :
                        employeeService.filterByDepartment(department, pageable)
        );
    }
}