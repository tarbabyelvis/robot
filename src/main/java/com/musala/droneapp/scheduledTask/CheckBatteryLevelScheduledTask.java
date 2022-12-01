package com.musala.droneapp.scheduledTask;

import com.musala.droneapp.drone.DroneService;
import com.musala.droneapp.models.entity.Drone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Tarbaby Elvis Banda on 1/12/2022
 **/
@Component
@Profile("scheduled-task-runner")
@RequiredArgsConstructor
@Slf4j
public class CheckBatteryLevelScheduledTask {
    private final DroneService service;

    @Scheduled(cron = "${battery.level.check.cron}")
    public void onInvoke() {
        List<Drone> drones = service.findAll();
        drones.stream().forEach(drone -> logLevels(drone));
    }

    private void logLevels(Drone drone) {
        log.info(String.format("Battery level for drone %s at %.2f", drone.getSerial(), drone.getBatteryCapacity()));
    }
}
