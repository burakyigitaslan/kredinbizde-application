package com.patika.garantiservice.repository;

import com.patika.garantiservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<List<Application>> findByUserId(Long id);
}
