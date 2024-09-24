package org.hakkou.mock.boats.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.exceptions.UserException;
import org.hakkou.mock.boats.mappers.UserMapper;
import org.hakkou.mock.boats.model.User;
import org.hakkou.mock.boats.repo.UserRepository;
import org.hakkou.mock.boats.service.management.UserManagement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserManagement, UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserException("User does not exist");
        } else {
            return userMapper.entityToDto(user.get());
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) throws UserException {
        if(userDto.getId() != null) {
            throw new UserException("Payload validation failed : no id for user");
        } else {
            return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(encodeUserPassword(userDto))));
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws UserException {
        if(userDto.getId() == null) {
            throw new UserException("Payload validation failed : user to update must have id");
        } else {
            Optional<User> userBeforeUpdate = userRepository.findById(userDto.getId());
            if(userBeforeUpdate.isPresent()) {
                return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(encodeUserPassword(userDto))));
            } else {
                throw new UserException("User with given id in payload does not exist");
            }            
        }
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new UserException("Must provide a valid user id to delete user");
        });
        userRepository.delete(user);
    }

    private UserDto encodeUserPassword(UserDto inputUser) {
        String encodedPassword = passwordEncoder.encode(inputUser.getPassword());
        inputUser.setPassword(encodedPassword);
        return inputUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        Optional<User> repoUser = userRepository.findByUsername(username);
        if(repoUser.isPresent()) {
            return userToUserDetails(repoUser.get());
        } else {
            throw new UsernameNotFoundException("user with username : " + username + " not found");
        }
    }

    private org.springframework.security.core.userdetails.User userToUserDetails(User entityUser) {
        Set<GrantedAuthority> authorities = entityUser.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
            entityUser.getUsername(), entityUser.getPassword(), authorities
        );
    }    
}
