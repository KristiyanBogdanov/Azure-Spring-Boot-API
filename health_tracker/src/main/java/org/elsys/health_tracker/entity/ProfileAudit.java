package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Profiles_Audit")
@Data
@NoArgsConstructor
public class ProfileAudit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "begin_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime beginDate;

    @Column(name = "end_date", columnDefinition = "DATETIME")
    private LocalDateTime endDate;

    @Column(name = "profile_id", nullable = false, columnDefinition = "BIGINT")
    private Long profileId;

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

    public ProfileAudit(Profile profile) {
        profileId = profile.getId();
        gender = profile.getGender();
        birthday = profile.getBirthday();
        height = profile.getHeight();
        weight = profile.getWeight();
        bodyFat = profile.getBodyFat();
        healthBio = profile.getHealthBio();
    }
}
