package com.jf.app.controller;

import com.jf.app.exception.ResourceNotFoundException;
import com.jf.app.mapper.DeveloperMappers;
import com.jf.app.model.DeveloperDto;
import com.jf.app.service.DeveloperService;
import com.jf.app.entity.Developer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/projects")
public class DeveloperController {

    private final DeveloperService developerService;

    private final DeveloperMappers developerMappers;

    @PostMapping(path = "/{idProject}/developers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody DeveloperDto developerDto, @PathVariable Long idProject){

        return ResponseEntity.status(HttpStatus.CREATED).body(developerService.create(developerMappers.map(developerDto), idProject));
    }

    @GetMapping(path = "/{idProject}/developers/{idDev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable Long idProject, @PathVariable Long idDev){
        Optional<Developer> developer = developerService.get(idDev, idProject);
        if(developer.isPresent()){
            return ResponseEntity.ok().body(developer.get());
        } else throw new ResourceNotFoundException(Developer.class, idDev);
    }

    @GetMapping(path = "/{idProject}/developers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Developer>> getAllByPage(@RequestParam(required = false, name = "page", defaultValue = "0") int page,
                                                      @RequestParam(required = false, name = "limit", defaultValue = "50") int limit,
                                                        @PathVariable("idProject") Long idProject){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Developer> devs = developerService.getAll(pageable, idProject);
        return ResponseEntity.ok().body(devs);
    }

    @PutMapping(path = "/{idProject}/developers/{idDev}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDev(@Valid @RequestBody DeveloperDto developerDto,
                                     @PathVariable("idProject") Long idProject,
                                     @PathVariable("idDev") Long idDev){
        return ResponseEntity.ok().body(developerService.update(developerMappers.map(developerDto), idDev, idProject));
    }

    @DeleteMapping(path = "/{idProject}/developers/{idDev}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("idProject") Long idProject, @PathVariable("idDev") Long idDev) {
        developerService.delete(idDev);
        return ResponseEntity.ok().body("Developer is deleted");
    }
}
