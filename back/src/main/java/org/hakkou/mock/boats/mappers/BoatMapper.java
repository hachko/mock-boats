package org.hakkou.mock.boats.mappers;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.model.Boat;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BoatMapper {

    BoatDto entityToDto(Boat boat);

    Boat dtoToEntity(BoatDto boatDto);

    List<BoatDto> listEntitiesIntoDtos(List<Boat> boats);

    List<Boat> listDtosIntoEntities(List<BoatDto> boatDtos);

}
