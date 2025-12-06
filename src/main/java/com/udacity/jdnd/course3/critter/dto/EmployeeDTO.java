package com.udacity.jdnd.course3.critter.dto;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
    private LocalDate date;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

}

