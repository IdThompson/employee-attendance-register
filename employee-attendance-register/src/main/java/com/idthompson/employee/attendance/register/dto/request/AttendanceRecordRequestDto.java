package com.idthompson.employee.attendance.register.dto.request;

import com.idthompson.employee.attendance.register.enums.AttendanceType;
import com.idthompson.employee.attendance.register.model.Employee;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecordRequestDto {
    @NotNull(message = "Employee ID is required")
    private Long employeeId;
    @NotNull(message = "Attendance type is required")
    private AttendanceType type;
    @NotNull(message = "Date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private LocalTime time;
}
