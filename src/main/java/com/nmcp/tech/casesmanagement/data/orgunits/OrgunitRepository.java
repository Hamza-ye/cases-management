package com.nmcp.tech.casesmanagement.data.orgunits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-03-29.
 */

public interface OrgunitRepository extends OrgunitRepositoryCustom, JpaRepository<Orgunit, Long> {

    Page<Orgunit> findByNameLikeIgnoreCase(String name, Pageable page);

    int countByNameLikeIgnoreCase(String name);

    Optional<Orgunit> findByCode(String code);

    Optional<Orgunit> findByIdString(String uid);
//    Page<OrgUnit> findBy(Pageable page);
}