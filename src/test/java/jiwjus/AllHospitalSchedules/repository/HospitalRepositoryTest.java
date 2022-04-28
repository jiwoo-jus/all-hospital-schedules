package jiwjus.AllHospitalSchedules.repository;

import jiwjus.AllHospitalSchedules.enumtype.DayOfWeek;
import jiwjus.AllHospitalSchedules.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class HospitalRepositoryTest {

    @Autowired HospitalRepository hospitalRepository;

    @Test
    public void testHospital(){
        Hospital hospital1 = new Hospital("서울대학교병원", DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital saveHospital1 = hospitalRepository.save(hospital1);

        Hospital findHospital1 = hospitalRepository.findById(saveHospital1.getId()).get();

        assertThat(findHospital1.getId()).isEqualTo(hospital1.getId());

    }
}