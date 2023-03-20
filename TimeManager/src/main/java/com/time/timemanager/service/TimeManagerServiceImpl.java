package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceAlreadyExist;
import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.RequestPayLoad;
import com.time.timemanager.repository.TimeManagerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimeManagerServiceImpl implements TimeManagerUserService {
    @Autowired
    private TimeManagerUserRepository timeManagerUserRepository;

    @Override
    public TimeManagerUser createUser(RequestPayLoad requestPayLoad) throws ResourceNotFoundException {
     TimeManagerUser getUserEmail= findByEmailAddress(requestPayLoad.getEmail());
        if (Objects.equals(getUserEmail.getEmail(), requestPayLoad.getEmail())) {
            throw new ResourceAlreadyExist("email already exist");

        } else  {
            TimeManagerUser user = new TimeManagerUser();
            user.setEmail(requestPayLoad.getEmail());
            user.setFirstName(requestPayLoad.getFirstName());
            user.setPhoneNumber(requestPayLoad.getPassword());
            user.setLastName(requestPayLoad.getLastName());
            timeManagerUserRepository.save(user);
            return user;
        }


    }

    @Override
    public List<TimeManagerUser> findALL() {
        return timeManagerUserRepository.findAll();
    }

    @Override
    public TimeManagerUser findByEmailAddress(String email) throws ResourceNotFoundException {
       Optional<TimeManagerUser>user  =  timeManagerUserRepository.findByEmail(email);
       if(user.isEmpty()) {
           throw new ResourceNotFoundException("email does not exist");
       }
           else {
               return user.get();
           }
       }

    @Override
    public TimeManagerUser findByID(Integer id) throws ResourceNotFoundException {
        Optional<TimeManagerUser> user = timeManagerUserRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("id not found,id does not exist");
        }
        else {
            return user.get();
        }
    }
}
