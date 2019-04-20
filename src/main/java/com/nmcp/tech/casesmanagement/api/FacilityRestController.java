package com.nmcp.tech.casesmanagement.api;

import com.nmcp.tech.casesmanagement.api.resources.FacilityResource;
import com.nmcp.tech.casesmanagement.api.resources.FacilityResourceAssembler;
import com.nmcp.tech.casesmanagement.data.DistrictRepository;
import com.nmcp.tech.casesmanagement.data.Facility;
import com.nmcp.tech.casesmanagement.data.FacilityRepository;
import com.nmcp.tech.casesmanagement.services.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-13.
 */
@RestController
@RequestMapping(path = "/facilities", produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class FacilityRestController {

    FacilityRepository facilityRepository;
    FacilityService facilityService;
    DistrictRepository districtRepository;
    FacilityResourceAssembler facilityResourceAssembler;
    PagedResourcesAssembler<Facility> pagedResourcesAssembler;
//    Logger log;


    @Autowired
    public FacilityRestController(FacilityRepository facilityRepository,
                                  FacilityService facilityService,
                                  DistrictRepository districtRepository,
                                  FacilityResourceAssembler fSA,
                                  PagedResourcesAssembler<Facility> pagedResourcesAssembler) {
        this.facilityRepository = facilityRepository;
        this.facilityService = facilityService;
        this.districtRepository = districtRepository;
        this.facilityResourceAssembler = fSA;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(produces = "application/json")
    public PagedResources<FacilityResource> getFacilities(
            @RequestParam(name = "governorate", required = false) String governorate,
            @RequestParam(name = "district", required = false) String districtCode,
            @RequestParam(name = "name", required = false) String name,
            @PageableDefault(value = 10, page = 0)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "districtGovernorate", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "districtCode", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)

            }) Pageable page) {

        if (districtCode != null && !districtCode.isEmpty()) {
            if (name != null && !name.isEmpty()) {

                Page<Facility> facilityPage =
//                            facilityRepository.findByDistrictGovernorateLikeIgnoreCaseAndDistrictCodeAndNameLikeIgnoreCase(
//                                    "%" + governorate + "%", districtCode, "%" + name + "%", page);
                        facilityRepository.findByDistrictCodeAndNameLikeIgnoreCase(districtCode, "%" + name + "%", page);

                PagedResources<FacilityResource> pagedFacilityResources =
                        pagedResourcesAssembler.toResource(facilityPage,
                                facilityResourceAssembler,
                                ControllerLinkBuilder.linkTo(
                                        ControllerLinkBuilder.methodOn(FacilityRestController.class)
                                                .getFacilities(governorate, districtCode, name, page)).withSelfRel());

                return pagedFacilityResources; //return facilityRepository.findByDistrictCodeAndNameLikeIgnoreCase(districtCode, "%"+name+"%", page).getContent();
            }
            Page<Facility> facilityPage =
                    facilityRepository.findByDistrictCode(districtCode, page);

            PagedResources<FacilityResource> pagedFacilityResources =
                    pagedResourcesAssembler.toResource(facilityPage,
                            facilityResourceAssembler,
                            ControllerLinkBuilder.linkTo(
                                    ControllerLinkBuilder.methodOn(FacilityRestController.class)
                                            .getFacilities(governorate, districtCode, name, page)).withSelfRel());

            return pagedFacilityResources;

        }

        if (name != null && !name.isEmpty()) {
            Page<Facility> facilityPage =
                    facilityRepository.findByNameLikeIgnoreCase("%" + name + "%", page);

            PagedResources<FacilityResource> pagedFacilityResources =
                    pagedResourcesAssembler.toResource(facilityPage,
                            facilityResourceAssembler,
                            ControllerLinkBuilder.linkTo(
                                    ControllerLinkBuilder.methodOn(FacilityRestController.class)
                                            .getFacilities(governorate, districtCode, name, page)).withSelfRel());

            return pagedFacilityResources;

        }

        Page<Facility> facilityPage =
                facilityRepository.findAll(page);

        PagedResources<FacilityResource> pagedFacilityResources =
                pagedResourcesAssembler.toResource(facilityPage,
                        facilityResourceAssembler,
                        ControllerLinkBuilder.linkTo(
                                ControllerLinkBuilder.methodOn(FacilityRestController.class)
                                        .getFacilities(governorate, districtCode, name, page)).withSelfRel());

        return pagedFacilityResources;

    }

    @GetMapping(path = "/{teamId}", produces = "application/json")
    HttpEntity<FacilityResource> getFacilityById(@PathVariable("id") Long id) {
        Optional<Facility> facilityOptional = facilityRepository.findById(id);
        if (facilityOptional.isPresent()) {
            FacilityResource facilityResource = new FacilityResource(facilityOptional.get());
            facilityResource.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(FacilityRestController.class)
                                    .getFacilityById(id)
                    ).withSelfRel());
            return new ResponseEntity<FacilityResource>(facilityResource, HttpStatus.OK);
        }
        return new ResponseEntity<FacilityResource>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Facility postFacility(@RequestBody Facility facility) {
        return facilityRepository.save(facility);
    }

}
