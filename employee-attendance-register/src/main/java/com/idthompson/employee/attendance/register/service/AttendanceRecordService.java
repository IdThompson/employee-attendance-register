package com.idthompson.employee.attendance.register.service;

import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.AttendanceRecordResponseDto;
import com.idthompson.employee.attendance.register.model.AttendanceRecord;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordService {
    ApiResponse<AttendanceRecordResponseDto> register(AttendanceRecordRequestDto request);
    ApiResponse<List<AttendanceRecordResponseDto>> getRegister(Long employeeId, LocalDate start, LocalDate end);
}
