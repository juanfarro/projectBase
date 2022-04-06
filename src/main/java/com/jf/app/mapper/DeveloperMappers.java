package com.jf.app.mapper;

import com.jf.app.entity.Developer;
import com.jf.app.model.DeveloperDto;
import org.mapstruct.Mapper;

@Mapper
public interface DeveloperMappers {

    Developer map (DeveloperDto developerDto);
}
