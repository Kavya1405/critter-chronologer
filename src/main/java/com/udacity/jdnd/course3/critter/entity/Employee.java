package com.udacity.jdnd.course3.critter.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="employee")

public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection(targetClass= EmployeeSkill.class)
    @CollectionTable(name="employee_skills",joinColumns = @JoinColumn(name="employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection(targetClass =DayOfWeek .class)
    @CollectionTable(name="employee_days_available",joinColumns = @JoinColumn(name="employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
    //getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }
    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

}
