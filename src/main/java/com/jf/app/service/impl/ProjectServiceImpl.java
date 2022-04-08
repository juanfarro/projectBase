package com.jf.app.service.impl;

import com.jf.app.entity.Project;
import com.jf.app.entity.Type;
import com.jf.app.exception.ResourceNotFoundException;
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

    @Override
    public Project create(Project project, Long idType) {

        Optional<Type> t = typeRepository.findById(idType);

        if(t.isPresent()){
            project.setType(t.get());
        } else throw new ResourceNotFoundException(Type.class, idType);

        return projectRepository.save(project);
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
    public Project update(Project projectDto, Long id) {
        Optional<Project> projectToUpdate = projectRepository.findById(id);

        if(projectToUpdate.isPresent()){
            Project project = projectToUpdate.get();
            project.setName(projectDto.getName());
            project.setTechnology(projectDto.getTechnology());
            project.setDelivery(projectDto.getDelivery());
            return projectRepository.save(project);
        }else throw new ResourceNotFoundException(Project.class, id);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
