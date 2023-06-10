package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "Profiles")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(64)")
    private Gender gender;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(name = "height", nullable = false, columnDefinition = "NUMERIC(3,2)")
    private Float height;

    @Column(name = "weight", nullable = false, columnDefinition = "NUMERIC(4,1)")
    private Float weight;

    @Column(name = "body_fat", nullable = false, columnDefinition = "NUMERIC(3,1)")
    private Float bodyFat;

    @Column(name = "health_bio", columnDefinition = "TEXT")
    private String healthBio;
}