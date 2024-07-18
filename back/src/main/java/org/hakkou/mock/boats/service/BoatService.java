package org.hakkou.mock.boats.service;

import org.hakkou.mock.boats.repo.BoatRepository;
import org.hakkou.mock.boats.mappers.BoatMapper;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.model.Boat;
import org.hakkou.mock.boats.service.management.BoatManagement;
import org.hakkou.mock.boats.exceptions.BoatException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoatService implements BoatManagement {    
    
    private final BoatMapper boatMapper;
    
    private final BoatRepository boatRepo;

    public BoatDto getBoat(Long id) throws BoatException {
        return boatMapper.entityToDto(boatRepo.findById(id).orElseThrow(() -> {
            return new BoatException("not found");
        }));        
    }

    public List<BoatDto> getAllBoats() {
        return boatMapper.listEntitiesIntoDtos(boatRepo.findAll());
    }

    public BoatDto saveBoat(BoatDto boat) {
        Boat entityToSave = boatMapper.dtoToEntity(boat);
        return boatMapper.entityToDto(boatRepo.save(entityToSave));
    }

    public void deleteBoat(Long id) throws BoatException {
        Optional<Boat> boatToDelete = boatRepo.findById(id);
        if(boatToDelete.isPresent()) {
            boatRepo.deleteById(id);
        } else {
            throw new BoatException("not found, cannot delete");
        }    
    }
    
    public BoatDto updateBoat(BoatDto boatDto) throws BoatException {
        if(boatExists(boatDto.getId())) {
            return boatMapper.entityToDto(boatRepo.save(boatMapper.dtoToEntity(boatDto)));
        } else {
            throw new BoatException("not found, cannot update");
        }
    }

    private boolean boatExists(Long id) {
        return boatRepo.existsById(id);
    }

}
