package com.jf.app.repository;

import com.jf.app.entity.Developer;
import com.jf.app.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Page<Developer> findByProject(Project project, Pageable pageable );
}
