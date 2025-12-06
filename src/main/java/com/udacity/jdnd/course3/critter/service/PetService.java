package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }
    public Pet savePet(Pet pet,Long ownerId) {
        Customer owner=customerRepository.findById(ownerId).orElse(null);
        if(owner!=null){
            pet.setCustomer(owner);
            Pet savedPet=petRepository.save(pet);
            owner.getPets().add(savedPet);
            customerRepository.save(owner);
            return savedPet;
        }
        return null;
    }
    public Pet getPet(Long id) {
        return petRepository.findById(id).orElse(null);
    }
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByCustomerId(ownerId);
    }
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }
}
