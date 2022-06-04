package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Hospital findByName(String name);
}
