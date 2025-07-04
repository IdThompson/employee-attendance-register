package com.idthompson.employee.attendance.register;

import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.AttendanceRecordResponseDto;
import com.idthompson.employee.attendance.register.enums.AttendanceType;
import com.idthompson.employee.attendance.register.enums.EmployeeType;
import com.idthompson.employee.attendance.register.enums.Gender;
import com.idthompson.employee.attendance.register.exception.BadRequestException;
import com.idthompson.employee.attendance.register.exception.ResourceNotFoundException;
import com.idthompson.employee.attendance.register.model.AttendanceRecord;
import com.idthompson.employee.attendance.register.model.Department;
import com.idthompson.employee.attendance.register.model.Employee;
import com.idthompson.employee.attendance.register.repository.AttendanceRecordRepository;
import com.idthompson.employee.attendance.register.repository.EmployeeRepository;
import com.idthompson.employee.attendance.register.service.impl.AttendanceRecordServiceImpl;
import com.idthompson.employee.attendance.register.strategy.AttendanceStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceImplTest {
    @Mock
    private AttendanceRecordRepository attendanceRepo;
    @Mock private EmployeeRepository employeeRepo;
    @Mock private AttendanceStrategyFactory strategyFactory;

    @InjectMocks
    private AttendanceRecordServiceImpl attendanceService;

    private final Employee dummyEmployee = Employee.builder()
            .id(1L)
            .firstName("John")
            .lastName("Doe")
            .gender(Gender.MALE)
            .address("123 Street")
            .type(EmployeeType.MEDICAL)
            .department(Department.builder().id(1L).name("Cardiology").build())
            .build();

    private final AttendanceRecord dummyRecord = AttendanceRecord.builder()
            .id(100L)
            .employee(dummyEmployee)
            .type(AttendanceType.SIGN_IN)
            .date(LocalDate.of(2023, 1, 1))
            .time(LocalTime.of(9, 0))
            .build();

    @Test
    void register_validSignIn_shouldReturnSuccess() {
        AttendanceRecordRequestDto request = AttendanceRecordRequestDto.builder()
                .employeeId(1L)
                .type(AttendanceType.SIGN_IN)
                .date(LocalDate.of(2023, 1, 1))
                .time(LocalTime.of(9, 0))
                .build();

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(dummyEmployee));
        when(strategyFactory.getStrategy(AttendanceType.SIGN_IN)).thenReturn((req, emp) -> dummyRecord);
        when(attendanceRepo.save(any())).thenReturn(dummyRecord);

        ApiResponse<AttendanceRecordResponseDto> response = attendanceService.register(request);

        assertEquals("Attendance recorded successfully", response.getMessage());
        assertEquals("SIGN_IN", response.getData().getType());
        verify(attendanceRepo).save(any());
    }

    @Test
    void register_signInWithoutTime_shouldThrowBadRequestException() {
        AttendanceRecordRequestDto request = AttendanceRecordRequestDto.builder()
                .employeeId(1L)
                .type(AttendanceType.SIGN_IN)
                .date(LocalDate.now())
                .time(null)
                .build();

        BadRequestException ex = assertThrows(BadRequestException.class, () ->
                attendanceService.register(request));

        assertEquals("Time is required for sign-in or sign-out attendance type", ex.getMessage());
    }

    @Test
    void register_employeeNotFound_shouldThrowResourceNotFoundException() {
        AttendanceRecordRequestDto request = AttendanceRecordRequestDto.builder()
                .employeeId(999L)
                .type(AttendanceType.SIGN_IN)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        when(employeeRepo.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                attendanceService.register(request));

        assertEquals("Employee not found with ID: 999", ex.getMessage());
    }
}
