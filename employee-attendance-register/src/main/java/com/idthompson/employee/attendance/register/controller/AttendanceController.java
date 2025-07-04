package com.idthompson.employee.attendance.register.controller;

import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.dto.response.ApiResponse;
import com.idthompson.employee.attendance.register.dto.response.AttendanceRecordResponseDto;
import com.idthompson.employee.attendance.register.service.AttendanceRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceRecordService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AttendanceRecordResponseDto>> register(@Valid @RequestBody AttendanceRecordRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.register(request));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<List<AttendanceRecordResponseDto>>> getRecords(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(attendanceService.getRegister(employeeId, start, end));
    }
}
