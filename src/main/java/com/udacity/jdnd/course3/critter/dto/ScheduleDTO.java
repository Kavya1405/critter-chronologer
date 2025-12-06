package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class ScheduleDTO {
    private Long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;
    //getters and setters
    public Long getId() {
        return id;}
    public void setId(Long id) {
        this.id = id;
    }
    public List<Long> getEmployeeIds() {
        return employeeIds;
    }
    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
    public List<Long> getPetIds() {
        return petIds;
    }
    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Set<EmployeeSkill> getActivities() {
        return activities;

    }
    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }


}


