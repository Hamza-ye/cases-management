package com.nmcp.tech.casesmanagement.csvbatch;

import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.DataValueJobCompletionNotificationListener;
import com.nmcp.tech.casesmanagement.csvbatch.processors.DataValueProcessor;
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
public class DataValueBatchConfig {

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
    public FlatFileItemReader<DataValue> reader() {
        FlatFileItemReader<DataValue> reader = new FlatFileItemReader<DataValue>();
        reader.setResource(new ClassPathResource("DataValuesCsv.csv"));
        reader.setLineMapper(new DefaultLineMapper<DataValue>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("year", "week_no", "dataValue", "dataelement", "Facility"); //Year,Week,Value,dataelement,Facility
            }});
            setFieldSetMapper(new FieldSetMapper<DataValue>() {
                @Override
                public DataValue mapFieldSet(FieldSet fieldSet) throws BindException {
                    DataValue dV = new DataValue();
//                    DataValueId dataValueId = new DataValueId();
//                    dataValueId.setYear(fieldSet.readInt(0));
//                    dataValueId.setWeekNo(fieldSet.readInt(1));
//                    dataValueId.setFacilityId(fieldSet.readString(4));
//                    dataValueId.setDataElementId(fieldSet.readString(3));
//                    dV.setDataValueId(dataValueId);
//                    dV.setDataValue(fieldSet.readInt(2));

                    dV.setYear(fieldSet.readInt(0));
                    dV.setWeek(fieldSet.readInt(1));
//                    dV.setFacilityId(fieldSet.readString(4));
//                    dV.setDataElementId(fieldSet.readString(3));
//                    dV.setDataValueId(dataValueId);
                    dV.setDataValue(fieldSet.readInt(2));

                    DataElement de = dataElementRepository.findByCode(fieldSet.readString(3)).get();
                    Facility f = facilityRepository.findByCode(fieldSet.readString(4)).get();
                    dV.setDataElement(de);
                    dV.setFacility(f);
                    return dV;
                }
            });
        }});
        return reader;
    }

    @Bean
    public DataValueProcessor processor() {
        return new DataValueProcessor();
    }

    @Bean
    public RepositoryItemWriter<DataValue> writer() {
        RepositoryItemWriter<DataValue> writer = new RepositoryItemWriter<DataValue>();
        writer.setRepository(dataValueRepository);
        writer.setMethodName("save");
        return writer;
    }

    //The next chunk focuses on the actual job configuration.
    @Bean
    public Job importedDistrictJop(DataValueJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importedDataValuesJop")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<DataValue, DataValue>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
