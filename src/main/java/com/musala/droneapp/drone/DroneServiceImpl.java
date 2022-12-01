package com.musala.droneapp.drone;

import com.musala.droneapp.exceptions.BatteryCriticallyLowException;
import com.musala.droneapp.exceptions.CarryingCapacityExceededException;
import com.musala.droneapp.exceptions.RecordExistException;
import com.musala.droneapp.exceptions.RecordNotFoundException;
import com.musala.droneapp.medication.MedicationConvertor;
import com.musala.droneapp.models.dto.DroneDTO;
import com.musala.droneapp.models.dto.MedicationDTO;
import com.musala.droneapp.models.entity.Drone;
import com.musala.droneapp.models.entity.Medication;
import com.musala.droneapp.models.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepo repo;
    private final DroneConvertor convertor;
    private final MedicationConvertor medicationConvertor;

    @Override
    public Optional<Drone> register(DroneDTO dto) {
        findBySerial(dto.serial()).ifPresent(drone -> {
            throw new RecordExistException(String.format("Drone with serial number %s already registered", dto.serial()));
        });
        Drone drone = convertor.toEntity(dto);
        drone.setState(State.IDLE);
        return Optional.of(repo.save(drone));
    }

    @Override
    public Optional<Drone> update(DroneDTO dto) {
        findBySerial(dto.serial()).orElseThrow(() -> new RecordNotFoundException("Drone not found"));
        Drone drone = convertor.toEntity(dto);
        return Optional.of(repo.save(drone));
    }

    @Override
    public Optional<Drone> loadDrone(String serial, List<MedicationDTO> medicationItems) {
        Drone drone = findBySerial(serial)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Drone with serial number %s not found", serial)));
        if (!isDroneAvailableForLoading(drone.getState()))
            throw new RuntimeException("Drone is not available for loading");
        if (isBatteryCriticallyLow(drone.getBatteryCapacity()))
            throw new BatteryCriticallyLowException("Drone battery is critically low");
        double totalMedicationsWeight = medicationItems
                .stream()
                .mapToDouble(MedicationDTO::weight)
                .sum();
        if (isWeightTooHeavy(drone.getWeightLimit(), totalMedicationsWeight))
            throw new CarryingCapacityExceededException(String.format("The medications are beyond the carrying capacity of %.2f grams", drone.getWeightLimit()));
        updateDroneState(drone, State.LOADING);
        List<Medication> medications = medicationItems
                .stream()
                .map(medicationConvertor::toEntity)
                .toList();
        drone.getMedications().addAll(medications);
        updateDroneState(drone, State.LOADED);
        return Optional.of(repo.save(drone));
    }

    private boolean isBatteryCriticallyLow(double batteryLevel) {
        final double CRITICAL_BATTERY_LEVEL = 25.0;
        return batteryLevel < CRITICAL_BATTERY_LEVEL;
    }

    private void updateDroneState(Drone drone, State state) {
        drone.setState(state);
    }

    private boolean isWeightTooHeavy(double carryingCapacity, double weightToLoad) {
        return weightToLoad > carryingCapacity;
    }

    private boolean isDroneAvailableForLoading(State droneState) {
        List<State> availableStates = List.of(State.IDLE);  //consider if RETURNING state can be considered here
        return availableStates.contains(droneState);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medication> findMedications(String serial) {
        Drone drone = findBySerial(serial)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Drone with serial number %s not found", serial)));
        return drone.getMedications().stream().toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Drone> findAvailableDrones() {
        return repo.findByStateIn(List.of(State.IDLE));
    }

    @Override
    @Transactional(readOnly = true)
    public double checkBatteryLevel(String serial) {
        Drone drone = findBySerial(serial)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Drone with serial number %s not found", serial)));
        return drone.getBatteryCapacity();
    }

    /*
    Return a random level since we cant get the level from the hardware now
     */
    private double getBatteryLevel() {
        return Math.random() * 100.0;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Drone> findBySerial(String serial) {
        return repo.findBySerial(serial);
    }
}
