package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElse(null);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer getOwnerByPet(Long petId){
        Pet pet=petRepository.findById(petId).orElse(null);
        if(pet==null){
            return null;
        }
        return pet.getCustomer();
    }

}
