package jiwjus.AllHospitalSchedules.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class HospitalDepartment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_department_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "hospital_department_name")
    private String name;

    @OneToMany(mappedBy = "hospitalDepartment")
    private List<Doctor> doctors = new ArrayList<>();


    public void changeHospital(Hospital hospital){
        this.hospital = hospital;
        hospital.getHospitalDepartments().add(this);
    }

    public void changeDepartment(Department department){
        this.department = department;
        department.getHospitalDepartments().add(this);
    }

    public HospitalDepartment(Hospital hospital, Department department, String name){
        this.name = name;
        if(hospital != null){
            changeHospital(hospital);
        }
        if(department != null){
            changeDepartment(department);
        }
    }
}
