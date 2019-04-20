package com.nmcp.tech.casesmanagement.csvbatch;

import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.FacilityJobCompletionNotificationListener;
import com.nmcp.tech.casesmanagement.csvbatch.processors.FacilityProcessor;
import com.nmcp.tech.casesmanagement.data.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;

/**
 * Created by Hamza on 2019-02-15.
 */
//@Configuration
//@EnableBatchProcessing
public class FacilityBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public FacilityRepository facilityRepository;

    @Autowired
    public DistrictRepository districtRepository;

    @Autowired
    public DataElementRepository dataElementRepository;

    @Autowired
    public DataValueRepository dataValueRepository;

    @Bean
    public FlatFileItemReader<Facility> reader() {
        FlatFileItemReader<Facility> reader = new FlatFileItemReader<Facility>();

        reader.setResource(new ClassPathResource("Facilities.csv"));

        reader.setLineMapper(new DefaultLineMapper<Facility>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("code", "name", "district");
            }});

            setFieldSetMapper(new FieldSetMapper<Facility>() {
                @Override
                public Facility mapFieldSet(FieldSet fieldSet) throws BindException {
                    Facility fac = new Facility();
                    fac.setCode(fieldSet.readString(0));
                    fac.setName(fieldSet.readString(1));
                    District dist = new District();
                    dist = districtRepository.findByCode(fieldSet.readString(2)).get();
                    fac.setDistrict(dist);
                    return fac;
                }
            });
        }});
        return reader;
    }

    @Bean
    public FacilityProcessor processor() {
        return new FacilityProcessor();
    }

    @Bean
    public RepositoryItemWriter<Facility> writer() {

        RepositoryItemWriter<Facility> writer = new RepositoryItemWriter<Facility>();
        writer.setRepository(facilityRepository);
        writer.setMethodName("save");
        return writer;
//        JdbcBatchItemWriter<Facility> writer = new JdbcBatchItemWriter<Facility>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Facility>());
//        writer.setSql("INSERT INTO district (teamId, name,district_id) VALUES (:teamId, :name, :district_id)");
//        writer.setDataSource(dataSource);
//        return writer;
    }

    //The next chunk focuses on the actual job configuration.
    @Bean
    public Job importedDistrictJop(FacilityJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importedFacilitiesJop")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Facility, Facility>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
