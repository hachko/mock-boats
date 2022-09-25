package org.hakkou.mock.boats.service.management;

import java.util.List;

import org.hakkou.mock.boats.dto.BoatDto;

public interface BoatManagement {
    public List<BoatDto> getAllBoats();

    public BoatDto getBoat(Long id);

    public BoatDto saveBoat(BoatDto boat);

    public void deleteBoat(Long id);

    public boolean boatExists(Long id);
}
