package org.hakkou.mock.boats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoatDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
