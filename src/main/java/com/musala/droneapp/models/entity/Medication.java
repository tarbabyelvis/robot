package com.musala.droneapp.models.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Medication extends BaseEntity{
    @Pattern(regexp = "[\\w\\d_-]+",message = "Error on code validation")
    private String name;
    private double weight;
    @Pattern(regexp = "[A-Z\\d_]+",message = "Error on code validation")
    private String code;
    @Basic(fetch= FetchType.LAZY)
    private byte[] image;
}
