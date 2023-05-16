package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Genders")
@Data
public class Gender {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "gender", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    private String gender;
}
