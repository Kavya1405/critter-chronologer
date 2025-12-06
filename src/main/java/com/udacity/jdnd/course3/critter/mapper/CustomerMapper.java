package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.Collections;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer){
        if(customer == null){
            return null;
        }
        CustomerDTO dto=new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhoneNumber( customer.getPhoneNumber() );
        //dto.setNotes(customer.getNotes());
        if(customer.getPets()!=null){
            dto.setPetIds(
                    customer.getPets()
                            .stream()
                            .map(Pet::getId)
                            .collect(Collectors.toList())
            );
        }
        else{
            dto.setPetIds(Collections.emptyList());
        }
        return dto;

    }
    public static Customer toEntity(CustomerDTO dto){
        if(dto == null){
            return null;
        }
        Customer customer=new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        //customer.setNotes(dto.getNotes());
        return customer;
    }
}
