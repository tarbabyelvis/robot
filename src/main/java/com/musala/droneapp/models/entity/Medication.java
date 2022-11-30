package com.musala.droneapp.models.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Entity
@Data
public class Medication {
    @Pattern(regexp = "[\\w\\d_-]+",message = "Error on code validation")
    private String name;
    private double weight;
    @Pattern(regexp = "[A-Z\\d_]+",message = "Error on code validation")
    private String code;
    private byte[] image;
}
