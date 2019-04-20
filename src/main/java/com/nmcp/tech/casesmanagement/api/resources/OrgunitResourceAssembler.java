package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.api.OrgunitRestController;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Hamza on 2019-03-30.
 */
@Component
public class OrgunitResourceAssembler extends ResourceAssemblerSupport<Orgunit, OrgunitResource> {

    public OrgunitResourceAssembler() {
        super(OrgunitRestController.class, OrgunitResource.class);
    }

    @Override
    public OrgunitResource toResource(Orgunit orgunit) {
        return createResourceWithId(orgunit.getIdString(), orgunit);
    }

    @Override
    protected OrgunitResource instantiateResource(Orgunit orgunit) {
        return new OrgunitResource(orgunit);
    }
}
