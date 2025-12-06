package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    public  PetController(PetService petService) {
        this.petService = petService;
    }
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet= PetMapper.toEntity(petDTO);
        Pet saved=petService.savePet(pet,petDTO.getOwnerId());
        return PetMapper.toDTO(saved);

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        return PetMapper.toDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAllPets()
                .stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList());

    }
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwner(ownerId)
                .stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList());
    }
}
