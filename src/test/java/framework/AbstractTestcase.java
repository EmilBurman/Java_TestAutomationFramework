package framework;

import framework.frontend.WebdriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.time.Instant;
import java.util.Date;

public abstract class AbstractTestcase {
    @BeforeAll
    public static void startTestSuite(){
        System.out.println("----------- STARTING NEW TEST SUITE -----------");
        System.out.println(Date.from(Instant.now()));
    }

    @AfterAll
    public static void shutdownTestSuite(){
        System.out.println("----------- ENDING TEST SUITE -----------");
        System.out.println(Date.from(Instant.now()));
    }

}
