package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.api.DistrictRestController;
import com.nmcp.tech.casesmanagement.data.District;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Hamza on 2019-02-21.
 */
@Component
public class DistrictResourceAssembler extends ResourceAssemblerSupport<District, DistrictResource> {

    public DistrictResourceAssembler() {
        super(DistrictRestController.class, DistrictResource.class); //

    }

    @Override
    public DistrictResource toResource(District district) {

        return createResourceWithId(district.getId(), district);
    }

    @Override
    protected DistrictResource instantiateResource(District district) {
        return new DistrictResource(district);
    }
}
