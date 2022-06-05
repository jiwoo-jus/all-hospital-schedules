package jiwjus.AllHospitalSchedules.entity;

import jiwjus.AllHospitalSchedules.enumtype.DayOfWeek;
import jiwjus.AllHospitalSchedules.enumtype.Region1Enum;
import jiwjus.AllHospitalSchedules.enumtype.Region2Enum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(value = false)
class DoctorTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity(){
        Region1 region1_1 = new Region1(Region1Enum.서울);
        Region1 region1_2 = new Region1(Region1Enum.경기);
        Region1 region1_3 = new Region1(Region1Enum.인천);
        em.persist(region1_1);
        em.persist(region1_2);
        em.persist(region1_3);
        Region2 region2_1 = new Region2(region1_1, Region2Enum.강북구);
        Region2 region2_2 = new Region2(region1_1, Region2Enum.강남구);
        Region2 region2_3 = new Region2(region1_1, Region2Enum.서초구);
        Region2 region2_4 = new Region2(region1_2, Region2Enum.성남시);
        Region2 region2_5 = new Region2(region1_3, Region2Enum.남동구);
        Region2 region2_6 = new Region2(region1_3, Region2Enum.부평구);
        em.persist(region2_1);
        em.persist(region2_2);
        em.persist(region2_3);
        em.persist(region2_4);
        em.persist(region2_5);
        em.persist(region2_6);
        Hospital hospital1 = new Hospital("혜화서울대병원", region2_1, DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital2 = new Hospital("삼성서울병원", region2_2, DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital3 = new Hospital("서울성모병원", region2_2, DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital4 = new Hospital("분당서울대병원", region2_4, DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital5 = new Hospital("인천길병원", region2_5, DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital6 = new Hospital("인천성모병원", region2_6, DayOfWeek.SAT, DayOfWeek.SUN);
        em.persist(hospital1);
        em.persist(hospital2);
        em.persist(hospital3);
        em.persist(hospital4);
        em.persist(hospital5);
        em.persist(hospital6);

        Department department1 = new Department("이비인후과");
        Department department2 = new Department("호흡기내과");
        em.persist(department1);
        em.persist(department2);

        HospitalDepartment hospitalDepartment1 = new HospitalDepartment(hospital1, department1, "이비인후과");
        HospitalDepartment hospitalDepartment2 = new HospitalDepartment(hospital2, department1, "이비인후과");
        HospitalDepartment hospitalDepartment3 = new HospitalDepartment(hospital3, department1, "이비인후과");
        HospitalDepartment hospitalDepartment4 = new HospitalDepartment(hospital4, department1, "이비인후과");
        HospitalDepartment hospitalDepartment5 = new HospitalDepartment(hospital5, department1, "이비인후과");
        HospitalDepartment hospitalDepartment6 = new HospitalDepartment(hospital6, department1, "이비인후과");
        HospitalDepartment hospitalDepartment7 = new HospitalDepartment(hospital1, department2, "호흡기내과");
        HospitalDepartment hospitalDepartment8 = new HospitalDepartment(hospital2, department2, "호흡기내과");
        HospitalDepartment hospitalDepartment9 = new HospitalDepartment(hospital3, department2, "호흡기내과");
        HospitalDepartment hospitalDepartment10 = new HospitalDepartment(hospital4, department2, "호흡기내과");
        HospitalDepartment hospitalDepartment11 = new HospitalDepartment(hospital5, department2, "호흡기내과");
        HospitalDepartment hospitalDepartment12 = new HospitalDepartment(hospital6, department2, "호흡기내과");

        em.persist(hospitalDepartment1);
        em.persist(hospitalDepartment2);
        em.persist(hospitalDepartment3);
        em.persist(hospitalDepartment4);
        em.persist(hospitalDepartment5);
        em.persist(hospitalDepartment6);
        em.persist(hospitalDepartment7);
        em.persist(hospitalDepartment8);
        em.persist(hospitalDepartment9);
        em.persist(hospitalDepartment10);
        em.persist(hospitalDepartment11);
        em.persist(hospitalDepartment12);

        Doctor doctor1 = new Doctor(hospitalDepartment1, "성명훈");
        Doctor doctor2 = new Doctor(hospitalDepartment1, "오승하");
        Doctor doctor3 = new Doctor(hospitalDepartment1, "이재서");
        Doctor doctor4 = new Doctor(hospitalDepartment1, "이준호");
        Doctor doctor5 = new Doctor(hospitalDepartment1, "김동영");
        Doctor doctor6 = new Doctor(hospitalDepartment1, "박무균");
        Doctor doctor7 = new Doctor(hospitalDepartment7, "유철규");
        Doctor doctor8 = new Doctor(hospitalDepartment7, "임재준");
        Doctor doctor9 = new Doctor(hospitalDepartment7, "이창훈");
        Doctor doctor10 = new Doctor(hospitalDepartment7, "최선미");
        Doctor doctor11 = new Doctor(hospitalDepartment7, "조재영");
        Doctor doctor12 = new Doctor(hospitalDepartment7, "곽낙원");

        em.persist(doctor1);
        em.persist(doctor2);
        em.persist(doctor3);
        em.persist(doctor4);
        em.persist(doctor5);
        em.persist(doctor6);
        em.persist(doctor7);
        em.persist(doctor8);
        em.persist(doctor9);
        em.persist(doctor10);
        em.persist(doctor11);
        em.persist(doctor12);


        //초기화
        em.flush(); //강제로 현재까지의 인서트 쿼리 날림
        em.clear(); //강제로 영속성컨텍스트에 있는 캐시 지움

        //확인
        List<HospitalDepartment> hospitalDepartments = em
                .createQuery("select m from HospitalDepartment m", HospitalDepartment.class)
                .getResultList();

        for(HospitalDepartment hospitalDepartment : hospitalDepartments){
            System.out.println("hospitalDepartment = " + hospitalDepartment);
            System.out.println("-> hospitalDepartment.hospital = " + hospitalDepartment.getHospital());
            System.out.println("-> hospitalDepartment.department = " + hospitalDepartment.getDepartment());
        }

        List<Doctor> doctors = em
                .createQuery("select m from Doctor m", Doctor.class)
                .getResultList();

        for(Doctor doctor : doctors){
            System.out.println("doctor = " + doctor);
            System.out.println("-> doctor.hospitalDepartment = " + doctor.getHospitalDepartment());
        }

    }
}