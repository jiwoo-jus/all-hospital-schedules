package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.entity.Hospital;
import jiwjus.AllHospitalSchedules.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Long> {
    HospitalDepartment findByHospitalAndName(Hospital hospital, String name);
}
