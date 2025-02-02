package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.Role;
import com.seliganoacai.acai.repository.ManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public List<Manager> findAll() {
     return repository.findAll();
    }

    @Transactional
    public Manager findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("Usuário não encontrado")));
    }

    @Transactional
    public Manager updateManager(Long id, String name, String password, String username) {

        Manager manager = findById(id);

        if (name != null && !name.isEmpty()) {
            manager.setName(name);
        }

        if (username != null && !username.isEmpty()) {
            manager.setUsername(username);
        }

        if (password != null && !password.isEmpty()) {
            manager.setPassword(passwordEncoder.encode(password));
        }

        return repository.save(manager);
    }

    @Transactional
    public void delete(Long id) {
        Manager manager = findById(id);
        repository.delete(manager);
    }
}
