package com.nmcp.tech.casesmanagement.csvbatch.processors;

/**
 * Created by Hamza on 2019-02-12.
 */

import com.nmcp.tech.casesmanagement.data.DataValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class DataValueProcessor implements ItemProcessor<DataValue, DataValue> {

    private static final Logger log = LoggerFactory.getLogger(FacilityProcessor.class);

    @Override
    public DataValue process(final DataValue dataValue) throws Exception {
//        final String firstName = DataElement.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//
//        final Person transformedPerson = new Person(firstName, lastName,person.getEmail(),person.getAge());
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return dataValue;
    }

}