package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.Collections;
import java.util.stream.Collectors;

public class ScheduleMapper {
    public static ScheduleDTO toDTO(Schedule schedule) {
        if (schedule == null) {
            return null;
        }
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate());
        dto.setActivities(schedule.getActivities());
        if(schedule.getEmployees()!=null){
            dto.setEmployeeIds(schedule.getEmployees()
                    .stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList()));
        }
        else{
            dto.setEmployeeIds(Collections.emptyList());
        }
        if(schedule.getPets()!=null){
            dto.setPetIds(schedule.getPets()
                    .stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList()));
        }
        else{
            dto.setPetIds(Collections.emptyList());
        }
        return dto;
    }
}
