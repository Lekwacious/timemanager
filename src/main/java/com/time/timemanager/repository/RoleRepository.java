package com.time.timemanager.repository;

import com.time.timemanager.model.RoleName;
import com.time.timemanager.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleName roleName);

}
