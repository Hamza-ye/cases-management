package com.nmcp.tech.casesmanagement.data;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hamza on 2019-02-12.
 */

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"year", "week", "facility_id", "data_element_id"})
})
@Data
@NoArgsConstructor
////@Table(name = "data-value")
//@IdClass(DataValue.class)
@Deprecated
public class DataValue extends AbstractEntity {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "week")
    private Integer week;

    private Integer dataValue;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_element_id")
    private DataElement dataElement;

    private AgeGroup ageGroup;

    @Override
    public Serializable getId() {
        return this.id;
    }


    public enum AgeGroup {LESS_THAN_5, GREATER_THAN_5}

}
