package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.entity.Doctor;
import jiwjus.AllHospitalSchedules.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByHospitalDepartmentAndName(HospitalDepartment hospitalDepartment, String name);
}
