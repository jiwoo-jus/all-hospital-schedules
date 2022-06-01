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
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_department_id")
    private HospitalDepartment hospitalDepartment;

    @Column(name = "doctor_name")
    private String name;

    @OneToMany(mappedBy = "doctor")
    private List<Schedule> schedules = new ArrayList<>();


    public void changeHospitalDepartment(HospitalDepartment hospitalDepartment){
        this.hospitalDepartment = hospitalDepartment;
        hospitalDepartment.getDoctors().add(this);
    }

    public Doctor(HospitalDepartment hospitalDepartment, String name){
        this.name = name;
        if(hospitalDepartment != null){
            changeHospitalDepartment(hospitalDepartment);
        }
    }
}
