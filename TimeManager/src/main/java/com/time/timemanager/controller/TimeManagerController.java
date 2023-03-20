package com.time.timemanager.controller;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.RequestPayLoad;
import com.time.timemanager.payload.responsePayLoad.BaseResponsePayload;
import com.time.timemanager.payload.responsePayLoad.TimeManagerBaseResponse;
import com.time.timemanager.service.TimeManagerUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/timeManager")
@AllArgsConstructor
public class TimeManagerController {
    private  final TimeManagerUserService timeManger;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody RequestPayLoad requestPayLoad){
        try{
            TimeManagerUser user = timeManger.createUser(requestPayLoad);
            return new ResponseEntity<>(new TimeManagerBaseResponse(true,"sucessfull",
                    HttpStatus.CREATED.value(),LocalDate.now(),user),HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new BaseResponsePayload
                    (false, e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                            LocalDate.now()),HttpStatus.BAD_REQUEST);
        }

    }
}
