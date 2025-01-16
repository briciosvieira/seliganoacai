package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByUsername(String username);
}
