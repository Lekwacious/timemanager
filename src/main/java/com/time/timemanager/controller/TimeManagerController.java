package com.time.timemanager.controller;

import com.time.timemanager.exception.ResourceNotFoundException;
import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.payload.requestPayload.LoginRequest;
import com.time.timemanager.payload.requestPayload.SignupRequest;
import com.time.timemanager.payload.responsePayLoad.BaseResponsePayload;
import com.time.timemanager.payload.responsePayLoad.TimeManagerBaseResponse;
import com.time.timemanager.security.JwtTokenProvider;
import com.time.timemanager.security.payload.reponsePayload.JwtAuthenticationResponse;
import com.time.timemanager.service.TimeManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/timeManager")
public class TimeManagerController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TimeManagerUserService timeManagerUserService;

    @Autowired
    JwtTokenProvider tokenProvider;



    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest requestPayLoad){
        try{
            TimeManagerUser user = timeManagerUserService.createUser(requestPayLoad);
            return new ResponseEntity<>(new TimeManagerBaseResponse(true,"successful",
                    HttpStatus.CREATED.value(),LocalDate.now(),user),HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new BaseResponsePayload
                    (false, e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                            LocalDate.now()),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/create/{email}")
    public ResponseEntity<?> findUserByEmail(@RequestBody String email){
        try{
            TimeManagerUser user = timeManagerUserService.findByEmailAddress(email);
            return new ResponseEntity<>(new TimeManagerBaseResponse(true,"successful",
                    HttpStatus.OK.value(),LocalDate.now(),user),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new BaseResponsePayload
                    (false, e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                            LocalDate.now()),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
