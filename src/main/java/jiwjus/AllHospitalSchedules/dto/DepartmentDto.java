package jiwjus.AllHospitalSchedules.dto;

import jiwjus.AllHospitalSchedules.entity.Department;
import lombok.Data;

@Data
public class DepartmentDto {

    private Long id;
    private String name;

    public DepartmentDto(Department department){
        this.id = department.getId();
        this.name = department.getName();
    }
}
