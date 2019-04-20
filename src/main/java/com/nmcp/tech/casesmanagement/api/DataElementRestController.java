package com.nmcp.tech.casesmanagement.api;

import com.nmcp.tech.casesmanagement.data.DataElement;
import com.nmcp.tech.casesmanagement.data.DataElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hamza on 2019-02-13.
 */
@RestController
@RequestMapping(path = "/dataElement", produces = "application/json")
@CrossOrigin(origins = "*")
public class DataElementRestController {
    DataElementRepository dataElementRepository;

    @Autowired
    public DataElementRestController(DataElementRepository dataElementRepository) {
        this.dataElementRepository = dataElementRepository;
    }

    @GetMapping(produces = "application/json")
    public Iterable<DataElement> getDataElement() {
        PageRequest page = PageRequest.of(
                0, 20, Sort.by("createdAt").ascending());
        return dataElementRepository.findAll(page);
    }
}
