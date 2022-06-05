package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Hospital findByName(String name);

    @Query( "select h.id " +
            "from   Hospital h " +
            "join   h.region2 r2 " +
            "where  r2.id in :region2Ids ")
    List<Long> findIdsByRegion2Ids(List<Long> region2Ids);
}
