package com.musala.droneapp.medication;

import com.musala.droneapp.models.dto.MedicationDTO;
import com.musala.droneapp.models.entity.Medication;
import org.springframework.stereotype.Component;

/**
 * @Author Tarbaby Elvis Banda on 30/11/2022
 **/

@Component
public class MedicationConvertor {
    public Medication toEntity(MedicationDTO dto) {
        Medication medication = new Medication();
        medication.setId(dto.id());
        medication.setCode(dto.code());
        medication.setImage(dto.image());
        medication.setName(dto.name());
        medication.setWeight(dto.weight());
        return medication;
    }

    public MedicationDTO toDTO(Medication medication) {
        return new MedicationDTO(
                medication.getId(),
                medication.getName(),
                medication.getWeight(),
                medication.getCode(),
                medication.getImage()
        );
    }
}
