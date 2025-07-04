package com.idthompson.employee.attendance.register.strategy.impl;

import com.idthompson.employee.attendance.register.enums.AttendanceType;
import com.idthompson.employee.attendance.register.strategy.AttendanceStrategy;
import com.idthompson.employee.attendance.register.strategy.AttendanceStrategyFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceStrategyFactoryImpl implements AttendanceStrategyFactory {
    private final List<AttendanceStrategy> strategies;
    private final Map<AttendanceType, AttendanceStrategy> strategyMap = new EnumMap<>(AttendanceType.class);

    public AttendanceStrategyFactoryImpl(List<AttendanceStrategy> strategies) {
        this.strategies = strategies;
    }

    @PostConstruct
    public void initStrategyMap() {
        for (AttendanceStrategy strategy : strategies) {
            if (strategy instanceof SignInStrategy) {
                strategyMap.put(AttendanceType.SIGN_IN, strategy);
            } else if (strategy instanceof SignOutStrategy) {
                strategyMap.put(AttendanceType.SIGN_OUT, strategy);
            } else if (strategy instanceof SickLeaveStrategy) {
                strategyMap.put(AttendanceType.SICK_LEAVE, strategy);
            } else if (strategy instanceof AbsenceStrategy) {
                strategyMap.put(AttendanceType.ABSENCE, strategy);
            }
        }

        // Optional: log what was loaded
        strategyMap.forEach((type, strat) ->
                System.out.println("Strategy registered: " + type + " -> " + strat.getClass().getSimpleName()));
    }

    @Override
    public AttendanceStrategy getStrategy(AttendanceType type) {
        AttendanceStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for: " + type);
        }
        return strategy;
    }
}
