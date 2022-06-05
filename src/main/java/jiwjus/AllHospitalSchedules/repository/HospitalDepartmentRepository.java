package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.dto.HospitalDepartmentDto;
import jiwjus.AllHospitalSchedules.entity.Hospital;
import jiwjus.AllHospitalSchedules.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Long> {

    HospitalDepartment findByHospitalAndName(Hospital hospital, String name);

    @Query( "select new jiwjus.AllHospitalSchedules.dto.HospitalDepartmentDto(hd) " +
            "from   HospitalDepartment hd " +
            "join   hd.department d " +
            "join   hd.hospital h " +
            "where  d.id = :departmentId " +
            "and    h.id in :region2Ids ")
    List<HospitalDepartmentDto> findDtosByDepartmentIdAndHospitalIds(Long departmentId, List<Long> region2Ids);
}
