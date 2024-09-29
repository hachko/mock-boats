package org.hakkou.mock.boats.controller;

import org.hakkou.mock.boats.dto.UserDto;
import org.hakkou.mock.boats.exceptions.UserException;
import org.hakkou.mock.boats.service.management.UserManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserManagement userService;

    @GetMapping("/read/{id}")    
    public UserDto getUser(@PathVariable Long id) {
        try {
            return userService.getUser(id);
        } catch (UserException usEx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, usEx.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public UserDto updateUSer(@PathVariable Long id, @RequestBody UserDto updatedValue) {
        try {
            if(!updatedValue.getId().equals(id)) {
                throw new UserException("User id must be the same in url and payload");
            }
            return userService.updateUser(updatedValue);
        } catch (UserException usEx) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, usEx.getMessage());
        }
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userValue) {
        try {
            return userService.createUser(userValue);
        } catch (UserException usEx) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, usEx.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (UserException usEx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, usEx.getMessage());
        }
    }
}
