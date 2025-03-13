package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByUsername(String username);

    @Query("SELECT m.role FROM Manager m WHERE m.username = :username")
    Role findRoleByUsername(@Param("username") String username);


}
