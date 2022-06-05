package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.Region1;
import jiwjus.AllHospitalSchedules.enumtype.Region1Enum;
import lombok.Data;

@Data
public class Region1Dto {
    private Long id;
    private Region1Enum name;

    public Region1Dto(Region1 region1){
        this.id = region1.getId();
        this.name = region1.getName();
    }
}
