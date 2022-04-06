package com.jf.app.service.impl;

import com.jf.app.entity.Type;
import com.jf.app.repository.TypeRepository;
import com.jf.app.service.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public void create(Type type) {
        typeRepository.save(type);
    }

    @Override
    public Optional<Type> get(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Page<Type> getAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type update(Type type) {
        return null;
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }
}
