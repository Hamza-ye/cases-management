package com.nmcp.tech.casesmanagement.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-12.
 */

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    //    Page<Facility> findBy(Pageable page);
    Page<Facility> findByNameLikeIgnoreCase(String name, Pageable page);

    int countByNameLikeIgnoreCase(String name);

    Optional<Facility> findByCode(String code);

    Page<Facility> findBy(Pageable page);

    //    Page<Facility> findByDistrict(District district);
    Page<Facility> findByDistrictCodeAndNameLikeIgnoreCase(String districtCode, String name, Pageable page);

    Page<Facility> findByDistrictCode(String districtCode, Pageable page);

    Page<Facility> findByDistrictGovernorate(String governorate, Pageable page);

    Page<Facility> findByDistrict(District district, Pageable page);


    Page<Facility> findByDistrictGovernorateLikeIgnoreCaseAndDistrictCodeAndNameLikeIgnoreCase(String governorate, String districtCode, String name, Pageable page);
}
