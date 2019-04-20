package com.nmcp.tech.casesmanagement.api;

import com.nmcp.tech.casesmanagement.api.resources.OrgunitResource;
import com.nmcp.tech.casesmanagement.api.resources.OrgunitResourceAssembler;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import com.nmcp.tech.casesmanagement.data.orgunits.OrgunitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Hamza on 2019-03-30.
 */
@RestController
@RequestMapping(path = "/orgunits", produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class OrgunitRestController {

    OrgunitRepository orgunitRepository;
    OrgunitResourceAssembler orgunitResourceAssembler;
    PagedResourcesAssembler<Orgunit> pagedResourcesAssembler;

    @Autowired
    public OrgunitRestController(OrgunitRepository orgunitRepository,
                                 OrgunitResourceAssembler orgunitResourceAssembler,
                                 PagedResourcesAssembler<Orgunit> pagedResourcesAssembler) {

        this.orgunitRepository = orgunitRepository;
        this.orgunitResourceAssembler = orgunitResourceAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(produces = "application/json")
    public PagedResources<OrgunitResource> getOrgunits(
            @PageableDefault(value = 10, page = 0) Pageable page) {
        Page<Orgunit> facilityPage =
                orgunitRepository.findAll(page);

        PagedResources<OrgunitResource> pagedOrgunitsResources =
                pagedResourcesAssembler.toResource(facilityPage,
                        orgunitResourceAssembler,
                        ControllerLinkBuilder.linkTo(
                                ControllerLinkBuilder.methodOn(OrgunitRestController.class)
                                        .getOrgunits(page)).withSelfRel());

        return pagedOrgunitsResources;
    }

    @GetMapping(path = "/{teamId}", produces = "application/json")
    HttpEntity<OrgunitResource> getOrgUnitById(@PathVariable("id") String uid) {
        Optional<Orgunit> orgunitOptional = orgunitRepository.findByIdString(uid);
        if (orgunitOptional.isPresent()) {
            OrgunitResource orgunitResource = new OrgunitResource(orgunitOptional.get());
            orgunitResource.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(OrgunitRestController.class)
                                    .getOrgUnitById(uid)
                    ).withSelfRel());
            return new ResponseEntity<OrgunitResource>(orgunitResource, HttpStatus.OK);
        }
        return new ResponseEntity<OrgunitResource>(HttpStatus.NOT_FOUND);
    }

}
