package com.musala.droneapp.drone;

import com.musala.droneapp.models.entity.Drone;
import com.musala.droneapp.models.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepo extends JpaRepository<Drone, Long> {
    Optional<Drone> findBySerial(String serial);
    List<Drone> findByStateIn(List<State> states);
}
