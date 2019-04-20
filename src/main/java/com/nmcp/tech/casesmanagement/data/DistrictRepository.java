package com.nmcp.tech.casesmanagement.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-12.
 */

public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByCode(String code);

    Page<District> findByName(String code, Pageable pageable);

    Page<District> findByNameLikeIgnoreCase(String code, Pageable page);

    Page<District> findByGovernorate(String code, Pageable page);

    Page<District> findByGovernorateLikeIgnoreCaseAndNameLikeIgnoreCase(String governorate, String name, Pageable page);
}
