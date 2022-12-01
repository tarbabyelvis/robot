package com.musala.droneapp.drone;

import com.musala.droneapp.medication.MedicationConvertor;
import com.musala.droneapp.models.dto.DroneDTO;
import com.musala.droneapp.models.entity.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Component
@RequiredArgsConstructor
public class DroneConvertor {
    private final MedicationConvertor medicationConvertor;

    public Drone toEntity(DroneDTO dto) {
        Drone drone = new Drone();
        drone.setId(dto.id());
        drone.setSerial(dto.serial());
        drone.setModel(dto.model());
        drone.setState(dto.state());
        drone.setBatteryCapacity(dto.batteryCapacity());
        drone.setWeightLimit(dto.weight());
        drone.setMedications(dto.medications().stream().map(medicationConvertor::toEntity).collect(Collectors.toSet()));
        return drone;
    }

    public DroneDTO toDTO(Drone drone) {
        return new DroneDTO(
                drone.getId(),
                drone.getSerial(),
                drone.getModel(),
                drone.getWeightLimit(),
                drone.getBatteryCapacity(),
                drone.getState(),
                drone.getMedications().stream().map(medicationConvertor::toDTO).collect(Collectors.toSet())
        );
    }
}
