package jiwjus.AllHospitalSchedules.entity;

import jiwjus.AllHospitalSchedules.enumtype.Region2Enum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region2")
public class Region2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region2_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region1_id")
    private Region1 region1;

    @Enumerated(EnumType.STRING)
    @Column(name = "region2_name")
    private Region2Enum name;


    public void changeRegion1(Region1 region1){
        this.region1 = region1;
        region1.getRegion2s().add(this);
    }

    public Region2(Region1 region1,Region2Enum region2Enum){
        this.name = region2Enum;
        if(region1 != null){
            changeRegion1(region1);
        }
    }

}