package com.nmcp.tech.casesmanagement.data.common.commontables;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ProgramProject {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long id;
    private String code;
}

@Embeddable
@Getter
@Setter
class ProgramProjectId implements Serializable {

    @Column(name = "team_id")
    private Long projectId;
    @Column(name = "activity_id")
    private Long programId;

    private ProgramProjectId() {
    }

    public ProgramProjectId(Long programId, Long projectId) {
        this.projectId = projectId;
        this.programId = programId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ProgramProjectId)) return false;
        ProgramProjectId that = (ProgramProjectId) other;
        return projectId.equals(that.projectId) && programId.equals(that.programId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, programId);
    }
}