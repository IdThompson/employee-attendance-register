package com.idthompson.employee.attendance.register.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {
    @NotBlank(message = "Department name is required")
    private String name;
}
