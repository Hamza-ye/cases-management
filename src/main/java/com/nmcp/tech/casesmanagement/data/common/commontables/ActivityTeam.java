package com.nmcp.tech.casesmanagement.data.common.commontables;

import com.nmcp.tech.casesmanagement.data.activity.Activity;
import com.nmcp.tech.casesmanagement.data.teams.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ActivityTeam {
    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private ActivityTeamId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("activityId")
    private Activity activity;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ActivityTeam)) return false;
        ActivityTeam that = (ActivityTeam) other;
        return getTeam().equals(that.getTeam()) &&
                getActivity().equals(that.getActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam(), getActivity());
    }
}

@Embeddable
@Getter
@Setter
class ActivityTeamId implements Serializable {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "activity_id")
    private Long activityId;

    private ActivityTeamId() {
    }

    public ActivityTeamId(Long activityId, Long teamId) {
        this.teamId = teamId;
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ActivityTeamId)) return false;
        ActivityTeamId that = (ActivityTeamId) other;
        return teamId.equals(that.teamId) && activityId.equals(that.activityId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, activityId);
    }
}