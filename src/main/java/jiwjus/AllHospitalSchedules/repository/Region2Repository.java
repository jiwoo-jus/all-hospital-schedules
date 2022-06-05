package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.dto.Region2Dto;
import jiwjus.AllHospitalSchedules.entity.Region2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Region2Repository extends JpaRepository<Region2, Long> {

    @Query("select new jiwjus.AllHospitalSchedules.dto.Region2Dto(r2) from Region2 r2 join r2.region1 r1 where r1.id = :region1Id ")
    List<Region2Dto> findDtosByRegion1Id(Long region1Id);
}
