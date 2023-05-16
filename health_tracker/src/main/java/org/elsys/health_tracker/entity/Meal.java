package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Meals")
@Data
public class Meal {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    private String name;

    @Column(name = "calories", nullable = false, columnDefinition = "INTEGER")
    private Integer calories;
}
