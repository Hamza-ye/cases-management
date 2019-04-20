package com.nmcp.tech.casesmanagement.csvbatch;

import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.FacilityJobCompletionNotificationListener;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import com.nmcp.tech.casesmanagement.data.orgunits.OrgunitRepository;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Hamza on 2019-03-30.
 */
public class OrgunitCsvWriter implements ItemWriter<Orgunit> {
    private static final Logger log = LoggerFactory.getLogger(FacilityJobCompletionNotificationListener.class);

    @Autowired
    private OrgunitRepository orgunitRepository;

    @Override
    public void write(List<? extends Orgunit> orgunits) throws Exception {
//        orgunitRepository
        for (Orgunit orgunit : orgunits) {
            log.info("org unit Name: " + orgunit.getName() + " orgunit Parent: " + orgunit.getParent());
            NestedSetsTreeNode parent = orgunitRepository.findByIdString(orgunit.getParent()).get();
            orgunitRepository.addChild(parent, orgunit);
        }
    }
}
