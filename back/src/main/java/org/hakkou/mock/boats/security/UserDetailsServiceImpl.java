package org.hakkou.mock.boats.security;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hakkou.mock.boats.model.User;
import org.hakkou.mock.boats.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

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
