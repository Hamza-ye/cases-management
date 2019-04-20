package com.nmcp.tech.casesmanagement.services;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-15.
 */

public interface FilterableCrudService<T extends AbstractEntity> extends CrudService<T> {

    Page<T> findAnyMatching(Optional<String> filter, Pageable pageable);

    long countAnyMatching(Optional<String> filter);

}