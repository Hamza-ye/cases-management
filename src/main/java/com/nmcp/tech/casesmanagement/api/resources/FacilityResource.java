package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.data.Facility;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * Created by Hamza on 2019-02-21.
 */
@Data
public class FacilityResource extends ResourceSupport {

    static final DistrictResourceAssembler districtAssembler = new DistrictResourceAssembler();
    private String code;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private DistrictResource district;

    public FacilityResource(Facility facility) {
        this.code = facility.getCode();
        this.name = facility.getName();
        this.description = facility.getDescription();
        this.createdAt = facility.getCreatedAt();
        this.district = districtAssembler.toResource(facility.getDistrict());
//        this.district = facility.getDistrict();
    }
}
