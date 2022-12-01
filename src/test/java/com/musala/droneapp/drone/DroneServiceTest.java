package com.musala.droneapp.drone;

import com.musala.droneapp.medication.MedicationConvertor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DroneServiceTest {
    private final DroneRepo repo = Mockito.mock(DroneRepo.class);
    private DroneService service;
    @Autowired
    private DroneConvertor droneConvertor;
    @Autowired
    private MedicationConvertor medicationConvertor;

    @BeforeEach
    void setUp() {
        service = new DroneServiceImpl(repo, droneConvertor, medicationConvertor);
    }

    @Test
    void register() {
    }

    @Test
    void loadDrone() {
    }

    @Test
    void findMedications() {
    }

    @Test
    void findAvailableDrones() {
    }

    @Test
    void checkBatteryLevel() {
    }

    @Test
    void findBySerial() {
    }
}