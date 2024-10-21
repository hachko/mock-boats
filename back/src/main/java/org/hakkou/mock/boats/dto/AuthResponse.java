package org.hakkou.mock.boats.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    @JsonProperty("isValid")
    private boolean isValid;
    private List<String> roles;
    
}
