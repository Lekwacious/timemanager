package com.time.timemanager.service;

import com.time.timemanager.exception.ResourceAlreadyExist;
import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.RoleName;
import com.time.timemanager.model.Roles;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.requestPayload.SignupRequest;
import com.time.timemanager.repository.RoleRepository;
import com.time.timemanager.repository.TimeManagerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TimeManagerServiceImpl implements TimeManagerUserService {
    @Autowired
    private TimeManagerUserRepository timeManagerUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TimeManagerUser createUser(SignupRequest requestPayLoad) throws ResourceNotFoundException {
     Boolean emailExist= timeManagerUserRepository.existsByEmail(requestPayLoad.getEmail());
        if (emailExist) {
            throw new ResourceAlreadyExist("email already exist");

        } else  {
            TimeManagerUser user = new TimeManagerUser();
            user.setEmail(requestPayLoad.getEmail());
            user.setFirstName(requestPayLoad.getFirstName());
            user.setPhoneNumber(requestPayLoad.getPassword());
            user.setLastName(requestPayLoad.getLastName());
            user.setPassword(passwordEncoder.encode(requestPayLoad.getPassword()));
           Optional<Roles> role= roleRepository.findByName(RoleName.ROLE_USER);
            Roles userRole = null; 
           if (role.isEmpty()){
                 userRole = new  Roles(RoleName.ROLE_USER);

                user.setRoles(Collections.singleton(userRole));
                roleRepository.save(userRole);
                timeManagerUserRepository.save(user);
                
            }
            else {
                user.setRoles(Collections.singleton(role.get()));
                timeManagerUserRepository.save(user);
            }


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
    public TimeManagerUser findByID(Long id) throws ResourceNotFoundException {
        Optional<TimeManagerUser> user = timeManagerUserRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("id not found,id does not exist");
        }
        else {
            return user.get();
        }
    }
}
