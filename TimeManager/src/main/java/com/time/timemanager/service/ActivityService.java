package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.Activity;
import com.time.timemanager.payload.requestPayload.ActivityRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface ActivityService {
    Activity logActivity(ActivityRequest activityRequest);
    List<Activity> findByUserId(Long aLong) throws ResourceNotFoundException;
    List<Activity> findByDate(LocalDate date) throws ResourceNotFoundException;

    Activity updateActivity(Long activityId) throws ResourceNotFoundException;
}
