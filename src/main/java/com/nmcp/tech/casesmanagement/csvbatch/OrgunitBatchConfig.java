package com.nmcp.tech.casesmanagement.csvbatch;

import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.OrgunitJobCompletionNotificationListener;
import com.nmcp.tech.casesmanagement.csvbatch.processors.OrgunitProcessor;
import com.nmcp.tech.casesmanagement.data.District;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

/**
 * Created by Hamza on 2019-03-30.
 */
//@Configuration
//@EnableBatchProcessing
public class OrgunitBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public FlatFileItemReader<Orgunit> reader() {
        FlatFileItemReader<Orgunit> reader = new FlatFileItemReader<Orgunit>();

        reader.setResource(new ClassPathResource("Facilities.csv"));

        reader.setLineMapper(new DefaultLineMapper<Orgunit>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("code", "name", "idString", "parent");
            }});

            setFieldSetMapper(new FieldSetMapper<Orgunit>() {
                @Override
                public Orgunit mapFieldSet(FieldSet fieldSet) throws BindException {
                    Orgunit orgunit = new Orgunit();
                    orgunit.setCode(fieldSet.readString(0));
                    orgunit.setName(fieldSet.readString(1));
                    orgunit.setIdString(fieldSet.readString(2));
                    orgunit.setParent(fieldSet.readString(3));
                    District dist = new District();
                    return orgunit;
                }
            });
        }});
        return reader;
    }

    @Bean
    public OrgunitProcessor processor() {
        return new OrgunitProcessor();
    }

    @Bean
    public OrgunitCsvWriter writer() {

//        RepositoryItemWriter<Orgunit> writer = new RepositoryItemWriter<Orgunit>();
//        writer.setRepository(orgunitRepository);
//        writer.setMethodName("save");
        return new OrgunitCsvWriter();
//        JdbcBatchItemWriter<Orgunit> writer = new JdbcBatchItemWriter<Orgunit>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Orgunit>());
//        writer.setSql("INSERT INTO district (teamId, name,district_id) VALUES (:teamId, :name, :district_id)");
//        writer.setDataSource(dataSource);
//        return writer;
    }

    //The next chunk focuses on the actual job configuration.
    @Bean
    public Job importedOrgunitsJop(OrgunitJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importedOrgunitsJop")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Orgunit, Orgunit>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}


