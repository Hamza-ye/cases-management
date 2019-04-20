package com.nmcp.tech.casesmanagement.services;

import com.nmcp.tech.casesmanagement.data.DataElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-02-16.
 */
public class DataElementService implements FilterableCrudService<DataElement> {

    @Override
    public Page<DataElement> findAnyMatching(Optional<String> filter, Pageable pageable) {
        return null;
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        return 0;
    }

    @Override
    public JpaRepository<DataElement, Long> getRepository() {
        return null;
    }
}
