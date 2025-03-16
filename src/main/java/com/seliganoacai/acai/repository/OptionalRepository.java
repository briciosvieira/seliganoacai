package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionalRepository extends JpaRepository<Optional, Long> {


    @Query("SELECT o FROM Optional o WHERE o.id IN :ids")
    List<Optional> findAllById(@Param("ids") List<Long> ids);
}
