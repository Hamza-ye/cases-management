package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.api.FacilityRestController;
import com.nmcp.tech.casesmanagement.data.Facility;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Hamza on 2019-02-21.
 */
@Component
public class FacilityResourceAssembler extends ResourceAssemblerSupport<Facility, FacilityResource> {

    //    public FacilityResourceAssembler(Class<?> controllerClass, Class<FacilityResource> resourceType) {
//        super(controllerClass, resourceType); //FacilityRestController.class
//
//    }
    public FacilityResourceAssembler() {
        super(FacilityRestController.class, FacilityResource.class); //

    }

    @Override
    public FacilityResource toResource(Facility facility) {

        return createResourceWithId(facility.getId(), facility);
    }

    @Override
    protected FacilityResource instantiateResource(Facility facility) {
        return new FacilityResource(facility);
    }
}
