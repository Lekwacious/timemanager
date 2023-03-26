package com.time.timemanager.controller;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.Activity;
import com.time.timemanager.payload.requestPayload.ActivityRequest;
import com.time.timemanager.payload.requestPayload.DateRequest;
import com.time.timemanager.payload.responsePayLoad.ActivityBaseResponse;
import com.time.timemanager.payload.responsePayLoad.ActivityListResponse;
import com.time.timemanager.payload.responsePayLoad.BaseResponsePayload;
import com.time.timemanager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/timeManager")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/log")
    public ResponseEntity<?> addLog(@RequestBody ActivityRequest requestPayLoad){
        Activity user = activityService.logActivity(requestPayLoad);
        return new ResponseEntity<>(new ActivityBaseResponse(true,"successful",
                HttpStatus.CREATED.value(), LocalDate.now(),user),HttpStatus.CREATED);

    }
    @PutMapping("/log/{activityId}")
    public ResponseEntity<?> updateActivity(@PathVariable Long activityId) throws ResourceNotFoundException {
        try {
            Activity user = activityService.updateActivity(activityId);
            return new ResponseEntity<>(new ActivityBaseResponse(true,"successful",
                    HttpStatus.OK.value(), LocalDate.now(),user),HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new BaseResponsePayload(true, e.getMessage(),
                    HttpStatus.BAD_REQUEST.value(), LocalDate.now() ),HttpStatus.BAD_REQUEST);

        }


    }
    @GetMapping("/log/user/{userId}")
    public ResponseEntity<?> getUserActivity(@PathVariable Long userId) throws ResourceNotFoundException {
        try {
            List<Activity> activities = activityService.findByUserId(userId);
            return new ResponseEntity<>(new ActivityListResponse(true,"successful",
                    HttpStatus.OK.value(), LocalDate.now(),activities),HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new BaseResponsePayload(false, e.getMessage(),
                    HttpStatus.BAD_REQUEST.value(), LocalDate.now() ),HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/log/date")
    public ResponseEntity<?> getActivityByDate(@RequestBody DateRequest date) throws ResourceNotFoundException {
        try {
            List<Activity> activities = activityService.findByDate(date.getDate());
            return new ResponseEntity<>(new ActivityListResponse(true,"successful",
                    HttpStatus.OK.value(), LocalDate.now(),activities),HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new BaseResponsePayload(false, e.getMessage(),
                    HttpStatus.BAD_REQUEST.value(), LocalDate.now() ),HttpStatus.BAD_REQUEST);

        }
    }


}
