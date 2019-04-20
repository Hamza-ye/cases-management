package com.nmcp.tech.casesmanagement.csvbatch.processors;

//import com.infotech.batch.model.Person;

import com.nmcp.tech.casesmanagement.data.Facility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class FacilityProcessor implements ItemProcessor<Facility, Facility> {

    private static final Logger log = LoggerFactory.getLogger(FacilityProcessor.class);

    @Override
    public Facility process(final Facility facility) throws Exception {
//        final String firstName = DataElement.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//
//        final Person transformedPerson = new Person(firstName, lastName,person.getEmail(),person.getAge());
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return facility;
    }

}