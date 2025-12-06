package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee saved = employeeService.saveEmployee(employee);
        return EmployeeMapper.toDTO(saved);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable Long employeeId) {
        return EmployeeMapper.toDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/{employeeId}/availability")
    public void setAvailability(@PathVariable Long employeeId,@RequestBody Set<DayOfWeek> days) {
        employeeService.setAvailability(employeeId,days);
    }

    @PostMapping("/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeDTO requestDTO) {
        Set<EmployeeSkill> skills = requestDTO.getSkills();
        LocalDate date = requestDTO.getDate();
        DayOfWeek day = date.getDayOfWeek();

        return employeeService.findEmployeesForService(skills, day)
                .stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }





}
