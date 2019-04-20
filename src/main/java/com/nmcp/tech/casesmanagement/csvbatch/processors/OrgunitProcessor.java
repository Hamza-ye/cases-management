package com.nmcp.tech.casesmanagement.csvbatch.processors;

import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Hamza on 2019-03-30.
 */
public class OrgunitProcessor implements ItemProcessor<Orgunit, Orgunit> {
    private static final Logger log = LoggerFactory.getLogger(FacilityProcessor.class);

    @Override
    public Orgunit process(final Orgunit orgunit) throws Exception {
//        final String firstName = DataElement.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//
//        final Person transformedPerson = new Person(firstName, lastName,person.getEmail(),person.getAge());
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return orgunit;
    }
}
