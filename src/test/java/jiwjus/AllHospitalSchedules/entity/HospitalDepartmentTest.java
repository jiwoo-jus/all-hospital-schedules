package jiwjus.AllHospitalSchedules.entity;

import jiwjus.AllHospitalSchedules.enumtype.DayOfWeek;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class HospitalDepartmentTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity(){
        Hospital hospital1 = new Hospital("서울대학교병원", DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital2 = new Hospital("삼성서울병원", DayOfWeek.SAT, DayOfWeek.SUN);
        Hospital hospital3 = new Hospital("서울성모병원", DayOfWeek.SAT, DayOfWeek.SUN);
        em.persist(hospital1);
        em.persist(hospital2);
        em.persist(hospital3);

        Department department1 = new Department("이비인후과");
        Department department2 = new Department("안과");
        em.persist(department1);
        em.persist(department2);

        HospitalDepartment hospitalDepartment1 = new HospitalDepartment(hospital1, department1, "서울대학교병원이비인후과");
        HospitalDepartment hospitalDepartment2 = new HospitalDepartment(hospital2, department1, "삼성서울병원이비인후과");
        HospitalDepartment hospitalDepartment3 = new HospitalDepartment(hospital3, department1, "서울성모병원이비인후과");
        HospitalDepartment hospitalDepartment4 = new HospitalDepartment(hospital1, department2, "서울대학교병원안과");
        HospitalDepartment hospitalDepartment5 = new HospitalDepartment(hospital2, department2, "삼성서울병원병원안과");
        HospitalDepartment hospitalDepartment6 = new HospitalDepartment(hospital3, department2, "서울성모병원안과");
        em.persist(hospitalDepartment1);
        em.persist(hospitalDepartment2);
        em.persist(hospitalDepartment3);
        em.persist(hospitalDepartment4);
        em.persist(hospitalDepartment5);
        em.persist(hospitalDepartment6);

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
    }

}