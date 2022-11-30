package com.musala.droneapp.models.dto;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
public record MedicationDTO(
        Long id,
        String name,
        double weight,
        String code,
        byte[] image
) {
}
