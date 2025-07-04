package com.idthompson.employee.attendance.register.repository;

import com.idthompson.employee.attendance.register.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByEmployee_IdAndDateBetween(Long id, LocalDate start, LocalDate end);
}
