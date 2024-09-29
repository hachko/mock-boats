package org.hakkou.mock.boats.service.management;

import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.exceptions.UserException;

public interface UserManagement {
    UserDto getUser(Long id) throws UserException;
    UserDto createUser(UserDto userDto) throws UserException;
    UserDto updateUser(UserDto userDto) throws UserException;
    void deleteUser(Long id) throws UserException;
}
