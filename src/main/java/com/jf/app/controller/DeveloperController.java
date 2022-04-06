package com.jf.app.controller;

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
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/projects/{idProject}/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody DeveloperDto developerDto, @PathVariable("idProject") Long idProject){
        developerService.create(developerDto, idProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(developerDto);
    }

    @GetMapping(path = "/{idDev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByEmail(@PathVariable("idProject") Long idProject, @PathVariable("idDev") Long idDev){
        Optional<Developer> developer = developerService.get(idDev);
        if(developer.isPresent()){
            return ResponseEntity.ok().body(developer.get());
        }
        else {
            return ResponseEntity.badRequest().body("Developer not found");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Developer>> getAllByPage(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                                      @RequestParam(required = false, name = "limit", defaultValue = "50") int limit,
                                                        @PathVariable("idProject") Long idProject, @PathVariable("idDev") Long idDev){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Developer> devs = developerService.getAll(pageable, idProject);
        return ResponseEntity.ok().body(devs);
    }

    @PutMapping(path = "/{idDev}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRole(@Valid @RequestBody DeveloperDto developerDto,
                                     @PathVariable("idProject") Long idProject,
                                     @PathVariable("idDev") Long idDev){
        return ResponseEntity.ok().body(developerService.update(developerDto, idDev, idProject));
    }

    @DeleteMapping(path = "/{idDev}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("idProject") Long idProject, @PathVariable("idDev") Long idDev) {
        developerService.delete(idDev);
        return ResponseEntity.ok().body("Developer is deleted");
    }
}
