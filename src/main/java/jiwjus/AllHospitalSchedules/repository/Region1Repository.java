package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.dto.Region1Dto;
import jiwjus.AllHospitalSchedules.entity.Region1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Region1Repository extends JpaRepository<Region1, Long> {

    @Query("select new jiwjus.AllHospitalSchedules.dto.Region1Dto(r1) from Region1 r1")
    List<Region1Dto> findDtos();
}
