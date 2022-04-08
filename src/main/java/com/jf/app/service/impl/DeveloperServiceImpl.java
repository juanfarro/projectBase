package com.jf.app.service.impl;

import com.jf.app.entity.Developer;
import com.jf.app.entity.Project;
import com.jf.app.exception.ResourceNotFoundException;
import com.jf.app.model.DeveloperDto;
import com.jf.app.repository.DeveloperRepository;
import com.jf.app.repository.ProjectRepository;
import com.jf.app.service.DeveloperService;
import com.jf.app.mapper.DeveloperMappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    private final ProjectRepository projectRepository;



    @Override
    public Developer create(Developer developer, Long idProject) {

        Optional<Project> p = projectRepository.findById(idProject);

        if(p.isPresent()){
            developer.setProject(p.get());
        } else throw new ResourceNotFoundException(Project.class, idProject);

        return developerRepository.save(developer);
    }

    @Override
    public Optional<Developer> get(Long idDev, Long idProject) {

        if(projectRepository.findById(idProject).isEmpty()) throw new ResourceNotFoundException(Project.class, idProject);

        return developerRepository.findById(idDev);
    }

    @Override
    public Page<Developer> getAll(Pageable pageable, Long idProject) {

        Optional<Project> p = projectRepository.findById(idProject);

        if(p.isPresent()){
            return developerRepository.findByProject(p.get(), pageable);
        } else throw new ResourceNotFoundException(Project.class, idProject);

    }

    @Override
    public Developer update(Developer developer, Long idDev, Long idProject) {

        Optional<Project> p = projectRepository.findById(idProject);

        if(p.isPresent()){
            developer.setProject(p.get());
        } else throw new ResourceNotFoundException(Project.class, idProject);

        Optional<Developer> devToUpdate = developerRepository.findById(idDev);

        if(devToUpdate.isPresent()){
            Developer dev =devToUpdate.get();
            dev.setFirstName(developer.getFirstName());
            dev.setLastName(developer.getLastName());
            dev.setSeniority(developer.getSeniority());
            return developerRepository.save(dev);
        } else throw new ResourceNotFoundException(Developer.class, idDev);
    }

    @Override
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }
}
