package com.nmcp.tech.casesmanagement;

import com.nmcp.tech.casesmanagement.integration.actionexamples.FileWriterGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class CasesManagementApplication {

    private static Logger logger = LoggerFactory.getLogger(CasesManagementApplication.class);
//    @Autowired
//    private ConfigurableEnvironment env;

    public static void main(String[] args) {
        SpringApplication.run(CasesManagementApplication.class, args);
    }

    @Bean
    public AtomicInteger getInteger() {
        return new AtomicInteger(new Integer(0));
    }

    @Bean
    public CommandLineRunner writeData(FileWriterGateway gateway, Environment env, AtomicInteger integer) {
        return args -> {
            String[] activeProfiles = env.getActiveProfiles();
            if (activeProfiles.length > 0) {
                String profile = activeProfiles[0];
//                logger.info("integer value 1: " + integer.getAndIncrement());
//                logger.info("integer value 2: " + integer.getAndIncrement());
//                logger.info("integer value 3: " + integer.getAndIncrement());
//                gateway.writeToFile("simple.txt", "Hello, Spring Integration! (" + profile + ")");

            } else {
                System.out.println("No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
            }
        };
    }

//    @PostConstruct
//    private void init() {
//        env.setActiveProfiles("javaconfig");
//    }

}

