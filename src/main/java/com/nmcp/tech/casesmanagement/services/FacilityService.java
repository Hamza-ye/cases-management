package com.nmcp.tech.casesmanagement.services;

import com.nmcp.tech.casesmanagement.data.Facility;
import com.nmcp.tech.casesmanagement.data.FacilityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-15.
 */
@Service
public class FacilityService implements FilterableCrudService<Facility> {
    FacilityRepository facilityRepository;

    public Optional<Facility> findAnyMatchingByCode(Optional facilityFilter) {
        String repositoryFilter = "" + facilityFilter.get() + "";
        return facilityRepository.findByCode(repositoryFilter);
    }

    @Override
    public JpaRepository getRepository() {
        return facilityRepository;
    }

    @Override
    public Page findAnyMatching(Optional filter, Pageable pageable) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return facilityRepository.findByNameLikeIgnoreCase(repositoryFilter, pageable);
        } else {
            return find(pageable);
        }
    }

    @Override
    public long countAnyMatching(Optional filter) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return facilityRepository.countByNameLikeIgnoreCase(repositoryFilter);
        } else {
            return count();
        }
    }

    public Page<Facility> find(Pageable pageable) {
        return facilityRepository.findBy(pageable);
    }

}
