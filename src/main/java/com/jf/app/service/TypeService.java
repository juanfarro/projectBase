package com.jf.app.service;

import com.jf.app.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TypeService {

    void create(Type type);

    Optional<Type> get(Long id);

    Page<Type> getAll(Pageable pageable);

    Type update(Type type);

    void delete(Long id);
}
