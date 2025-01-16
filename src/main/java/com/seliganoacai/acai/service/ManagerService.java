package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.Role;
import com.seliganoacai.acai.repository.ManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ManagerRepository repository;

    @Transactional
    public Manager save(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return repository.save(manager);
    }

    @Transactional
    public Manager findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException(String.format("Usuário não encontrado")));
    }

    @Transactional
    public Role findRoleByUsername(String username) {
        return repository.findRoleByUsername(username);
    }
}
