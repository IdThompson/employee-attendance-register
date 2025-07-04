package com.idthompson.employee.attendance.register.service.impl;

import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.AttendanceRecordResponseDto;
import com.idthompson.employee.attendance.register.enums.AttendanceType;
import com.idthompson.employee.attendance.register.exception.BadRequestException;
import com.idthompson.employee.attendance.register.exception.ResourceNotFoundException;
import com.idthompson.employee.attendance.register.model.AttendanceRecord;
import com.idthompson.employee.attendance.register.model.Employee;
import com.idthompson.employee.attendance.register.repository.AttendanceRecordRepository;
import com.idthompson.employee.attendance.register.repository.EmployeeRepository;
import com.idthompson.employee.attendance.register.service.AttendanceRecordService;
import com.idthompson.employee.attendance.register.strategy.AttendanceStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private final AttendanceRecordRepository attendanceRepo;
    private final EmployeeRepository employeeRepo;
    private final AttendanceStrategyFactory strategyFactory;
    @Override
    public ApiResponse<AttendanceRecordResponseDto> register(AttendanceRecordRequestDto request) {
        if (request.getType() == null || request.getDate() == null || request.getEmployeeId() == null) {
            throw new BadRequestException("Attendance type, date, and employee ID are required");
        }
        if ((request.getType() == AttendanceType.SIGN_IN || request.getType() == AttendanceType.SIGN_OUT)
                && request.getTime() == null) {
            throw new BadRequestException("Time is required for sign-in or sign-out attendance type");
        }

        Employee e = employeeRepo.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + request.getEmployeeId()));

        AttendanceRecord rec = strategyFactory.getStrategy(request.getType())
                .register(request, e);

        AttendanceRecord saved = attendanceRepo.save(rec);

        return ApiResponse.of("Attendance recorded successfully", toDto(saved));
    }

    @Override
    public ApiResponse<List<AttendanceRecordResponseDto>> getRegister(Long employeeId, LocalDate start, LocalDate end) {
        List<AttendanceRecord> records = attendanceRepo.findByEmployee_IdAndDateBetween(employeeId, start, end);
        if (records.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No attendance records found for employee ID " + employeeId +
                            " between " + start + " and " + end
            );
        }
        List<AttendanceRecordResponseDto> dtoList = records.stream().map(this::toDto).toList();
        return ApiResponse.of("Attendance records retrieved", dtoList);
    }
    private AttendanceRecordResponseDto toDto(AttendanceRecord record) {
        return AttendanceRecordResponseDto.builder()
                .id(record.getId())
                .type(record.getType().name())
                .date(record.getDate())
                .time(record.getTime())
                .employeeId(record.getEmployee().getId())
                .employeeName(record.getEmployee().getFirstName() + " " + record.getEmployee().getLastName())
                .build();
    }
}
