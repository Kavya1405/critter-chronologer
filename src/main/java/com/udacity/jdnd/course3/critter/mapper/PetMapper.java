package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;

public class PetMapper {
    public static PetDTO toDTO(Pet pet) {
        if(pet == null) {
            return null;
        }
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getType());
        dto.setBirthDate(pet.getBirthDate());
        dto.setNotes(pet.getNotes());
        dto.setOwnerId(pet.getCustomer()!=null?pet.getCustomer().getId():null);
        return dto;
    }
    public static Pet toEntity(PetDTO dto) {
        if(dto == null) {
            return null;
        }
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setType(dto.getType());
        pet.setBirthDate(dto.getBirthDate());
        pet.setNotes(dto.getNotes());
        return pet;

    }
}
