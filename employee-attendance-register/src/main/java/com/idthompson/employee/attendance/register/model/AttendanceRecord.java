package com.idthompson.employee.attendance.register.model;

import com.idthompson.employee.attendance.register.enums.AttendanceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendanceRecord")
public class AttendanceRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Employee employee;
    @Enumerated(EnumType.STRING)
    private AttendanceType type;
    private LocalDate date;
    private LocalTime time;
}
