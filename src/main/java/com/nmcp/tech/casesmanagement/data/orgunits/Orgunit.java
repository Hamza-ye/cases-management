package com.nmcp.tech.casesmanagement.data.orgunits;

import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hamza on 2019-02-13.
 */
@Entity
public class Orgunit implements NestedSetsTreeNode {

    @Id
    @GeneratedValue
    private Long id; // primary key

    private String name;

    @Column(unique = true)
    private String idString;
    //    @Column(unique = true)
    private String code;
    private String parent;
    @ManyToOne(targetEntity = Orgunit.class)
    private NestedSetsTreeNode topLevel; // root reference

    private int lft; // "left" would be a SQL keyword!
    private int rgt; // "right" would be a SQL keyword!

    private Date createdAt;

    public Orgunit() {
    }

    public Orgunit(String name, String code) {
        this.name = name;
        this.code = code;
    }


    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public NestedSetsTreeNode getTopLevel() {
        return topLevel;
    }

    @Override
    public void setTopLevel(NestedSetsTreeNode topLevel) {
        this.topLevel = topLevel;
    }

    @Override
    public int getLeft() {
        return lft;
    }

    @Override
    public void setLeft(int left) {
        this.lft = left;
    }

    @Override
    public int getRight() {
        return rgt;
    }

    @Override
    public void setRight(int right) {
        this.rgt = right;
    }

    @Override
    public Orgunit clone() {
        return new Orgunit(getName(), getCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Orgunit == false)
            return false;

        if (id != null)
            return id.equals(((Orgunit) obj).getId());

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
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + ": OrgunitId=" + getId() + ", name=" + getName();
    }

}
