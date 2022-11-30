package com.musala.droneapp.models.entity;

import com.musala.droneapp.models.enums.Model;
import com.musala.droneapp.models.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import lombok.Data;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Entity
@Data
public class Drone extends BaseEntity {
    @Column(nullable = false)
    private String serial;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Model model;
    @DecimalMax(value = "500")
    private double weight;
    private double batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
}
