package com.nmcp.tech.casesmanagement.data;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hamza on 2019-02-12.
 */

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Deprecated
public class District extends AbstractEntity {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true)
    private String code;
    private String governorate;
    private String name;
    private String description;

    @Override
    public Serializable getId() {
        return this.id;
    }
}
