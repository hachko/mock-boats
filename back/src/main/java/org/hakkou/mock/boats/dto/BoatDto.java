package org.hakkou.mock.boats.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoatDto {
    private Long id;
    private String name;
    private String description;    
}
