package org.hakkou.mock.boats.controller;

import java.util.List;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.exceptions.BoatException;
import org.hakkou.mock.boats.service.management.BoatManagement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("boats")
@CrossOrigin
@AllArgsConstructor
public class BoatController {
    
    private final BoatManagement boatService;

    @GetMapping("/read/all")
    public List<BoatDto> getAllBoats() {
        return boatService.getAllBoats();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoat(@PathVariable Long id) {
        try {
            boatService.deleteBoat(id);
        } catch (BoatException bex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,bex.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public BoatDto getById(@PathVariable Long id) {
        try {
            return boatService.getBoat(id);
        } catch (BoatException bex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,bex.getMessage());
        }
    }

    @PostMapping("/create")
    public BoatDto addBoat(@RequestBody  BoatDto boat) {
        if(boat.getId() != null) {            
            return null;
        } else {
            return boatService.saveBoat(boat);
        }
    }

    @PutMapping("/update")
    public BoatDto updateBoat(@RequestBody BoatDto boat) {
       try {
        return boatService.updateBoat(boat);
       } catch (BoatException bex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,bex.getMessage());
       }
    }

}
