package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.Doctor;
import lombok.Data;

import java.util.List;

@Data
public class DoctorScheduleDto {
    private Long doctorId;
    private String doctorName;
    private List<String> scheduleList;

    public DoctorScheduleDto(Doctor doctor, List<String> scheduleDtos) {
        this.doctorId = doctor.getId();
        this.doctorName = doctor.getName();
        this.scheduleList = scheduleDtos;
    }
}