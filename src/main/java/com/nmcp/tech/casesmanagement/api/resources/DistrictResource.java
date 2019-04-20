package com.nmcp.tech.casesmanagement.api.resources;

import com.nmcp.tech.casesmanagement.data.District;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * Created by Hamza on 2019-02-21.
 */
@Data
public class DistrictResource extends ResourceSupport {

    private String code;
    private String governorate;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public DistrictResource(District district) {
        this.code = district.getCode();
        this.name = district.getName();
        this.governorate = district.getGovernorate();
        this.description = district.getDescription();
        this.createdAt = district.getCreatedAt();
    }
}
