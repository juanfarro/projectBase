package com.jf.app.service.impl;

import com.jf.app.entity.Developer;
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

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    private final ProjectRepository projectRepository;

    private final DeveloperMappers developerMappers;

    @Override
    public void create(DeveloperDto developer, Long idProject) {

        Developer dev = developerMappers.map(developer);
        dev.setProject(projectRepository.findById(idProject).get());
        developerRepository.save(dev);
    }

    @Override
    public Optional<Developer> get(Long id) {
        return developerRepository.findById(id);
    }

    @Override
    public Page<Developer> getAll(Pageable pageable, Long idProject) {
        return developerRepository.findByProject(pageable, idProject);
    }

    @Override
    public Developer update(DeveloperDto developer, Long idDev, Long idProject) {

        Optional<Developer> devToUpdate = developerRepository.findById(idDev);

        if(devToUpdate.isPresent()){
            Developer dev = developerMappers.map(developer);
            developerRepository.save(dev);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }
}
