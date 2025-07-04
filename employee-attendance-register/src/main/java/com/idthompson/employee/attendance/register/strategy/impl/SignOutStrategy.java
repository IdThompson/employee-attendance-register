package com.idthompson.employee.attendance.register.strategy.impl;


import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.enums.AttendanceType;
import com.idthompson.employee.attendance.register.model.AttendanceRecord;
import com.idthompson.employee.attendance.register.model.Employee;
import com.idthompson.employee.attendance.register.strategy.AttendanceStrategy;
import org.springframework.stereotype.Component;

@Component
public class SignOutStrategy implements AttendanceStrategy {

    @Override
    public AttendanceRecord register(AttendanceRecordRequestDto request, Employee employee) {
        return AttendanceRecord.builder()
                .employee(employee)
                .type(AttendanceType.SIGN_OUT)
                .date(request.getDate())
                .time(request.getTime())
                .build();
    }
}