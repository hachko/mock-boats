package org.hakkou.mock.boats.service;

import org.hakkou.mock.boats.repo.BoatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.exceptions.BoatException;
import org.hakkou.mock.boats.model.Boat;
import org.hakkou.mock.boats.mappers.BoatMapperImpl;

@ExtendWith(MockitoExtension.class)
public class BoatServiceTest {

    @Mock
    private BoatRepository boatRepo;
    
    @Mock
    private BoatMapperImpl boatMapper;
    
    @InjectMocks
    private BoatService boatService;

    private List<Boat> mockedBoats = new ArrayList<>(Arrays.asList(
        Boat.builder().id(1L).name("first").description("first boat").build(),
        Boat.builder().id(2L).name("second").description("second boat").build()
    ));

    @Test
    public void getAllBoatsCallsFindAllInRepo() {        

        // WHEN
       boatService.getAllBoats();

        //THEN
        verify(boatRepo).findAll();
    }


    @Test
    public void exception_handled_when_getBoat_called() {    
        when(boatRepo.findById(anyLong())).thenAnswer(invoke -> getFromMock(invoke.getArgument(0)));    

        Assertions.assertDoesNotThrow(() -> {
            boatService.getBoat(1L);
        });
        Assertions.assertThrows(BoatException.class, () -> {
            boatService.getBoat(3L);        
        });
    }

    private Optional<Boat> getFromMock(Long id) {
        return mockedBoats.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
    
    @Test
    public void exception_handled_when_addBoat_called() {
        // GIVEN 
        when(boatRepo.save(any(Boat.class))).thenAnswer(invoke -> saveInMock(invoke.getArgument(0)));        
        when(boatMapper.dtoToEntity(any(BoatDto.class))).thenCallRealMethod();
        // WHEN / THEN
        try {
            boatService.addBoat(BoatDto.builder()
                .id(null)
                .name("added")
                .description("added boat")
            .build());
            Assertions.assertEquals(3, mockedBoats.size());
            Assertions.assertEquals(3L, mockedBoats.get(2).getId());
            Assertions.assertEquals("added", mockedBoats.get(2).getName());
            Assertions.assertEquals("added boat", mockedBoats.get(2).getDescription());
        } catch (Exception ex) {
            Assertions.assertTrue(false);
        }

        // throws exception if boat added has an id
        Assertions.assertThrows(BoatException.class, () -> {
            boatService.addBoat(BoatDto.builder()
                .id(1L)
                .name("added")
                .description("added boat")
            .build());
        });
    }

    private Boat saveInMock(Boat boatToSave) {
        getFromMock(boatToSave.getId()).ifPresentOrElse(b -> {
            b.setName(boatToSave.getName());
            b.setDescription(boatToSave.getDescription());          
        }, () -> {
            boatToSave.setId(Long.valueOf(mockedBoats.size() + 1));
            mockedBoats.add(boatToSave);
        });
        return boatToSave;
    }

    @Test
    public void exception_handled_when_deleteBoat_called() {
        // GIVEN         
        when(boatRepo.findById(anyLong())).thenAnswer(invoke -> getFromMock(invoke.getArgument(0)));
        
        // WHEN / THEN
        Assertions.assertThrows(BoatException.class, () -> {
            boatService.deleteBoat(3L);
        });
        try {
            boatService.deleteBoat(1L);
            // THEN
            verify(boatRepo).deleteById(1L);
        } catch (Exception ex ) {
            Assertions.assertTrue(false);
        }
    }
}
