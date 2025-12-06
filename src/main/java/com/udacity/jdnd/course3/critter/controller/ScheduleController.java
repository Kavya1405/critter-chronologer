package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.PetType;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.Scheduleservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final Scheduleservice scheduleservice;
    public ScheduleController(Scheduleservice scheduleservice){
        this.scheduleservice = scheduleservice;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule saved = scheduleservice.createSchedule(
                scheduleDTO.getEmployeeIds(),
                scheduleDTO.getPetIds(),
                scheduleDTO.getDate(),
                scheduleDTO.getActivities()
        );

        return ScheduleMapper.toDTO(saved);
    }


    @GetMapping
    public List<ScheduleDTO> getAllSchedules(){
        return scheduleservice.getAllSchedules()
                .stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/pet/{petId}")
    public List <ScheduleDTO> getScheduleForPet(@PathVariable Long petId){
        return scheduleservice.getScheduleForPet(petId)
                .stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId){
        return scheduleservice.getScheduleForEmployee(employeeId)
                .stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId){
        return scheduleservice.getScheduleForCustomer(customerId)
                .stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
}
