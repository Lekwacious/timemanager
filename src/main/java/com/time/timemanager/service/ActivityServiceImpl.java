package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.Activity;
import com.time.timemanager.payload.requestPayload.ActivityRequest;
import com.time.timemanager.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity logActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setUserId(Long.valueOf(activityRequest.getUserId()));
        activity.setDate(LocalDate.now());
        activity.setIdNumber(activityRequest.getIdNumber());
        activity.setTimeStarted(LocalTime.now());
        activity.setTitle(activityRequest.getTitle());
        activity.setDescription(activityRequest.getDescription());
        //activity.setTimeEnded(activityRequest.getTimeEnded());
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> findByUserId(Long userId) throws ResourceNotFoundException {
        List<Activity> activity = activityRepository.findByUserId(userId);
        if (activity.isEmpty()){
            throw new ResourceNotFoundException("User have not logged any activity");
        }
        return activity;
    }

    @Override
    public List<Activity> findByDate(LocalDate date) throws ResourceNotFoundException {
        List<Activity> activity = activityRepository.findByDate(date);
        if (activity.isEmpty()){
            throw new ResourceNotFoundException("User have not logged any activity");
        }
        return activity;
    }

    @Override
    public Activity updateActivity(Long activityId) throws ResourceNotFoundException {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if (activity.isPresent()){
            activity.get().setTimeEnded(LocalTime.now());
            return activityRepository.save(activity.get());
        }
        else {
            throw new ResourceNotFoundException("Activity does not exist");
        }

    }
}
