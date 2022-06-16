package jiwjus.AllHospitalSchedules.dto;

import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class HospitalDepartmentRequestDto {

    private Long region1Id;
    private Long region2Id1;
    private Long region2Id2;
    private Long region2Id3;
    private Long region2Id4;
    private Long region2Id5;
    private Long departmentId;
}
