package jiwjus.AllHospitalSchedules.entity;

import jiwjus.AllHospitalSchedules.enumtype.DayOfWeek;
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
@ToString(of = {"id", "name", "dayOff1", "dayOff2"})
public class Hospital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    @Column(name = "hospital_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressLevel2_id")
    private Region2 region2;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOff1;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOff2;

    @OneToMany(mappedBy = "hospital")
    private List<HospitalDepartment> hospitalDepartments = new ArrayList<>();


    public Hospital(String name, Region2 region2, DayOfWeek dayOff1, DayOfWeek dayOff2){
        this.name = name;
        this.region2 = region2;
        this.dayOff1 = dayOff1;
        this.dayOff2 = dayOff2;
    }
}
