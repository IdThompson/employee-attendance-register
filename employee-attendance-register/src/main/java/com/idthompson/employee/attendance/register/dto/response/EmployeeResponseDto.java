package com.idthompson.employee.attendance.register.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String type;
    private String department;
}
