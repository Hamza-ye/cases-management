package com.nmcp.tech.casesmanagement.data.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
public class Person extends AbstractEntity {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;
    //    String code;
    String name;
    String address;
    String phone;

    @Override
    public Serializable getId() {
        return this.id;
    }
}
