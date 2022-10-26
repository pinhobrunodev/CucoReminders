package com.ucsal.cucoreminders.repositories;

import com.ucsal.cucoreminders.entities.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeScheduleRepository extends JpaRepository<TimeSchedule,Long> {
}
