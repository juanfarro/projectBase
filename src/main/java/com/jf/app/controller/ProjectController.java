package com.jf.app.controller;

import com.jf.app.entity.Project;
import com.jf.app.entity.Type;
import com.jf.app.model.ProjectDto;
import com.jf.app.service.ProjectService;
import com.jf.app.service.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TypeService typeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody ProjectDto projectDto){

        if(typeService.getAll(PageRequest.of(1, 10)).getContent().isEmpty()){
            Type t1 = new Type();
            t1.setName("Type one");

            Type t2 = new Type();
            t2.setName("Type two");

            typeService.create(t1);
            typeService.create(t2);
        }

        projectService.create(projectDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(projectDto);
    }

    @GetMapping(path = "/{idProject}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("idProject") Long idProject){
        Optional<Project> project = projectService.get(idProject);
        if(project.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(project.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Project>> getAllByPage(@RequestParam(required = false, name = "page", defaultValue = "0") int page,
                                                   @RequestParam(required = false, name = "limit", defaultValue = "50") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Project> projects = projectService.getAll(pageable);
        return ResponseEntity.ok().body(projects);
    }

    @PutMapping(path = "/{idProject}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRole(@Valid @RequestBody ProjectDto projectDto, @PathVariable("idProject") Long idProject){
        return ResponseEntity.ok().body(projectService.update(projectDto, idProject));
    }

    @DeleteMapping(path = "/{idProject}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("idProject") Long idProject) {
        projectService.delete(idProject);
        return ResponseEntity.ok().body("Project is deleted");
    }
}
