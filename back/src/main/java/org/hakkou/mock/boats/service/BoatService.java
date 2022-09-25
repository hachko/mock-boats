package org.hakkou.mock.boats.service;

import org.hakkou.mock.boats.repo.BoatRepository;
import org.hakkou.mock.boats.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.model.Boat;
import org.hakkou.mock.boats.service.management.BoatManagement;
import java.util.List;

@Service
public class BoatService implements BoatManagement {
    
    @Autowired
    private MapUtils mapUtils;

    @Autowired
    private BoatRepository boatRepo;

    public BoatDto getBoat(Long id) {
        return mapUtils.getMappedObject(boatRepo.findById(id).get(), BoatDto.class);
    }

    public List<BoatDto> getAllBoats() {
        return mapUtils.getMappedList(boatRepo.findAll(), BoatDto.class);
    }

    public BoatDto saveBoat(BoatDto boat) {
        Boat entityToSave = mapUtils.getMappedObject(boat, Boat.class);
        return mapUtils.getMappedObject(boatRepo.save(entityToSave), BoatDto.class);
    }

    public void deleteBoat(Long id) {
        boatRepo.deleteById(id);
    }

    public boolean boatExists(Long id) {
        return boatRepo.existsById(id);
    }

}
