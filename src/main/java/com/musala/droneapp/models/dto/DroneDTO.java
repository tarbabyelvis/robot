package com.musala.droneapp.models.dto;

import com.musala.droneapp.models.enums.Model;
import com.musala.droneapp.models.enums.State;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
public record DroneDTO(
        Long id,
        String serial,
        Model model,
        double weight,
        double batteryCapacity,
        State state
) {
}
