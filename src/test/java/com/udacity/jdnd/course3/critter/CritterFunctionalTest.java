package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import com.udacity.jdnd.course3.critter.controller.CustomerController;
import com.udacity.jdnd.course3.critter.controller.EmployeeController;
import com.udacity.jdnd.course3.critter.controller.PetController;
import com.udacity.jdnd.course3.critter.controller.ScheduleController;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entity.PetType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Transactional
@SpringBootTest(classes = CritterApplication.class)
public class CritterFunctionalTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;

    @Test
    public void testCreateCustomer(){
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = customerController.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerController.getAllCustomers().get(0);

        Assertions.assertEquals(newCustomer.getName(), customerDTO.getName());
        Assertions.assertEquals(newCustomer.getId(), retrievedCustomer.getId());
        Assertions.assertTrue(retrievedCustomer.getId() > 0);
    }

    @Test
    public void testCreateEmployee(){
        EmployeeDTO employeeDTO = createEmployeeDTO();
        EmployeeDTO newEmployee = employeeController.saveEmployee(employeeDTO);
        EmployeeDTO retrievedEmployee = employeeController.getEmployee(newEmployee.getId());

        Assertions.assertEquals(employeeDTO.getSkills(), newEmployee.getSkills());
        Assertions.assertEquals(newEmployee.getId(), retrievedEmployee.getId());
        Assertions.assertTrue(retrievedEmployee.getId() > 0);
    }

    @Test
    public void testAddPetsToCustomer() {
        CustomerDTO newCustomer = customerController.saveCustomer(createCustomerDTO());

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        PetDTO newPet = petController.savePet(petDTO);

        PetDTO retrievedPet = petController.getPet(newPet.getId());

        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwnerId(), newCustomer.getId());

        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(newPet.getId(), pets.get(0).getId());
        Assertions.assertEquals(newPet.getName(), pets.get(0).getName());

        CustomerDTO retrievedCustomer = customerController.getAllCustomers().get(0);
        Assertions.assertTrue(retrievedCustomer.getPetIds() != null && retrievedCustomer.getPetIds().size() > 0);
        Assertions.assertEquals(retrievedCustomer.getPetIds().get(0), retrievedPet.getId());
    }

    @Test
    public void testFindPetsByOwner() {
        CustomerDTO newCustomer = customerController.saveCustomer(createCustomerDTO());

        PetDTO pet1 = createPetDTO();
        pet1.setOwnerId(newCustomer.getId());
        PetDTO newPet1 = petController.savePet(pet1);

        PetDTO pet2 = createPetDTO();
        pet2.setType(PetType.DOG);
        pet2.setName("DogName");
        pet2.setOwnerId(newCustomer.getId());
        PetDTO newPet2 = petController.savePet(pet2);

        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(2, pets.size());
        Assertions.assertEquals(newCustomer.getId(), pets.get(0).getOwnerId());
    }

    @Test
    public void testFindOwnerByPet() {
        CustomerDTO newCustomer = customerController.saveCustomer(createCustomerDTO());

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        PetDTO newPet = petController.savePet(petDTO);

        CustomerDTO owner = customerController.getOwnerByPet(newPet.getId());

        Assertions.assertEquals(owner.getId(), newCustomer.getId());
        Assertions.assertEquals(owner.getPetIds().get(0), newPet.getId());
    }

    @Test
    public void testChangeEmployeeAvailability() {
        EmployeeDTO emp1 = employeeController.saveEmployee(createEmployeeDTO());
        Assertions.assertNull(emp1.getDaysAvailable());

        Set<DayOfWeek> availability = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
        employeeController.setAvailability(emp1.getId(),availability);

        EmployeeDTO emp2 = employeeController.getEmployee(emp1.getId());
        Assertions.assertEquals(availability, emp2.getDaysAvailable());
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        EmployeeDTO emp1 = createEmployeeDTO();
        EmployeeDTO emp2 = createEmployeeDTO();
        EmployeeDTO emp3 = createEmployeeDTO();

        emp1.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        emp2.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        emp3.setDaysAvailable(Sets.newHashSet(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

        emp1.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        emp2.setSkills(Sets.newHashSet(EmployeeSkill.PETTING, EmployeeSkill.WALKING));
        emp3.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        EmployeeDTO emp1n = employeeController.saveEmployee(emp1);
        EmployeeDTO emp2n = employeeController.saveEmployee(emp2);
        EmployeeDTO emp3n = employeeController.saveEmployee(emp3);

        EmployeeDTO er1 = new EmployeeDTO();
        er1.setDate(LocalDate.of(2019, 12, 25));
        er1.setSkills(Sets.newHashSet(EmployeeSkill.PETTING));

        Set<Long> expected1 = Sets.newHashSet(emp1n.getId(), emp2n.getId());
        Set<Long> actual1 = employeeController.findEmployeesForService(er1)
                .stream().map(EmployeeDTO::getId).collect(Collectors.toSet());

        Assertions.assertEquals(expected1, actual1);

        EmployeeDTO er2 = new EmployeeDTO();
        er2.setDate(LocalDate.of(2019, 12, 27));
        er2.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        Set<Long> expected2 = Sets.newHashSet(emp3n.getId());
        Set<Long> actual2 = employeeController.findEmployeesForService(er2)
                .stream().map(EmployeeDTO::getId).collect(Collectors.toSet());

        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        EmployeeDTO employee = createEmployeeDTO();
        employee.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        EmployeeDTO savedEmployee = employeeController.saveEmployee(employee);

        CustomerDTO savedCustomer = customerController.saveCustomer(createCustomerDTO());

        PetDTO pet = createPetDTO();
        pet.setOwnerId(savedCustomer.getId());
        PetDTO savedPet = petController.savePet(pet);

        LocalDate date = LocalDate.of(2019, 12, 25);
        List<Long> pets = Lists.newArrayList(savedPet.getId());
        List<Long> employees = Lists.newArrayList(savedEmployee.getId());
        Set<EmployeeSkill> activities = Sets.newHashSet(EmployeeSkill.PETTING);

        scheduleController.createSchedule(createScheduleDTO(pets, employees, date, activities));
        ScheduleDTO schedule = scheduleController.getAllSchedules().get(0);

        Assertions.assertEquals(activities, schedule.getActivities());
        Assertions.assertEquals(date, schedule.getDate());
        Assertions.assertEquals(employees, schedule.getEmployeeIds());
        Assertions.assertEquals(pets, schedule.getPetIds());
    }

    @Test
    public void testFindScheduleByEntities() {
        ScheduleDTO sched1 = populateSchedule(1, 2,
                LocalDate.of(2019, 12, 25),
                Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));

        ScheduleDTO sched2 = populateSchedule(3, 1,
                LocalDate.of(2019, 12, 26),
                Sets.newHashSet(EmployeeSkill.PETTING));

        ScheduleDTO sched3 = new ScheduleDTO();
        sched3.setEmployeeIds(sched1.getEmployeeIds());
        sched3.setPetIds(sched2.getPetIds());
        sched3.setActivities(Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.PETTING));
        sched3.setDate(LocalDate.of(2020, 3, 23));
        scheduleController.createSchedule(sched3);

        List<ScheduleDTO> scheds1e = scheduleController.getScheduleForEmployee(sched1.getEmployeeIds().get(0));
        compareSchedules(sched1, scheds1e.get(0));
        compareSchedules(sched3, scheds1e.get(1));

        List<ScheduleDTO> scheds2e = scheduleController.getScheduleForEmployee(sched2.getEmployeeIds().get(0));
        compareSchedules(sched2, scheds2e.get(0));

        List<ScheduleDTO> scheds1p = scheduleController.getScheduleForPet(sched1.getPetIds().get(0));
        compareSchedules(sched1, scheds1p.get(0));

        List<ScheduleDTO> scheds2p = scheduleController.getScheduleForPet(sched2.getPetIds().get(0));
        compareSchedules(sched2, scheds2p.get(0));
        compareSchedules(sched3, scheds2p.get(1));

        List<ScheduleDTO> scheds1c = scheduleController.getScheduleForCustomer(
                customerController.getOwnerByPet(sched1.getPetIds().get(0)).getId());
        compareSchedules(sched1, scheds1c.get(0));

        List<ScheduleDTO> scheds2c = scheduleController.getScheduleForCustomer(
                customerController.getOwnerByPet(sched2.getPetIds().get(0)).getId());
        compareSchedules(sched2, scheds2c.get(0));
        compareSchedules(sched3, scheds2c.get(1));
    }


    private static EmployeeDTO createEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("TestEmployee");
        employeeDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        return employeeDTO;
    }

    private static CustomerDTO createCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("TestCustomer");
        customerDTO.setPhoneNumber("123-456-789");
        return customerDTO;
    }

    private static PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestPet");
        petDTO.setType(PetType.CAT);
        return petDTO;
    }

    private static EmployeeDTO createEmployeeRequestDTO() {
        EmployeeDTO er = new EmployeeDTO();
        er.setDate(LocalDate.of(2019, 12, 25));
        er.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        return er;
    }

    private static ScheduleDTO createScheduleDTO(
            List<Long> petIds, List<Long> employeeIds, LocalDate date, Set<EmployeeSkill> activities) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setPetIds(petIds);
        dto.setEmployeeIds(employeeIds);
        dto.setDate(date);
        dto.setActivities(activities);
        return dto;
    }

    private ScheduleDTO populateSchedule(int numEmployees, int numPets,
                                         LocalDate date, Set<EmployeeSkill> activities) {

        List<Long> employeeIds = IntStream.range(0, numEmployees)
                .mapToObj(i -> createEmployeeDTO())
                .map(e -> {
                    e.setSkills(activities);
                    e.setDaysAvailable(Sets.newHashSet(date.getDayOfWeek()));
                    return employeeController.saveEmployee(e).getId();
                })
                .collect(Collectors.toList());

        CustomerDTO cust = customerController.saveCustomer(createCustomerDTO());

        List<Long> petIds = IntStream.range(0, numPets)
                .mapToObj(i -> createPetDTO())
                .map(p -> {
                    p.setOwnerId(cust.getId());
                    return petController.savePet(p).getId();
                })
                .collect(Collectors.toList());

        return scheduleController.createSchedule(createScheduleDTO(
                petIds, employeeIds, date, activities));
    }

    private static void compareSchedules(ScheduleDTO s1, ScheduleDTO s2) {
        Assertions.assertEquals(s1.getPetIds(), s2.getPetIds());
        Assertions.assertEquals(s1.getActivities(), s2.getActivities());
        Assertions.assertEquals(s1.getEmployeeIds(), s2.getEmployeeIds());
        Assertions.assertEquals(s1.getDate(), s2.getDate());
    }
}
