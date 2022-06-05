package jiwjus.AllHospitalSchedules.entity;

import jiwjus.AllHospitalSchedules.enumtype.Region1Enum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region1")
public class Region1 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region1_id")
    private Long id;

    @Column(name = "region1_name")
    @Enumerated(EnumType.STRING)
    private Region1Enum name;

    @OneToMany(mappedBy = "region1")
    List<Region2> region2s = new ArrayList<>();

    public Region1(Region1Enum region1Enum) {
        this.name = region1Enum;
    }
}
