package com.nmcp.tech.casesmanagement.api;

import com.nmcp.tech.casesmanagement.api.resources.DistrictResource;
import com.nmcp.tech.casesmanagement.api.resources.DistrictResourceAssembler;
import com.nmcp.tech.casesmanagement.data.District;
import com.nmcp.tech.casesmanagement.data.DistrictRepository;
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
 * Created by Hamza on 2019-02-14.
 */
@RestController
@RequestMapping(path = "/districts", produces = "application/json")
@CrossOrigin(origins = "*")
public class DistrictRestController {
    DistrictRepository districtRepository;
    DistrictResourceAssembler districtResourceAssembler;
    PagedResourcesAssembler<District> pagedResourcesAssembler;

    @Autowired
    public DistrictRestController(DistrictRepository districtRepository,
                                  DistrictResourceAssembler districtResourceAssembler,
                                  PagedResourcesAssembler<District> pagedResourcesAssembler) {
        this.districtRepository = districtRepository;
        this.districtResourceAssembler = districtResourceAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

//    @GetMapping(produces="application/json")
//    public Iterable<District> getDistricts(){
//        return districtRepository.findAll();
//    }

    @GetMapping(produces = "application/json")
    public PagedResources<DistrictResource> getAllDistricts(
            @RequestParam(name = "governorate", required = false) String governorate,
            @RequestParam(name = "name", required = false) String name,
            @PageableDefault(value = 10, page = 0)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "code", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)

            }) Pageable page) {
        if (governorate != null && !governorate.isEmpty()) {
            if (name != null && !name.isEmpty()) {

                Page<District> districtPage =
//                            facilityRepository.findByDistrictGovernorateLikeIgnoreCaseAndDistrictCodeAndNameLikeIgnoreCase(
//                                    "%" + governorate + "%", districtCode, "%" + name + "%", page);
                        districtRepository.findByGovernorateLikeIgnoreCaseAndNameLikeIgnoreCase(governorate, "%" + name + "%", page);

                PagedResources<DistrictResource> pagedDistrictResources =
                        pagedResourcesAssembler.toResource(districtPage,
                                districtResourceAssembler,
                                ControllerLinkBuilder.linkTo(
                                        ControllerLinkBuilder.methodOn(DistrictRestController.class)
                                                .getAllDistricts(governorate, name, page)).withSelfRel());

                return pagedDistrictResources; //return facilityRepository.findByDistrictCodeAndNameLikeIgnoreCase(districtCode, "%"+name+"%", page).getContent();
            }
            Page<District> districtPage =
                    districtRepository.findByGovernorate(governorate, page);

            PagedResources<DistrictResource> pagedDistrictResources =
                    pagedResourcesAssembler.toResource(districtPage,
                            districtResourceAssembler,
                            ControllerLinkBuilder.linkTo(
                                    ControllerLinkBuilder.methodOn(DistrictRestController.class)
                                            .getAllDistricts(governorate, name, page)).withSelfRel());

            return pagedDistrictResources;

        }

        if (name != null && !name.isEmpty()) {
            Page<District> districtPage =
                    districtRepository.findByNameLikeIgnoreCase("%" + name + "%", page);

            PagedResources<DistrictResource> pagedDistrictResources =
                    pagedResourcesAssembler.toResource(districtPage,
                            districtResourceAssembler,
                            ControllerLinkBuilder.linkTo(
                                    ControllerLinkBuilder.methodOn(DistrictRestController.class)
                                            .getAllDistricts(governorate, name, page)).withSelfRel());

            return pagedDistrictResources;
        }

        Page<District> districtPage =
                districtRepository.findAll(page);

        PagedResources<DistrictResource> pagedDistrictResources =
                pagedResourcesAssembler.toResource(districtPage,
                        districtResourceAssembler,
                        ControllerLinkBuilder.linkTo(
                                ControllerLinkBuilder.methodOn(DistrictRestController.class)
                                        .getAllDistricts(governorate, name, page)).withSelfRel());

        return pagedDistrictResources;
    }

    @GetMapping(path = "/{teamId}")
    HttpEntity<DistrictResource> getDistrictById(@PathVariable("id") Long id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isPresent()) {
//            ResponseEntity<Facility> facilityResponseEntity = new ResponseEntity<Facility>(facilityOptional.get(), HttpStatus.OK);
            DistrictResource districtResource = new DistrictResource(districtOptional.get());
            districtResource.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(DistrictRestController.class)
                                    .getDistrictById(id)
                    ).withSelfRel());
            return new ResponseEntity<DistrictResource>(districtResource, HttpStatus.OK);
        }
        return new ResponseEntity<DistrictResource>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public District postFacility(@RequestBody District district) {
        return districtRepository.save(district);
    }
}
