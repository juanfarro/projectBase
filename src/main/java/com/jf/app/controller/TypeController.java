package com.jf.app.controller;

import com.jf.app.service.TypeService;
import com.jf.app.entity.Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/types")
public class TypeController {

    private final TypeService typeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Type>> getAllByPage(@RequestParam(required = false, name = "page", defaultValue = "0") int page,
                                                   @RequestParam(required = false, name = "limit", defaultValue = "50") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Type> types = typeService.getAll(pageable);
        return ResponseEntity.ok().body(types);
    }
}
