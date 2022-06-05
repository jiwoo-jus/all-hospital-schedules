package jiwjus.AllHospitalSchedules.service;

import jiwjus.AllHospitalSchedules.dto.*;

import java.util.List;

public interface UserService {

    List<Region1Dto> findAllRegion1s();
    List<Region2Dto> findRegion2sByRegion1(Long region1Id);
    List<DepartmentDto> findAllDepartments();
}
