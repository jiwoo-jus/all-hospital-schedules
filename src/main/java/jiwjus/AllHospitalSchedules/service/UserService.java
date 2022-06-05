package jiwjus.AllHospitalSchedules.service;

import jiwjus.AllHospitalSchedules.dto.*;

import java.util.List;

public interface UserService {

    List<Region1Dto> findRegion1s();
    List<Region2Dto> findRegion2sByRegion1Id(Long region1Id);
    List<DepartmentDto> findDepartments();
    List<HospitalDepartmentDto> findHospitalDepartmentsByDepartmentIdAndRegion2Ids(HospitalDepartmentRequestDto hospitalDepartmentRequestDto);
}
