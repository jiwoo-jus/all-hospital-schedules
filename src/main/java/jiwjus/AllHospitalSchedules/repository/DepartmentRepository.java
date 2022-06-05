package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.dto.DepartmentDto;
import jiwjus.AllHospitalSchedules.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select new jiwjus.AllHospitalSchedules.dto.DepartmentDto(d) from Department d")
    List<DepartmentDto> findAllDepartmentDtos();
}
