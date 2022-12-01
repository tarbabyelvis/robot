package com.musala.droneapp.models.dto;

import jakarta.validation.constraints.Pattern;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
public record MedicationDTO(
        Long id,
        @Pattern(regexp = "[\\w\\d_-]+",message = "Error on name validation")
        String name,
        double weight,
        @Pattern(regexp = "[A-Z\\d_]+",message = "Error on code validation")
        String code,
        byte[] image
) {
}
