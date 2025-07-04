package com.idthompson.employee.attendance.register.strategy;

import com.idthompson.employee.attendance.register.dto.request.AttendanceRecordRequestDto;
import com.idthompson.employee.attendance.register.model.AttendanceRecord;
import com.idthompson.employee.attendance.register.model.Employee;

public interface AttendanceStrategy {
    AttendanceRecord register(AttendanceRecordRequestDto request, Employee employee);

}
