package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Profiles")
@Data
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false, referencedColumnName = "id")
    private Gender gender;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private Date birthday;

    @Column(name = "height", nullable = false, columnDefinition = "NUMERIC(3,2)")
    private float height;

    @Column(name = "weight", nullable = false, columnDefinition = "NUMERIC(4,1)")
    private float weight;

    @Column(name = "body_fat", nullable = false, columnDefinition = "NUMERIC(3,1)")
    private float bodyFat;

    @Column(name = "health_bio", columnDefinition = "TEXT")
    private String healthBio;
}