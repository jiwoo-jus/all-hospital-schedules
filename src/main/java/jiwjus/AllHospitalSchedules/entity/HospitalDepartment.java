package jiwjus.AllHospitalSchedules.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class HospitalDepartment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_department_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public void changeHospital(Hospital hospital){
        this.hospital = hospital;
        hospital.getHospitalDepartments().add(this);
    }

    public void changeDepartment(Department department){
        this.department = department;
        department.getHospitalDepartments().add(this);
    }

    public HospitalDepartment(String name, Hospital hospital, Department department){
        this.name = name;
        this.hospital = hospital;

        if(hospital != null){
            changeHospital(hospital);
        }

        if(department != null){
            changeDepartment(department);
        }
    }

}
