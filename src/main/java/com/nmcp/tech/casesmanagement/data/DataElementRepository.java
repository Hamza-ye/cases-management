package com.nmcp.tech.casesmanagement.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-12.
 */

public interface DataElementRepository extends JpaRepository<DataElement, Long> {

    Optional<DataElement> findByCode(String code);
}
