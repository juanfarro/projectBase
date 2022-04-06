package com.jf.app.mapper;

import com.jf.app.entity.Project;
import com.jf.app.model.ProjectDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMappers {

    Project map (ProjectDto projectDto);
}
