package org.hakkou.mock.boats.service;

import org.hakkou.mock.boats.repo.BoatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.exceptions.BoatException;
import org.hakkou.mock.boats.model.Boat;
import org.hakkou.mock.boats.mappers.BoatMapperImpl;

@ExtendWith(MockitoExtension.class)
public class BoatServiceTest {

    @Mock
    private BoatRepository boatRepo;
    
    @Spy
    private BoatMapperImpl boatMapper;
    
    private BoatService boatService;

    @Test
    public void mapping_applies_whenread_methods_called() {
        // GIVEN
        boatService = new BoatService(boatMapper, boatRepo);
        List<Boat> boats = Arrays.asList(
            Boat.builder().id(1L).name("first").description("first boat").build(),
            Boat.builder().id(2L).name("second").description("second boat").build()
        );
        when(boatRepo.findAll()).thenReturn(boats);
        when(boatRepo.findById(anyLong())).thenAnswer(invoke -> getFromMock(invoke.getArgument(0), boats));

        // WHEN
        List<BoatDto> requestedBoats = boatService.getAllBoats();
        try {
            BoatDto requestedBoat = boatService.getBoat(2L);
            //THEN
            Assertions.assertEquals(2, requestedBoats.size());
            Assertions.assertEquals(1L, requestedBoats.get(0).getId());
            Assertions.assertEquals("first", requestedBoats.get(0).getName());
            Assertions.assertEquals("first boat", requestedBoats.get(0).getDescription());
            Assertions.assertEquals(2L, requestedBoat.getId());
        } catch (BoatException ex) {
            Assertions.assertTrue(false);
        }
        Assertions.assertThrows(BoatException.class, () -> {
            boatService.getBoat(3L);
        
        });      
        

    }

    private Optional<Boat> getFromMock(Long id, List<Boat> boats) {
        return boats.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
    
}
