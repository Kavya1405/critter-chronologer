package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee getEmployee(Long id){
        return employeeRepository.findById(id).orElse(null);

    }

    public void setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setDaysAvailable(daysAvailable);

        employeeRepository.save(employee);
    }


    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek){
        return employeeRepository.findAll().stream()
                .filter(employee->employee.getDaysAvailable().contains(dayOfWeek))
                .filter(employee->employee.getSkills().containsAll(skills))
                .collect(Collectors.toList()
                );
    }
}
