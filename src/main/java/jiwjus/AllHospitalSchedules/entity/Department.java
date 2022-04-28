package jiwjus.AllHospitalSchedules.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<HospitalDepartment> hospitalDepartments = new ArrayList<>();

    public Department(String name){
        this.name = name;
    }
}
