package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Created by Hamza on 2019-03-30.
 */
@Data
public class OrgunitResource extends ResourceSupport {
    private String name;
    private String idString;
    private String code;
    private String parent;
    private Date createdAt;

    public OrgunitResource(Orgunit orgunit) {
        this.name = orgunit.getName();
        this.idString = orgunit.getIdString();
        this.code = orgunit.getCode();
        this.parent = orgunit.getParent();
        this.createdAt = orgunit.getCreatedAt();
    }
}
