package com.musala.droneapp.scheduledTask;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Author Tarbaby Elvis Banda on 1/12/2022
 **/
@Component
@Profile("scheduled-task-runner")
public class CheckBatteryLevelTask {
}
