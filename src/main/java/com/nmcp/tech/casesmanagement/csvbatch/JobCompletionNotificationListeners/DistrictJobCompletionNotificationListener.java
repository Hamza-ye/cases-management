package com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners;


//import com.nmcp.tech.casesmanagement.data.District;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//import org.springframework.jdbc.core.RowMapper;

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;

@Component
public class DistrictJobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(DistrictJobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DistrictJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! District JOB FINISHED! Time to verify the results");
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