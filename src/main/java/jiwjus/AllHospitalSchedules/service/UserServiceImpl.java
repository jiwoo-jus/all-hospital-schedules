package jiwjus.AllHospitalSchedules.service;

import jiwjus.AllHospitalSchedules.dto.*;
import jiwjus.AllHospitalSchedules.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final Region1Repository region1Repository;
    private final Region2Repository region2Repository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final HospitalDepartmentRepository hospitalDepartmentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Region1Dto> findRegion1s() {
        return region1Repository.findDtos();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Region2Dto> findRegion2sByRegion1Id(Long region1Id) {
        return region2Repository.findDtosByRegion1Id(region1Id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DepartmentDto> findDepartments(){
        return departmentRepository.findDtos();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HospitalDepartmentDto> findHospitalDepartmentsByDepartmentIdAndRegion2Ids(HospitalDepartmentRequestDto req) {
        List<Long> region2Ids = Arrays.asList(req.getRegion2Id1(), req.getRegion2Id2(), req.getRegion2Id3());
        List<Long> hospitalIds = hospitalRepository.findIdsByRegion2Ids(region2Ids);
        return hospitalDepartmentRepository.findDtosByDepartmentIdAndHospitalIds(req.getDepartmentId(), hospitalIds);
    }
}
