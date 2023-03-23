package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.requestPayload.SignupRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TimeManagerUserService {
    TimeManagerUser createUser(SignupRequest requestPayLoad) throws ResourceNotFoundException;
   List< TimeManagerUser >findALL();

   TimeManagerUser findByEmailAddress(String email) throws ResourceNotFoundException;

   TimeManagerUser findByID(Long id) throws ResourceNotFoundException;


}
