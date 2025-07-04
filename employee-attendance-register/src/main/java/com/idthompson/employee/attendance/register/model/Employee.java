package com.idthompson.employee.attendance.register.model;

import com.idthompson.employee.attendance.register.enums.EmployeeType;
import com.idthompson.employee.attendance.register.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    @Enumerated(EnumType.STRING)
    private EmployeeType type;
    @ManyToOne
    private Department department;
}
