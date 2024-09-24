package org.hakkou.mock.boats.mappers;

import java.util.List;

import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);
    List<UserDto> listEntitiesIntoDtos(List<User> usersList);
    List<User> listDtoIntoEntities(List<UserDto> userDtosList);
}
