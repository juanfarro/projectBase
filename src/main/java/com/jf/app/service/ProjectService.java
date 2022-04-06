package com.jf.app.service;

import com.jf.app.entity.Project;
import com.jf.app.model.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {

    void create(ProjectDto projectDto);

    Optional<Project> get(Long id);

    Page<Project> getAll(Pageable pageable);

    Project update(ProjectDto projectDto, Long id);

    void delete(Long id);
}
