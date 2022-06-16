package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.Hospital;
import jiwjus.AllHospitalSchedules.enumtype.DayOfWeek;
import lombok.Data;

@Data
public class HospitalDto {

    private Long id;
    private String name;
    private DayOfWeek dayOfWeek1;
    private DayOfWeek dayOfWeek2;

    public HospitalDto(Hospital hospital){
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.dayOfWeek1 = hospital.getDayOff1();
        this.dayOfWeek2 = hospital.getDayOff2();
    }
}
