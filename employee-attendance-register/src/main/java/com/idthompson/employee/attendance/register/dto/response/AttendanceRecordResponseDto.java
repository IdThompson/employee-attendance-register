package com.idthompson.employee.attendance.register.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceRecordResponseDto {
    private Long id;
    private String type;
    private LocalDate date;
    private LocalTime time;
    private Long employeeId;
    private String employeeName;
}
