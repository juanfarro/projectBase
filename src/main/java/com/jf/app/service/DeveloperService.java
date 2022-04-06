package com.jf.app.service;

import com.jf.app.entity.Developer;
import com.jf.app.model.DeveloperDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeveloperService {

    void create(DeveloperDto developer, Long idProject);

    Optional<Developer> get(Long id);

    Page<Developer> getAll(Pageable pageable, Long idProject);

    Developer update(DeveloperDto developer, Long idDev, Long idProject);

    void delete(Long id);
}
