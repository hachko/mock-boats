package org.hakkou.mock.boats.mappers;

import java.util.List;

import java.util.Arrays;

import org.hakkou.mock.boats.dto.RoleDto;
import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.model.Role;
import org.hakkou.mock.boats.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserMapperTest {
    private UserMapper userMapper = new UserMapperImpl();

    @Test
    public void userMapsToUserDto() {
        User user = User.builder()
            .id(1L)
            .username("firstUser")
            .password("firstPassword")
            .roles(Arrays.asList(Role.builder().name("firstRole").build()))
        .build();

        UserDto userDto = userMapper.entityToDto(user);
        Assertions.assertEquals(user.getId(), userDto.getId());
        Assertions.assertEquals(user.getUsername(), userDto.getUsername());
        Assertions.assertEquals(user.getPassword(), userDto.getPassword());
        Assertions.assertEquals(1, userDto.getRoles().size());
        Assertions.assertEquals("firstRole", userDto.getRoles().get(0).getName());
    }

    @Test
    public void userDtoMapsToUser() {
        UserDto userDto = UserDto.builder()
            .id(1L)
            .username("firstUser")
            .password("firstPassword")
            .roles(Arrays.asList(
                RoleDto.builder().name("firstRole").build(),
                RoleDto.builder().name("secondRole").build()
            ))           
        .build();

        User user = userMapper.dtoToEntity(userDto);
        Assertions.assertEquals(userDto.getId(), user.getId());
        Assertions.assertEquals(userDto.getUsername(), user.getUsername());
        Assertions.assertEquals(userDto.getPassword(), user.getPassword());
        Assertions.assertEquals(2, userDto.getRoles().size());
        Assertions.assertEquals("firstRole", userDto.getRoles().get(0).getName());
        Assertions.assertEquals("secondRole", userDto.getRoles().get(1).getName());
    }

    @Test
    public void userListMapsToUserDtoList() {
        List<User> userList = Arrays.asList(
            User.builder().id(1L).username("one").password("pass1").build(),
            User.builder().id(2L).username("two").password("pass2").build()
        );

        List<UserDto> userDtoList = userMapper.listEntitiesIntoDtos(userList);
        Assertions.assertEquals(2, userDtoList.size());
        Assertions.assertEquals(1L,userDtoList.get(0).getId());
        Assertions.assertEquals("one", userDtoList.get(0).getUsername());        
        Assertions.assertEquals("pass1", userDtoList.get(0).getPassword());
        Assertions.assertEquals(2L, userDtoList.get(1).getId());
        Assertions.assertEquals("two", userDtoList.get(1).getUsername());
        Assertions.assertEquals("pass2", userDtoList.get(1).getPassword());

    }

    @Test
    public void userDtoListMapsToUserList() {
        List<UserDto> userDtoList = Arrays.asList(
            UserDto.builder().id(1L).username("one").password("pass1").build(),
            UserDto.builder().id(2L).username("two").password("pass2").build()
        );

        List<User> userList = userMapper.listDtoIntoEntities(userDtoList);

        Assertions.assertEquals(2, userList.size());
        Assertions.assertEquals(1L,userList.get(0).getId());
        Assertions.assertEquals("one", userList.get(0).getUsername());        
        Assertions.assertEquals("pass1", userList.get(0).getPassword());
        Assertions.assertEquals(2L, userList.get(1).getId());
        Assertions.assertEquals("two", userList.get(1).getUsername());
        Assertions.assertEquals("pass2", userList.get(1).getPassword());
    }
}
