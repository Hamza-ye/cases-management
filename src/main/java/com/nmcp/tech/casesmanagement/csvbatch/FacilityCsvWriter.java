package com.nmcp.tech.casesmanagement.csvbatch;

import com.nmcp.tech.casesmanagement.data.Facility;
import com.nmcp.tech.casesmanagement.data.FacilityRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Hamza on 2019-02-12.
 */

public class FacilityCsvWriter implements ItemWriter<Facility> {
    @Autowired
    private FacilityRepository studentRepository;

    @Override
    public void write(List<? extends Facility> students) throws Exception {
        studentRepository.saveAll(students);
    }
}