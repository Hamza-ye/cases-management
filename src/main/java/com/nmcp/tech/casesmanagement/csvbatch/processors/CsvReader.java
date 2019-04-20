package com.nmcp.tech.casesmanagement.csvbatch.processors;

import com.nmcp.tech.casesmanagement.data.District;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * Created by Hamza on 2019-02-12.
 */
//@Component
public class CsvReader<T> {
    private Class<T> genericType;
    private String file;
    private String[] columnsNames;
    private String writerSqlString;
    private String jobName;

    //    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    //    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    //    @Autowired
    public DataSource dataSource;

    // input (reader) -> Processor -> output (writer)

    public FlatFileItemReader<District> reader() {
        FlatFileItemReader<District> reader = new FlatFileItemReader<District>();
        reader.setResource(new ClassPathResource("districts.csv"));
        reader.setLineMapper(new DefaultLineMapper<District>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("teamId", "governorate_name", "district_name");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<District>() {{
                setTargetType(District.class);
            }});
        }});
        return reader;
    }


    public DistrictProcessor processor() {
        return new DistrictProcessor();
    }

    public JdbcBatchItemWriter<District> writer() {
        JdbcBatchItemWriter<District> writer = new JdbcBatchItemWriter<District>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<District>());
        writer.setSql("INSERT INTO district (teamId, governorate_name,district_name) VALUES (:teamId, :governorate_name, :district_name)");
        writer.setDataSource(dataSource);
        return writer;
    }

    //The next chunk focuses on the actual job configuration.
    public Job importedDistrictJop(JobExecutionListener listener) {
        return jobBuilderFactory.get("importedDistrictJop")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<District, District>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
