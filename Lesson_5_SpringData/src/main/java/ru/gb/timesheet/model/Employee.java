package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();
}
