package com.musala.droneapp.drone;

import com.musala.droneapp.models.dto.DroneDTO;
import com.musala.droneapp.models.dto.MedicationDTO;
import com.musala.droneapp.models.entity.Drone;
import com.musala.droneapp.models.entity.Medication;

import java.util.List;
import java.util.Optional;

public interface DroneService {
    Optional<Drone> register(DroneDTO dto);
    Optional<Drone> update(DroneDTO dto);

    Optional<Drone> loadDrone(String serial, List<MedicationDTO> medicationItems);

    List<Medication> findMedications(String serial);

    List<Drone> findAvailableDrones();

    double checkBatteryLevel(String serial);

    Optional<Drone> findBySerial(String serial);
    List<Drone> findAll();
}
