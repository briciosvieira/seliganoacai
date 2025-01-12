package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ManagerService {

    @Autowired
    private ManagerRepository repository;

    @Transactional
    public Manager save(Manager manager) {
        return repository.save(manager);
    }
}
