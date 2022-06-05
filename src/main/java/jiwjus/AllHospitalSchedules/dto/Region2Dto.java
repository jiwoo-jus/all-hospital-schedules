package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.Region2;
import jiwjus.AllHospitalSchedules.enumtype.Region2Enum;
import lombok.Data;

@Data
public class Region2Dto {
    private Long id;
    private Region2Enum name;

    public Region2Dto(Region2 region2){
        this.id = region2.getId();
        this.name = region2.getName();
    }
}
