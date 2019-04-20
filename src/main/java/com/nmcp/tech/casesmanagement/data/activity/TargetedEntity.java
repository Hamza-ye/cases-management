package com.nmcp.tech.casesmanagement.data.activity;


//import javax.persistence.EmbeddedId;

public class TargetedEntity {

    //    @EmbeddedId
    private TargetedEntityId targetedEntityId;

    public TargetedEntity(TargetedEntityId targetedEntityId) {
        this.targetedEntityId = targetedEntityId;
    }

    //    DataElement targeted
}
