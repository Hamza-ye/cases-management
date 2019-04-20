package com.nmcp.tech.casesmanagement.data.teams;

//import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;

import com.nmcp.tech.casesmanagement.data.common.commontables.ActivityTeam;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team implements NestedSetsTreeNode {


    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false, length = 11)
    private String code;

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ActivityTeam> activities = new ArrayList<>();

    private String parent;
    @ManyToOne(targetEntity = Team.class)
    private NestedSetsTreeNode topLevel; // root reference

    private int lft; // "left" would be a SQL keyword!
    private int rgt; // "right" would be a SQL keyword!

    public Team() {
    }

    public Team(String code, String description, List<ActivityTeam> activities, String parent) {
        this.code = code;
        this.description = description;
        this.activities = activities;
        this.parent = parent;

    }

    public Team(String code) {
        this.code = code;
    }

    @Override
    public Serializable getId() {
        return this.id;
    }

    @Override
    public int getLeft() {
        return lft;
    }

    @Override
    public void setLeft(int i) {
        this.lft = i;
    }

    @Override
    public int getRight() {
        return rgt;
    }

    @Override
    public void setRight(int i) {
        this.rgt = i;

    }

    @Override
    public NestedSetsTreeNode clone() {
        return new Team(getCode(), getDescription(), getActivities(), getParent());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team))
            return false;

        if (id != null)
            return id.equals(((Team) obj).getId());

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (id != null)
            return id.hashCode();

        throw new IllegalStateException("Do not use hashCode() with tranisent POJOs, they could get lost in Maps!");
    }

    /**
     * Overridden to avoid super.toString() calling hashCode(), as this might throw exception when no teamId exists yet.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + ": TeamId=" + getId() + ", code=" + getCode();
    }
}


