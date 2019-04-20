package com.nmcp.tech.casesmanagement.data.activity;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import com.nmcp.tech.casesmanagement.data.common.commontables.ActivityTeam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Activity extends AbstractEntity {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;

    String name;
    String code;
    String description;
    LocalDate startDate;
    LocalDate endDate;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ActivityTeam> teams = new ArrayList<>();

    public Activity() {
    }

    public Activity(String name, String code, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Serializable getId() {
        return this.id;
    }
}
