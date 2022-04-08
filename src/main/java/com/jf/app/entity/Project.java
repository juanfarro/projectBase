package com.jf.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", referencedColumnName = "id")
    @JsonIgnoreProperties("projects") // annotation to indicate which properties to exclude of this type of property in serialize
    //@JsonManagedReference //annotation to indicate the "parent" of a relationship
    private Type type;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("project")
    //@JsonBackReference //annotation to exclude when obj class is serialize, usually indicate the "children" of a relationship
    private Collection<Developer> developers;
}
