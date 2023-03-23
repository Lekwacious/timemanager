package com.time.timemanager.repository;

import com.time.timemanager.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUserId(Long aLong);
    List<Activity> findByDate(LocalDate date);
}
