package framework.api;

import framework.AbstractTestcase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Instant;
import java.util.Date;

public abstract class AbstractApiTestcase extends AbstractTestcase {

    @BeforeEach
    public void startNewTest(){
        System.out.println("*** STARTING NEW API TEST ***");
        System.out.println(Date.from(Instant.now()));
    }

    @AfterEach
    public void endTest(){
        System.out.println(Date.from(Instant.now()));
        System.out.println("*** ENDING API TEST ***");
    }

}
