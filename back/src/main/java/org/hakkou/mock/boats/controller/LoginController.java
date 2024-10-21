package org.hakkou.mock.boats.controller;

import java.util.List;

import org.hakkou.mock.boats.dto.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class LoginController {

    @GetMapping
    public AuthResponse login(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).toList();
        return new AuthResponse(true, roles);
    }

}
