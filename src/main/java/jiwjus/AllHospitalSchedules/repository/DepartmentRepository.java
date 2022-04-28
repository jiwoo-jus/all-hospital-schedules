package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
