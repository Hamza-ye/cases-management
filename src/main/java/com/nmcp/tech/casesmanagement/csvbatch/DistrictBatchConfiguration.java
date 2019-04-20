package com.nmcp.tech.casesmanagement.csvbatch;


import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.DistrictJobCompletionNotificationListener;
import com.nmcp.tech.casesmanagement.csvbatch.processors.DistrictProcessor;
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

//@Configuration
//@EnableBatchProcessing
public class DistrictBatchConfiguration {

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

    // input (reader) -> Processor -> output (writer)
    @Bean
    public FlatFileItemReader<District> reader() {
        FlatFileItemReader<District> reader = new FlatFileItemReader<District>();
        reader.setResource(new ClassPathResource("districts.csv"));
        reader.setLineMapper(new DefaultLineMapper<District>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("teamId", "governorate_name", "district_name");
            }});
            setFieldSetMapper(new FieldSetMapper<District>() {
                @Override
                public District mapFieldSet(FieldSet fieldSet) throws BindException {
                    District fac = new District();
                    fac.setCode(fieldSet.readString(0)); //teamId
                    fac.setGovernorate(fieldSet.readString(1)); //gov
                    fac.setName(fieldSet.readString(2)); //name
//                    District dist = new District();
//                    dist = districtRepository.findById(fieldSet.readString(2)).get(); //name
//                    fac.setDistrict(dist);
                    return fac;
                }
            });
        }});
        return reader;
    }

    @Bean
    public DistrictProcessor processor() {
        return new DistrictProcessor();
    }

    @Bean
    public RepositoryItemWriter<District> writer() {
//        JdbcBatchItemWriter<District> writer = new JdbcBatchItemWriter<District>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<District>());
//        writer.setSql("INSERT INTO district (teamId, governorate_name,district_name) VALUES (:teamId, :governorate_name, :district_name)");
//        writer.setDataSource(dataSource);
//        return writer;
        RepositoryItemWriter<District> writer = new RepositoryItemWriter<District>();
        writer.setRepository(districtRepository);
        writer.setMethodName("save");
        return writer;
    }

    //The next chunk focuses on the actual job configuration.
    @Bean
    public Job importedDistrictJop(DistrictJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importedDistrictJop")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<District, District>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Bean
//    public FlatFileItemReader<Facility> reader() {
//        FlatFileItemReader<Facility> reader = new FlatFileItemReader<Facility>();
//        reader.setResource(new ClassPathResource("Facilities.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Facility>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "teamId", "name", "district"});
//            }});
//            setFieldSetMapper(new FieldSetMapper<Facility>() {
//                @Override
//                public Facility mapFieldSet(FieldSet fieldSet) throws BindException {
//                    Facility fac = new Facility();
//                    fac.setTeamId(fieldSet.readString(0));
//                    fac.setName(fieldSet.readString(1));
//                    District dist = new District();
//                    dist = districtRepository.findById(fieldSet.readString(2)).get();
//                    fac.setDistrict(dist);
//                    return fac;
//                }
//            });
//        }});
//        return reader;
//    }
//
//    @Bean
//    public FacilityProcessor processor() {
//        return new FacilityProcessor();
//    }
//
//    @Bean
//    public RepositoryItemWriter<Facility> writer() {
//        RepositoryItemWriter<Facility> writer = new RepositoryItemWriter<Facility>();
//        writer.setRepository(facilityRepository);
//        writer.setMethodName("save");
//        return writer;
////        JdbcBatchItemWriter<Facility> writer = new JdbcBatchItemWriter<Facility>();
////        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Facility>());
////        writer.setSql("INSERT INTO district (teamId, name,district_id) VALUES (:teamId, :name, :district_id)");
////        writer.setDataSource(dataSource);
////        return writer;
//    }
//    //The next chunk focuses on the actual job configuration.
//    @Bean
//    public Job importedDistrictJop(FacilityJobCompletionNotificationListener listener) {
//        return jobBuilderFactory.get("importedFacilitiesJop")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step1())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<Facility, Facility> chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

//    @Bean
//    public FlatFileItemReader<DataValue> reader() {
//        FlatFileItemReader<DataValue> reader = new FlatFileItemReader<DataValue>();
//        reader.setResource(new ClassPathResource("DataValuesCsv.csv"));
//        reader.setLineMapper(new DefaultLineMapper<DataValue>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "year", "week_no" ,"dataValue", "dataelement" , "Facility"}); //Year,Week,Value,dataelement,Facility
//            }});
//            setFieldSetMapper(new FieldSetMapper<DataValue>() {
//                @Override
//                public DataValue mapFieldSet(FieldSet fieldSet) throws BindException {
//                    DataValue dV = new DataValue();
////                    DataValueId dataValueId = new DataValueId();
////                    dataValueId.setYear(fieldSet.readInt(0));
////                    dataValueId.setWeekNo(fieldSet.readInt(1));
////                    dataValueId.setFacilityId(fieldSet.readString(4));
////                    dataValueId.setDataElementId(fieldSet.readString(3));
////                    dV.setDataValueId(dataValueId);
////                    dV.setDataValue(fieldSet.readInt(2));
//
//                    dV.setYear(fieldSet.readInt(0));
//                    dV.setWeek(fieldSet.readInt(1));
////                    dV.setFacilityId(fieldSet.readString(4));
////                    dV.setDataElementId(fieldSet.readString(3));
////                    dV.setDataValueId(dataValueId);
//                    dV.setDataValue(fieldSet.readInt(2));
//
//                    DataElement de = dataElementRepository.findById(fieldSet.readString(3)).get();
//                    Facility f = facilityRepository.findById(fieldSet.readString(4)).get();
//                    dV.setDataElement(de);
//                    dV.setFacility(f);
//                    return dV;
//                }
//            });
//        }});
//        return reader;
//    }
//
//    @Bean
//    public DataValueProcessor processor() {
//        return new DataValueProcessor();
//    }
//
//    @Bean
//    public RepositoryItemWriter<DataValue> writer() {
//        RepositoryItemWriter<DataValue> writer = new RepositoryItemWriter<DataValue>();
//        writer.setRepository(dataValueRepository);
//        writer.setMethodName("save");
//        return writer;
//    }
//    //The next chunk focuses on the actual job configuration.
//    @Bean
//    public Job importedDistrictJop(DataValueJobCompletionNotificationListener listener) {
//        return jobBuilderFactory.get("importedDataValuesJop")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step1())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<DataValue, DataValue> chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }

}