package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.repository.OptionalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionalService {

    @Autowired
    private OptionalRepository repository;

    @Transactional
    public Optional create(Optional entity) {

        return repository.save(entity);
    }

    @Transactional
    public Optional findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Opcional não encontrada"));
    }
    @Transactional
    public List<Optional> findByIds(List<Long> productId) {
        return repository.findAllById(productId);
    }
}
