package com.musala.droneapp.drone;

import com.musala.droneapp.medication.MedicationConvertor;
import com.musala.droneapp.models.dto.DroneDTO;
import com.musala.droneapp.models.dto.MedicationDTO;
import com.musala.droneapp.models.entity.Drone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@RestController
@RequestMapping("/drone")
@Slf4j
@RequiredArgsConstructor
public class DispatchController {
    private final DroneService service;
    private final DroneConvertor convertor;
    private final MedicationConvertor medicationConvertor;

    @PostMapping("/register")
    public ResponseEntity<DroneDTO> register(@Validated @RequestBody DroneDTO droneDTO) {
        log.debug("register drone {}", droneDTO);
        Drone registeredDrone = service.register(droneDTO)
                .orElseThrow(() -> new RuntimeException("Failed to register drone"));
        return new ResponseEntity<>(convertor.toDTO(registeredDrone), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<DroneDTO> update(@Validated @RequestBody DroneDTO droneDTO) {
        log.debug("update drone {}", droneDTO);
        Drone updated = service.update(droneDTO)
                .orElseThrow(() -> new RuntimeException("Failed to update drone"));
        return new ResponseEntity<>(convertor.toDTO(updated), HttpStatus.OK);
    }

    @PutMapping("/load/{serial}")
    public ResponseEntity<DroneDTO> loadDrone(@PathVariable String serial, @Validated @RequestBody List<MedicationDTO> medications) {
        log.debug("Loading drone {} with medications", serial);
        Drone loadedDrone = service.loadDrone(serial, medications)
                .orElseThrow(() -> new RuntimeException("Failed to load drone"));
        return new ResponseEntity<>(convertor.toDTO(loadedDrone), HttpStatus.OK);
    }

    @GetMapping("/findMedications/{serial}")
    public List<MedicationDTO> findMedications(@PathVariable String serial) {
        log.debug("finding medications for drone {}", serial);
        return service.findMedications(serial)
                .stream()
                .map(medicationConvertor::toDTO)
                .toList();
    }

    @GetMapping("/findAvailableDrones")
    public List<DroneDTO> findAvailableDrones() {
        log.debug("finding available drones");
        return service.findAvailableDrones()
                .stream()
                .map(convertor::toDTO)
                .toList();
    }

    @GetMapping("/checkBatteryLevel/{serial}")
    public ResponseEntity<Double> checkBatteryLevel(@PathVariable String serial) {
        log.debug("checking battery level for drone {}", serial);
        double batteryLevel = service.checkBatteryLevel(serial);
        return new ResponseEntity<>(batteryLevel, HttpStatus.OK);
    }
}
