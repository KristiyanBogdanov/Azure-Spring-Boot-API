package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "MealsStats")
@Data
public class MealStat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false ,referencedColumnName = "id")
    private Meal meal;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private Date date;
}
