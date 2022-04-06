package com.jf.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String technology;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date delivery;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private Type type;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Collection<Developer> developers = new ArrayList<>();
}
