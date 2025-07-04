package com.idthompson.employee.attendance.register.strategy;

import com.idthompson.employee.attendance.register.enums.AttendanceType;

public interface AttendanceStrategyFactory {
    AttendanceStrategy getStrategy(AttendanceType type);

}
