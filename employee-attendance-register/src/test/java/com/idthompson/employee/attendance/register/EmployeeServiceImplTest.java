package com.idthompson.employee.attendance.register;

import com.idthompson.employee.attendance.register.dto.request.EmployeeRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.EmployeeResponseDto;
import com.idthompson.employee.attendance.register.enums.EmployeeType;
import com.idthompson.employee.attendance.register.enums.Gender;
import com.idthompson.employee.attendance.register.exception.ResourceNotFoundException;
import com.idthompson.employee.attendance.register.model.Department;
import com.idthompson.employee.attendance.register.model.Employee;
import com.idthompson.employee.attendance.register.repository.DepartmentRepository;
import com.idthompson.employee.attendance.register.repository.EmployeeRepository;
import com.idthompson.employee.attendance.register.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepo;
    @Mock private DepartmentRepository deptRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void save_validEmployee_shouldSucceed() {
        EmployeeRequestDto dto = EmployeeRequestDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .gender(Gender.FEMALE)
                .address("456 Avenue")
                .type(EmployeeType.NON_MEDICAL)
                .departmentName("Admin")
                .build();

        Department dept = Department.builder().id(1L).name("Admin").build();
        Employee emp = Employee.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Doe")
                .gender(Gender.FEMALE)
                .address("456 Avenue")
                .type(EmployeeType.NON_MEDICAL)
                .department(dept)
                .build();

        when(deptRepo.findByName("Admin")).thenReturn(Optional.of(dept));
        when(employeeRepo.save(any())).thenReturn(emp);

        ApiResponse<EmployeeResponseDto> response = employeeService.save(dto);

        assertEquals("Employee created", response.getMessage());
        assertEquals("Jane", response.getData().getFirstName());
    }

    @Test
    void update_employeeNotFound_shouldThrowResourceNotFoundException() {
        EmployeeRequestDto dto = EmployeeRequestDto.builder()
                .firstName("Updated")
                .lastName("User")
                .gender(Gender.MALE)
                .address("New Address")
                .type(EmployeeType.MEDICAL)
                .departmentName("Surgery")
                .build();

        when(employeeRepo.findById(100L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                employeeService.update(100L, dto));

        assertEquals("Employee not found with ID: 100", ex.getMessage());
    }
}
