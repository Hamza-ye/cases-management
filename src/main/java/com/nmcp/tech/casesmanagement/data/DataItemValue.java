package com.nmcp.tech.casesmanagement.data;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
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
        @UniqueConstraint(columnNames = {"year", "week", "orgunit_id", "data_element_id"})
})
@Data
@NoArgsConstructor
public class DataItemValue extends AbstractEntity {

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
    @JoinColumn(name = "orgunit_id")
    private Orgunit orgunit;

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
