package org.hakkou.mock.boats.controller;

import java.util.List;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.service.management.BoatManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boats")
@CrossOrigin
public class BoatController {
    @Autowired
    private BoatManagement boatService;

    @GetMapping("/read/all")
    public List<BoatDto> getAllBoats() {
        return boatService.getAllBoats();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoat(@PathVariable Long id) {
        boatService.deleteBoat(id);
    }

    @GetMapping("/read/{id}")
    public BoatDto getById(@PathVariable Long id) {
        return boatService.getBoat(id);
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
    public BoatDto updateBoat(@RequestBody BoatDto boat){
        if(!boatService.boatExists(boat.getId())) {
            //TODO add exception handling
            return null;
        } else {
            return boatService.saveBoat(boat);
        }
    }

}
