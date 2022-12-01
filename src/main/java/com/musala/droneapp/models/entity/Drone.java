package com.musala.droneapp.models.entity;

import com.musala.droneapp.models.enums.Model;
import com.musala.droneapp.models.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Drone extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String serial;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Model model;
    @DecimalMax(value = "500")
    private double weightLimit;
    private double batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state = State.IDLE;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Medication> medications = new HashSet<>();
}
