package org.hakkou.mock.boats.service;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.exceptions.UserException;
import org.hakkou.mock.boats.mappers.UserMapperImpl;
import org.hakkou.mock.boats.model.User;
import org.hakkou.mock.boats.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserMapperImpl userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    private List<User> mockedUsers = Arrays.asList(
        User.builder().id(1L).username("one").password("pass1").build(),
        User.builder().id(2L).username("two").password("pass2").build()
    );

    @Test
    public void repo_findAll_called_when_getUsers_executed() {
        // WHEN 
        try {
            userService.getUsers();
            
        } catch(UserException ex) {
            // nothing to test here
        }
        verify(userRepository).findAll();
    }

    @Test
    public void exception_handled_when_getUser_called() {
        // Given
        when(userRepository.findById(anyLong())).thenAnswer(inv -> getFromMock(inv.getArgument(0)));
        
        // When / Then        
        Assertions.assertDoesNotThrow(() -> {
            userService.getUser(1L);
        });

        UserException usEx = Assertions.assertThrows(UserException.class, () -> {
            userService.getUser(3L);
        });
        Assertions.assertEquals(usEx.getMessage(), "User does not exist");
    }

    private Optional<User> getFromMock(Long id) {
        return mockedUsers.stream().filter(u -> u.getId().equals(id)).findAny();
    }

    @Test
    public void exceptions_handled_when_updateUser_called() {
        // Given
        when(userRepository.findById(anyLong())).thenAnswer(inv -> getFromMock(inv.getArgument(0)));
        
        // When / Then
        UserException usExMissingId = Assertions.assertThrows(UserException.class, () -> {
            userService.updateUser(UserDto.builder()
                .id(null)
                .username("doesnt")
                .password("matter")
            .build());
        });
        Assertions.assertEquals("Payload validation failed : user to update must have id", 
        usExMissingId.getMessage());

        UserException usExDoesntExist = Assertions.assertThrows(UserException.class, () -> {
            userService.updateUser(UserDto.builder()
                .id(3L)
                .username("doesnt")
                .password("matter")
            .build());
        });
        Assertions.assertEquals("User with given id in payload does not exist", 
        usExDoesntExist.getMessage());
        
        UserDto legitUserToSave = UserDto.builder()
            .id(1L)
            .username("updated")
            .password("value")
        .build();
        try {
            userService.updateUser(legitUserToSave);
            verify(userRepository).save(userMapper.dtoToEntity(legitUserToSave));
        } catch (UserException usEx) {
            return;
        }
        
    }

    @Test
    public void exception_handled_when_deleteUser_called() {
        // Given
        when(userRepository.findById(anyLong())).thenAnswer(inv -> getFromMock(inv.getArgument(0)));
        
        // when / then
        Assertions.assertDoesNotThrow(() -> {
            userService.deleteUser(1L);
        });

        UserException usEx = Assertions.assertThrows(UserException.class, () -> {
            userService.deleteUser(3L);
        });
        Assertions.assertEquals("Must provide a valid user id to delete user", usEx.getMessage());
    }

    @Test
    public void exception_handled_when_createUser_called() {
        // Given
        UserDto userToAdd = UserDto.builder()
            .id(1L)
            .username("one")
            .password("pass1")
        .build();

        UserException usEx = Assertions.assertThrows(UserException.class, () -> {
            userService.createUser(userToAdd);
        });
        Assertions.assertEquals("Payload validation failed : no id for user", usEx.getMessage());
    }
}
