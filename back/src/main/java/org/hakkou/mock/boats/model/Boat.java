package org.hakkou.mock.boats.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Boats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "imageurl")
    String imageUrl;

    public boolean equals(Boat other) {
        return other != null && other.getId() == this.getId();
    }
}
