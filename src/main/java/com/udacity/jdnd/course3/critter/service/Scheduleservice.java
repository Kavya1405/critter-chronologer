package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class Scheduleservice {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    public Scheduleservice(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    public Schedule createSchedule(
            List<Long> employeeIds,
            List<Long> petIds,
            LocalDate date,
            Set<EmployeeSkill> activities
    ) {
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setActivities(activities);

        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        schedule.setEmployees(employees);

        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setPets(pets);

        return scheduleRepository.save(schedule);
    }


    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }
    public List<Schedule> getScheduleForPet(Long petId){
        return scheduleRepository.findByPetsId(petId);
    }
    public List<Schedule> getScheduleForEmployee(Long employeeId){
        return scheduleRepository.findByEmployeesId(employeeId);
    }
    public List<Schedule> getScheduleForCustomer(Long customerId){
        return scheduleRepository.findByPetsCustomerId(customerId);
    }
}
