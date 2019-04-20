package com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Hamza on 2019-03-30.
 */
@Component
public class OrgunitJobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(FacilityJobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! Facilities JOB FINISHED! Time to verify the results");
//
//			List<District> results = jdbcTemplate.query("SELECT governorateName, districtName FROM district", new RowMapper<District>() {
//				@Override
//				public District mapRow(ResultSet rs, int row) throws SQLException {
//					return new District(rs.getString(1), rs.getString(2));
//				}
//			});
//
//			for (District district : results) {
//				log.info("Found <" + district + "> in the database.");
//			}

        }
    }
}
