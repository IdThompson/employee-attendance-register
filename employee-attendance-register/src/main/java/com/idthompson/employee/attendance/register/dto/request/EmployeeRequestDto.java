package com.idthompson.employee.attendance.register.dto.request;

import com.idthompson.employee.attendance.register.enums.EmployeeType;
import com.idthompson.employee.attendance.register.enums.Gender;
import com.idthompson.employee.attendance.register.model.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Employee type is required")
    private EmployeeType type;
    @NotBlank(message = "Department name is required")
    private String departmentName;

}
