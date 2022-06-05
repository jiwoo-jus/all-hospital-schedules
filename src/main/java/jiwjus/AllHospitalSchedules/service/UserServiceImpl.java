package jiwjus.AllHospitalSchedules.service;

import jiwjus.AllHospitalSchedules.dto.*;
import jiwjus.AllHospitalSchedules.repository.DepartmentRepository;
import jiwjus.AllHospitalSchedules.repository.HospitalRepository;
import jiwjus.AllHospitalSchedules.repository.Region1Repository;
import jiwjus.AllHospitalSchedules.repository.Region2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final Region1Repository region1Repository;
    private final Region2Repository region2Repository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Region1Dto> findAllRegion1s() {
        return region1Repository.findAllRegion1Dtos();
    }

    @Override
    public List<Region2Dto> findRegion2sByRegion1(Long region1Id) {
        return region2Repository.findRegion2DtosByRegion1(region1Id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DepartmentDto> findAllDepartments(){
        return departmentRepository.findAllDepartmentDtos();
    }



}
