package com.nmcp.tech.casesmanagement.data.activity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

//@Embeddable
//@Getter
//@Setter
public class TargetedEntityId implements Serializable {
    @NotNull
    private Long activityId;

    @NotNull
    private String orgunitCode;

    public TargetedEntityId() {
    }

    public TargetedEntityId(@NotNull Long activityId, @NotNull String orgunitCode) {
        this.activityId = activityId;
        this.orgunitCode = orgunitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetedEntityId)) return false;
        TargetedEntityId that = (TargetedEntityId) o;
        return activityId.equals(that.activityId) &&
                orgunitCode.equals(that.orgunitCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, orgunitCode);
    }
}
