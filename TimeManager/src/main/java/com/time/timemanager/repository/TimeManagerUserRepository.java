package com.time.timemanager.repository;

import com.time.timemanager.model.TimeManagerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeManagerUserRepository extends JpaRepository<TimeManagerUser, Integer> {

    Optional<TimeManagerUser> findByEmail(String email);



}
