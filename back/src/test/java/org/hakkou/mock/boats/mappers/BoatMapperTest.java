package org.hakkou.mock.boats.mappers;

import java.util.Arrays;
import java.util.List;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.model.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoatMapperTest {
    private BoatMapper boatMapper = new BoatMapperImpl();

    @Test
    public void entityMapsToDto() {
        Boat boat = Boat.builder()
            .id(1L)
            .name("single")
            .description("single boat")
            .imageUrl("http://image.url.com")
        .build();
        BoatDto boatDto = boatMapper.entityToDto(boat);
        Assertions.assertEquals(1L, boatDto.getId());
        Assertions.assertEquals("single", boatDto.getName());
        Assertions.assertEquals("single boat", boatDto.getDescription());
        Assertions.assertEquals("http://image.url.com", boatDto.getImageUrl());
    }

    @Test
    public void dtoMapsToEntity() {
        BoatDto boatDto = BoatDto.builder()
            .id(1L)
            .name("single")
            .description("single boat")
            .imageUrl("http://image.url.com")
        .build();
        Boat boat = boatMapper.dtoToEntity(boatDto);
        Assertions.assertEquals(1L, boat.getId());
        Assertions.assertEquals("single", boat.getName());
        Assertions.assertEquals("single boat", boat.getDescription());
        Assertions.assertEquals("http://image.url.com", boat.getImageUrl());   
    }

    @Test
    public void entityListMapsToDtoList() {
        List<Boat> boats = Arrays.asList(
            Boat.builder()
                .id(1L)
                .name("first")
                .description("first boat")
                .imageUrl("http//first.image.com")
            .build(),
            Boat.builder()
                .id(2L)
                .name("second")
                .description("second boat")
                .imageUrl("http://second.image.com")
            .build()
        );

        List<BoatDto> boatDtos = boatMapper.listEntitiesIntoDtos(boats);

        Assertions.assertEquals(2, boatDtos.size());
        Assertions.assertEquals(1L, boatDtos.get(0).getId());
        Assertions.assertEquals("second", boatDtos.get(1).getName());
        Assertions.assertEquals("first boat", boatDtos.get(0).getDescription());
        Assertions.assertEquals("http://second.image.com", boatDtos.get(1).getImageUrl());
    }

    @Test
    public void dtoListMapsToEntitList() {
        List<BoatDto> boatsDtos = Arrays.asList(
            BoatDto
            .builder()
                .id(1L)
                .name("first")
                .description("first boat")
                .imageUrl("http//first.com")
            .build(),
            BoatDto
            .builder()
                .id(2L)
                .name("second")
                .description("second boat")
                .imageUrl("http://second.com")
            .build()
        );

        List<Boat> boats = boatMapper.listDtosIntoEntities(boatsDtos);

        Assertions.assertEquals(2, boats.size());
        Assertions.assertEquals(1L, boats.get(0).getId());
        Assertions.assertEquals("second", boats.get(1).getName());
        Assertions.assertEquals("first boat", boats.get(0).getDescription());
        Assertions.assertEquals("http://second.com", boats.get(1).getImageUrl());
    }
}
