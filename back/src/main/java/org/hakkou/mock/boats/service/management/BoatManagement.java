package org.hakkou.mock.boats.service.management;

import java.util.List;

import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.exceptions.BoatException;

public interface BoatManagement {
    public List<BoatDto> getAllBoats();

    public BoatDto getBoat(Long id) throws BoatException;

    public BoatDto saveBoat(BoatDto boat);

    public void deleteBoat(Long id) throws BoatException;

    public BoatDto updateBoat(BoatDto boat) throws BoatException;
}
