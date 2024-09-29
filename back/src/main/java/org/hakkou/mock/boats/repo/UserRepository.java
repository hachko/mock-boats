package org.hakkou.mock.boats.repo;

import java.util.Optional;

import org.hakkou.mock.boats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> { 
    Optional<User> findByUsername(String username);
}
