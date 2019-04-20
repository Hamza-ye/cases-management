package com.nmcp.tech.casesmanagement.data.teams;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import com.nmcp.tech.casesmanagement.data.common.Person;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Entity
//@Getter
//@Setter
public class TeamMember extends AbstractEntity {

    @Column(nullable = false, name = "code", length = 10)
    private Long memberNo;

    @OneToOne
    private Person basicInfo;

    private String description;
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter(AccessLevel.NONE)
//    private Long teamId;
    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private TeamMemberId id;

    public TeamMember(TeamMemberId id, Person basicInfo) {
        this.basicInfo = basicInfo;
        this.id = id;
    }

    @Override
    public Serializable getId() {
        return this.id;
    }

}

//@Embeddable
//@Getter
//@Setter
class TeamMemberId implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;

    public TeamMemberId(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeamMemberId)) return false;
        TeamMemberId that = (TeamMemberId) other;
        return team.getId().equals(that.team.getId()) &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team.getId(), id);
    }
}