package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer= CustomerMapper.toEntity(customerDTO);
        Customer saved=customerService.saveCustomer(customer);
        return CustomerMapper.toDTO(saved);
    }
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable Long customerId) {
        return CustomerMapper.toDTO((customerService.getCustomer(customerId)));
    }
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId) {
        Customer owner=customerService.getOwnerByPet(petId);
        return CustomerMapper.toDTO(owner);
    }


}
