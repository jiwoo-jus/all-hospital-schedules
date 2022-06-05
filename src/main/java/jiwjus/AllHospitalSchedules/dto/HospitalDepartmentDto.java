package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.HospitalDepartment;
import lombok.Data;

@Data
public class HospitalDepartmentDto {

    Long id;
    String name;

    public HospitalDepartmentDto(HospitalDepartment hospitalDepartment){
        this.id = hospitalDepartment.getId();
        this.name = hospitalDepartment.getName();
    }
}
