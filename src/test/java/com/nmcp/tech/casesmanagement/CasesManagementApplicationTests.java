package com.nmcp.tech.casesmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasesManagementApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void createFlux_fromArray() {
        String[] fruites = new String[]{"Apple", "Orange", "Grape", "banana", "Strawberry"};
        Flux<String> fruitFlux = Flux.fromArray(fruites);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

}

