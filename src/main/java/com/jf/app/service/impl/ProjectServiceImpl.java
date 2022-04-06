package com.jf.app.service.impl;

import com.jf.app.entity.Project;
import com.jf.app.model.ProjectDto;
import com.jf.app.repository.ProjectRepository;
import com.jf.app.repository.TypeRepository;
import com.jf.app.service.ProjectService;
import com.jf.app.mapper.ProjectMappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final TypeRepository typeRepository;

    private final ProjectMappers projectMappers;

    @Override
    public void create(ProjectDto projectDto, Long idType) {
        Project project = projectMappers.map(projectDto);
        project.setType(typeRepository.findById(idType).get());
        projectRepository.save(project);
    }

    @Override
    public Optional<Project> get(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> getAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Project update(ProjectDto projectDto, Long id) {
        Optional<Project> projectToUpdate = projectRepository.findById(id);

        if(projectToUpdate.isPresent()){
            Project project = projectMappers.map(projectDto);
            projectRepository.save(project);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
