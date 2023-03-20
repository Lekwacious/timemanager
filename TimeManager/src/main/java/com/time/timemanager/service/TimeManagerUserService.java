package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.RequestPayLoad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TimeManagerUserService {
    TimeManagerUser createUser(RequestPayLoad requestPayLoad) throws ResourceNotFoundException;
   List< TimeManagerUser >findALL();

   TimeManagerUser findByEmailAddress(String email) throws ResourceNotFoundException;

   TimeManagerUser findByID(Integer id) throws ResourceNotFoundException;


}
