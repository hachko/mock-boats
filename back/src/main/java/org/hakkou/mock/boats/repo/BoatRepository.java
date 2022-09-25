package org.hakkou.mock.boats.repo;

import org.hakkou.mock.boats.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat,Long>{
    
}
