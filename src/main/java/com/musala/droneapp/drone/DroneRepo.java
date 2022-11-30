package com.musala.droneapp.drone;

import com.musala.droneapp.models.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepo extends JpaRepository<Drone, Long> {
}
